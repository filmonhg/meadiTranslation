import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SubmitWordVote implements RequestHandler<WordVoteRequest,WordVoteResponse> {
    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "tigrinya_translate_vote";
    private Regions REGION = Regions.US_WEST_2;



    public WordVoteResponse handleRequest(WordVoteRequest wordVoteRequest, Context context) {

        this.initDynamoDbClient();

        persistData(wordVoteRequest);

        WordVoteResponse wordVoteResponse = new WordVoteResponse();
        wordVoteResponse.setMessage("Saved voted word Successfully!");
        return wordVoteResponse;
    }

    private PutItemOutcome persistData(WordVoteRequest wordVoteRequest)
            throws ConditionalCheckFailedException {

        System.out.println(wordVoteRequest.getVoteCount());
        System.out.println(wordVoteRequest.getVotersId().toString());
        System.out.println(wordVoteRequest.getContributorId());
        System.out.println(wordVoteRequest.getEnglishWord());
        System.out.println(wordVoteRequest.getTigrinyaWord());
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withString("tigrinyaWord",wordVoteRequest.getTigrinyaWord())
                                .withString("englishWord",wordVoteRequest.getEnglishWord())
                                .withString("contributorId",wordVoteRequest.getContributorId())
                                .withInt("voteCount",wordVoteRequest.getVoteCount())
                                .withStringSet("votersId",wordVoteRequest.getVotersId())
                        ));


    }


    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
