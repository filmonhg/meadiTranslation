import Model.SignInForm;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Created by KOKOB on 3/25/18.
 */
public class SignIn implements RequestHandler<SignInForm,String> {
    public String handleRequest(SignInForm signInForm, Context context) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("");
    }
}
