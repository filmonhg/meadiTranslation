package Model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Set;

/**
 * Created by Stella on 5/13/18.
 */
@DynamoDBTable(tableName="tigrinya_votes")
public class VoteRequest {

    private String tigrinyaWord;
    private int voteCount;
    private String englishWord;
    private String contributorId;
    private Set<String> votersId;
    private String voterId;

    @DynamoDBHashKey(attributeName="tigrinyaWord")
    public String getTigrinyaWord() { return tigrinyaWord; }
    public void setTigrinyaWord(String tigrinyaWord) {
        this.tigrinyaWord = tigrinyaWord;
    }

    @DynamoDBAttribute(attributeName="voteCount")
    public int getVoteCount() { return voteCount; }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @DynamoDBAttribute(attributeName="englishWord")
    public String getEnglishWord() { return englishWord; }
    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    @DynamoDBAttribute(attributeName="contributorId")
    public String getContributorId() { return contributorId; }
    public void setContributorId(String contributorId) {
        this.contributorId = contributorId;
    }

    @DynamoDBAttribute(attributeName="votersId")
    public Set<String> getVotersId() { return votersId; }
    public void setVotersId(Set<String> votersId) {
        this.votersId = votersId;
    }

    @DynamoDBIgnore
    public String getVoterId() { return voterId; }
    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
}
