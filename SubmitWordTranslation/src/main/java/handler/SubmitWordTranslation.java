package handler;


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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import model.*;

public class SubmitWordTranslation implements RequestHandler<WordTranslateRequest, WordTranslateResponse> {

        private DynamoDB dynamoDb;
        //private String DYNAMODB_TABLE_NAME = "tigrinya_translate_vote";
        private Regions REGION = Regions.US_WEST_2;
        //private HashMap<String,String> tableDetails = new HashMap<>();


        public WordTranslateResponse handleRequest(WordTranslateRequest wordTranslateRequest, Context context) {
            System.out.println("language : "+wordTranslateRequest.getLanguage());
            TableDetails tableDetails =TranslationConstants.languageInfo.get(wordTranslateRequest.getLanguage());
            //System.out.println("table Name : "+ TranslationConstants.languageInfo.get("tigrinya").toString());
            this.initDynamoDbClient();


            persistData(wordTranslateRequest,tableDetails);

            WordTranslateResponse wordTranslateResponse = new WordTranslateResponse();
            wordTranslateResponse.setMessage("Saved translated word Successfully!");
            return wordTranslateResponse;
        }

        private PutItemOutcome persistData(WordTranslateRequest wordTranslateRequest, TableDetails tableDetails)
                throws ConditionalCheckFailedException {
            System.out.println("table name : " + tableDetails.getTableName());
            System.out.println("Column Name : "+ tableDetails.getColumnName());
            Set<String> initialVotersSet = new HashSet<>();
            initialVotersSet.add("XXX");
            return this.dynamoDb.getTable(tableDetails.getTableName())
                    .putItem(
                            new PutItemSpec().withItem(new Item()
                                    .withString(tableDetails.getColumnName(),wordTranslateRequest.getTranslatedWord())
                                    .withString("englishWord",wordTranslateRequest.getEnglishWord())
                                    //.withString("timestamp", getCurrentTimeISO8601())
                                    .withString("contributorId",wordTranslateRequest.getContributorId())
                                    .withInt("voteCount",0)
                                    .withStringSet("votersId", initialVotersSet)
                            ));


        }

        private String getCurrentTimeISO8601()
        {

            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
            df.setTimeZone(tz);
            return df.format(new Date());
        }

        private void initDynamoDbClient() {
            AmazonDynamoDBClient client = new AmazonDynamoDBClient();
            client.setRegion(Region.getRegion(REGION));
            this.dynamoDb = new DynamoDB(client);
        }
    }


