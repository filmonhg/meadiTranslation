import Model.SignUpForm;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Created by KOKOB on 3/25/18.
 */
public class SignUp implements RequestHandler<SignUpForm,String> {
    public String handleRequest(SignUpForm signUpForm, Context context) {
        return null;
    }
}
