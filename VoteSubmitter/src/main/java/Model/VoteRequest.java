package Model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Set;

/**
 * Created by Stella on 5/13/18.
 */
@DynamoDBTable(tableName="tigrinya_translate_vote")
public class VoteRequest {

    private String englishWord;
    private String translatedWord;
    private int voteCount;
    private String contributorId;
    private Set<String> votersId;
    private String voterId;
    private String language;

    @DynamoDBIgnore
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    @DynamoDBHashKey(attributeName="englishWord")
    public String getEnglishWord() { return englishWord; }
    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    @DynamoDBRangeKey(attributeName="translatedWord")
    public String getTranslatedWord() { return translatedWord; }
    public void setTranslatedWord(String tigrinyaWord) {
        this.translatedWord = tigrinyaWord;
    }

    @DynamoDBAttribute(attributeName="voteCount")
    public int getVoteCount() { return voteCount; }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
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

    @Override
    public String toString() {
        return "VoteRequest{" +
                "englishWord='" + englishWord + '\'' +
                ", translatedWord='" + translatedWord + '\'' +
                ", voteCount=" + voteCount +
                ", contributorId='" + contributorId + '\'' +
                ", votersId=" + votersId +
                ", voterId='" + voterId + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
