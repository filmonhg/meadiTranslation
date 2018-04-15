package Util;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class NotifyMeDynamo implements RequestHandler<String,String> {

    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "notify_email";
    private Regions REGION = Regions.US_EAST_2;

    public String handleRequest(String email, Context context) {

        this.initDynamoDbClient();
        PutItemOutcome putItemOutcome=persistData(email);
        String personResponse ="Saved Successfully!!!";
        return putItemOutcome.toString();
    }

    private PutItemOutcome persistData(String email)
            throws ConditionalCheckFailedException {
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withString("email", email)));
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}