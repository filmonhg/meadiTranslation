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

public class SignUpRequestHandler implements RequestHandler<SignUpRequest,SignUpResponse>{
    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "users";
    private Regions REGION = Regions.US_WEST_2;

    public SignUpResponse handleRequest(SignUpRequest signUpRequest, Context context) {

        this.initDynamoDbClient();

        persistData(signUpRequest);

        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("User Saved Successfully!!!");
        return signUpResponse;
    }

    private PutItemOutcome persistData(SignUpRequest signUpRequest)
            throws ConditionalCheckFailedException {
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withString("userId",signUpRequest.getPhone())
                                .withString("email",signUpRequest.getEmail())
                                .withString("firstName", signUpRequest.getFirstName())
                                .withString("lastName",signUpRequest.getLastName())
                                .withString("phone",signUpRequest.getPhone())
                                .withString("address",signUpRequest.getAddress())
                                .withBoolean("active",false)
                                ));
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
