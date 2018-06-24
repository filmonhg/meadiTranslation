import java.util.Set;

public class WordVoteRequest {
    private String tigrinyaWord;
    private int voteCount;
    private String englishWord;
    private String contributorId;
    private Set<String> votersId;

    public String getTigrinyaWord() {
        return tigrinyaWord;
    }

    public void setTigrinyaWord(String tigrinyaWord) {
        this.tigrinyaWord = tigrinyaWord;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getContributorId() {
        return contributorId;
    }

    public void setContributorId(String contributorId) {
        this.contributorId = contributorId;
    }

    public Set<String> getVotersId() {
        return votersId;
    }

    public void setVotersId(Set<String> votersId) {
        this.votersId = votersId;
    }
}
