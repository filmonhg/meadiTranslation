import Util.S3_Util;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KOKOB on 3/25/18.
 */
public class NotifyMe implements RequestHandler<String,String> {

    public String handleRequest(String emailAddress, Context context) {

        if (emailAddress != null && validateEmail(emailAddress)) {

            try {

                S3_Util s3_util = new S3_Util();

                File usersFile = s3_util.downLoadFile(S3_Util.crowdSourceBucketKey, S3_Util.crowdSourceNotifyMeFileName, S3_Util.crowdSourceNotifyMeFileName);

                List<String> notifyMeUsers = Files.readAllLines(Paths.get(usersFile.toURI()));

                if (notifyMeUsers.contains(emailAddress)) {

                    return "User already saved";

                } else {

                    Path path = Paths.get(usersFile.toURI());

                    notifyMeUsers.add(emailAddress);

                    Path write = Files.write(path, notifyMeUsers);

                    s3_util.upLoadFile(S3_Util.crowdSourceBucketKey + S3_Util.crowdSourceNotifyMeFileName, write.toFile());

                    return "Thank you for your participation";
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "Sorry, something went wrong";
            }

        } else {

            return "Sorry, input is invalid";
        }
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

}
