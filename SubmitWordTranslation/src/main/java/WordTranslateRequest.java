import java.util.Set;

public class WordTranslateRequest {
    private String contributerId;
    private String englishWord;
    private String tigrinyaWord;
    //private Set<String> voters;

    public String getContributerId() {
        return contributerId;
    }

    public void setContributerId(String contributerId) {
        this.contributerId = contributerId;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getTigrinyaWord() {
        return tigrinyaWord;
    }

    public void setTigrinyaWord(String tigrinyaWord) {
        this.tigrinyaWord = tigrinyaWord;
    }

    /*
    public Set<String> getVoters() {
        return voters;
    }

    public void setVoters(Set<String> voters) {
        this.voters = voters;
    }

    @Override
    public String toString() {
        return "WordTranslateRequest{" +
                "contributerId='" + contributerId + '\'' +
                ", englishWord='" + englishWord + '\'' +
                ", tigrinyaWord='" + tigrinyaWord + '\'' +
                ", voters=" + voters +
                '}';
    } */
}
