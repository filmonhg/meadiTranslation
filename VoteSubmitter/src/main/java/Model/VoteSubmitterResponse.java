package Model;

/**
 * Created by Stella on 5/14/18.
 */
public class VoteSubmitterResponse {

    private String voteMessage;
    private String statusCode;

    public VoteSubmitterResponse(String voteMessage, String statusCode) {
        this.voteMessage = voteMessage;
        this.statusCode = statusCode;
    }

    public String getVoteMessage() {
        return voteMessage;
    }

    public void setVoteMessage(String voteMessage) {
        this.voteMessage = voteMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
