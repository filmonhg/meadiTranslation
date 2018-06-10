import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordsHandler {

    private static String DYNAMODB_TABLE_NAME = "words_table";
    private static Regions REGION = Regions.US_WEST_2;
    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(REGION)
            .withCredentials(new ProfileCredentialsProvider("filmonProfile"))
            .build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    public static void main(String[] args) throws Exception {
        WordsHandler wordsHandler = new WordsHandler();


        wordsHandler.readFile("words_sample");

        //wordsHandler.loadDataToTable(DYNAMODB_TABLE_NAME);

    }
    public void readFile(String fileName)
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        int order=0;
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                loadDataToTable(DYNAMODB_TABLE_NAME,line,order);
                order++;
                System.out.println(line);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataToTable(String tableName, String word, int order)
    {
        String firstChar = word.substring(0,1).toUpperCase();

        Table table = dynamoDB.getTable(tableName);
        try
        {
            //Item item = new Item(); //.withPrimaryKey("wordId", 001).withString("Word", "About");
            table.putItem(new PutItemSpec().withItem(new Item()
                    .withString("entryKey",firstChar)
                    .withInt("wordId",order)
                    .withString("word",word)));
        }
        catch (Exception e)
        {
            System.err.println("Failed to create item in table "+tableName+" " +e.getMessage());
        }
    }

    private void initDynamoDbClient() {
/*        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);*/

    }
}
