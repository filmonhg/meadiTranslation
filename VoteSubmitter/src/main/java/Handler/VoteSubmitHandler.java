package Handler;

import Exceptions.VoteAlreadyExistException;
import Model.VoteRequest;
import Model.VoteSubmitterResponse;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.TableDetails;
import model.TranslationConstants;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Stella on 5/14/18.
 */
public class VoteSubmitHandler implements RequestHandler<VoteRequest, VoteSubmitterResponse>
{

    private static DynamoDBMapper dynamoDbMapper;

    @Override
    public VoteSubmitterResponse handleRequest(VoteRequest voteRequest, Context context)
    {
        try {
            System.out.println(voteRequest.toString());
            System.out.println("table language : "+voteRequest.getLanguage());
            TableDetails tableDetails =TranslationConstants.languageInfo.get(voteRequest.getLanguage());
            System.out.println("table details test: "+tableDetails);
            String tableName= tableDetails.getTableName();
            this.initDynamoDbClient(tableName);
            if(voteRequest.getVotersId() != null){
                if(voteRequest.getVotersId().contains(voteRequest.getVoterId())){
                    throw new VoteAlreadyExistException("ናይ "+voteRequest.getTranslatedWord()+" ምርጫ ኣቀዲሙ ተረኪቡ አዩ።");
                }
                voteRequest.getVotersId().add(voteRequest.getVoterId());
            }else {
                Set<String> votersId = new HashSet<>();
                votersId.add(voteRequest.getVoterId());
                voteRequest.setVotersId(votersId);
            }
            voteRequest.setVoteCount(voteRequest.getVoteCount() + 1);
            insertOrUpdateVote(voteRequest);
        }
        catch (VoteAlreadyExistException e){
            e.printStackTrace();
            return new VoteSubmitterResponse((e.getMessage()!=null) ? e.getMessage() : "Sorry, something went wrong","208");
        }
        catch (Exception e){
            e.printStackTrace();
            return new VoteSubmitterResponse("Server Exception","500");
        }
        return new VoteSubmitterResponse("ናይ "+voteRequest.getTranslatedWord()+" ዕዉት ምርጫ፣ ተሳትፎኩም ነመስግን።: ","201");
    }

    private static void insertOrUpdateVote(VoteRequest voteRequest)
    {
        dynamoDbMapper.save(voteRequest);
    }

    private void initDynamoDbClient(String tableName) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_WEST_2).build();
        DynamoDBMapperConfig  mapperConfig = new DynamoDBMapperConfig.Builder().withTableNameOverride(TableNameOverride.withTableNameReplacement(tableName))
                .build();
        dynamoDbMapper = new DynamoDBMapper(client,mapperConfig);
    }



}
