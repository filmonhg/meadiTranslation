package Handler;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.TranslateResponse;
import model.VotesResponse;
import model.WordResponse;

import java.io.IOException;
import java.util.*;

public class TWordDetailsHandler implements RequestHandler<TWordDetailsRequest,TWordDetailsResponse> {

    private DynamoDB dynamoDb;
    private Regions REGION = Regions.US_WEST_2;
    private TWordDetailsResponse tWordDetailsResponse = new TWordDetailsResponse();

    public TWordDetailsResponse handleRequest(TWordDetailsRequest tWordDetailsRequest, Context context) {

        this.initDynamoDbClient();

        try {
            queryTable(tWordDetailsRequest.getEntryKey(), tWordDetailsRequest.getWordId());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return tWordDetailsResponse;
    }

    public TWordDetailsResponse queryTable(String entryKey, Integer startId) throws IOException {
        Integer lastId=startId+4;
        Table table = this.dynamoDb.getTable("words_table");
        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("entryKey = :ek and wordId between :w_id_1 and :w_id_2 ")
                .withMaxResultSize(3)
                .withValueMap(new ValueMap()
                        .withInt(":w_id_1", startId)
                        .withInt(":w_id_2",lastId)
                        .withString(":ek",entryKey));


        ItemCollection<QueryOutcome> items = table.query(spec);
        Iterator<Item> iterator = items.iterator();
        Item item = null;
        while (iterator.hasNext()) {
            WordResponse wordResponse = new WordResponse();
            item = iterator.next();
            System.out.println(item.get("word"));
            System.out.println(item.toJSONPretty());
            wordResponse.setWord(item.getString("word"));
            wordResponse.setWordId(item.getString("wordId"));
            wordResponse.setTranslationResponses(queryTranslated(item.getString("word")));
            tWordDetailsResponse.addItem(wordResponse);

        }
        return tWordDetailsResponse;
    }

    private List<TranslateResponse> queryTranslated(String englishWord)
    {
        List<TranslateResponse> translateResponses = new ArrayList<TranslateResponse> ();
        Table table = this.dynamoDb.getTable("tigrinya_translates");

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("englishWord = :engl_word")
                //.withMaxResultSize(3)
                .withValueMap(new ValueMap()
                        .withString(":engl_word",englishWord));
        ItemCollection<QueryOutcome> items = table.query(spec);
        Iterator<Item> iterator = items.iterator();
        Item item= null;
        while(iterator.hasNext())
        {
            item=iterator.next();
            System.out.println(item.toJSONPretty());
            TranslateResponse translateResponse = new TranslateResponse();
            translateResponse.setTigrinyaWord(item.getString("tigrinyaWord"));
            translateResponse.setContributorId(queryUser(item.getString("contributerId")));
            translateResponse.setVotesResponse(queryVotes(item.getString("tigrinyaWord")));
            translateResponses.add(translateResponse);
        }

        return translateResponses;
    }

    private VotesResponse queryVotes(String tigrinyaWord)
    {
        VotesResponse votesResponse = new VotesResponse();
        Table table = this.dynamoDb.getTable("tigrinya_votes");

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("tigrinyaWord = :tig_word")
                //.withMaxResultSize(3)
                .withValueMap(new ValueMap()
                        .withString(":tig_word",tigrinyaWord));
        ItemCollection<QueryOutcome> items = table.query(spec);
        Iterator<Item> iterator = items.iterator();
        Item item= null;
        while(iterator.hasNext()) {
            item = iterator.next();
            votesResponse.setVoteCount(item.getInt("voteCount"));
            votesResponse.setVotersMap(queryVoterUsers(item.getStringSet("votersId")));
        }
        return votesResponse;
    }

    private String queryUser(String userId)
    {
        String fullName=null;
        Table table = this.dynamoDb.getTable("users");
        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("userId = :u_id")
                .withValueMap(new ValueMap()
                        .withString(":u_id",userId));
        ItemCollection<QueryOutcome> items = table.query(spec);
        Iterator<Item> iterator = items.iterator();
        Item item= null;
        while(iterator.hasNext()) {
            item = iterator.next();
            fullName=item.get("firstName").toString()+" "+item.get("lastName").toString();
        }
        return fullName;
    }

    private Map<String,String> queryVoterUsers(Set<String> voters)
    {
        Map<String,String> votersMap= new HashMap<>();
        for(String s: voters)
        {
            votersMap.put(s,queryUser(s));

        }
        return votersMap;
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}