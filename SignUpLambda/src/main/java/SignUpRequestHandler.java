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

public class SignUpRequestHandler implements RequestHandler<SignUpDetails,SignUpResponse>{
    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "users";
    private Regions REGION = Regions.US_WEST_2;

    public SignUpResponse handleRequest(SignUpDetails signUpDetails, Context context) {

        this.initDynamoDbClient();

        persistData(signUpDetails);

        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("User Saved Successfully!!!");
        return signUpResponse;
    }

    private PutItemOutcome persistData(SignUpDetails signUpDetails)
            throws ConditionalCheckFailedException {
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withString("userId",signUpDetails.getRequest().getUserAttributes().getPhone_number())
                                .withString("email",signUpDetails.getRequest().getUserAttributes().getEmail())
                                .withString("firstName", signUpDetails.getRequest().getUserAttributes().getName())
                                .withString("lastName",signUpDetails.getRequest().getUserAttributes().getFamily_name())
                                .withString("phone",signUpDetails.getRequest().getUserAttributes().getPhone_number())
                                .withString("address",signUpDetails.getRequest().getUserAttributes().getAddress())
                                .withBoolean("active",false)
                                ));
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
