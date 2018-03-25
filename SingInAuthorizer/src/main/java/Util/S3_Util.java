package Util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by KOKOB on 3/25/18.
 */
public class S3_Util {

    private static AmazonS3 s3;

    private static final String crowdSourceAppBucket = "weferadata";

    public static final String crowdSourceBucketKey = "Subscription/NotifyMe/";

    public static final String crowdSourceNotifyMeFileName = "users.txt";


    static {
        s3 = AmazonS3ClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()).build();
    }

    public S3_Util(){

    }

    public boolean upLoadFile(String key, File file){

        try{
            s3.putObject(new PutObjectRequest(crowdSourceAppBucket, key, file));

            return true;

        } catch (AmazonServiceException ase){

            System.out.println("Amazon service upLoadFile error msg "+ ase.getMessage());

            return false;
        }
    }

    public List<String> getLinesFromS3File(String key){

        try {

            S3Object s3object = s3.getObject(new GetObjectRequest(crowdSourceAppBucket,key));
            BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
            String line;
            List<String> lines = new ArrayList<String>();

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;

        } catch (IOException e) {

            e.printStackTrace();

            return Collections.emptyList();

        }catch (AmazonServiceException ase){

            System.out.println("Amazon service getLinesFromS3File error msg "+ ase.getMessage());

            return Collections.emptyList();
        }
    }

    public boolean deleteFile(String bucketName, String Key){
        try{
            s3.deleteObject(new DeleteObjectRequest(crowdSourceAppBucket +"/"+bucketName,Key));

            return true;

        } catch (AmazonServiceException ase){

            System.out.println("Amazon service upLoadFile error msg "+ ase.getMessage());

            return false;
        }
    }

    public File downLoadFile(String key ,String fileName, String downloadedFileName){

        try {
            System.out.println("Downloading " + fileName + " ....");

            File file = new File("/tmp/"+downloadedFileName);

            s3.getObject(new GetObjectRequest(crowdSourceAppBucket, key + fileName), file);

            return file;
        } catch (AmazonServiceException ase){
            System.out.println("Amazon service downLoadFile error msg "+ ase.getMessage());
            return null;
        }

    }

    public boolean moveFileFromToS3(String sourceKey, String destinationKey){

        try{

            s3.copyObject(crowdSourceAppBucket,sourceKey, crowdSourceAppBucket,destinationKey);

            s3.deleteObject(new DeleteObjectRequest(crowdSourceAppBucket,sourceKey));

            System.out.println("File moved succesfully :");

            return true;

        } catch (AmazonServiceException ase){

            System.out.println("Amazon service upLoadFile error msg "+ ase.getMessage());

            return false;
        }
    }
}
