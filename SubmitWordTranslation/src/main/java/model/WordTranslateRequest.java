package model;

import java.util.Set;

public class WordTranslateRequest {
    private String contributorId;
    private String englishWord;
    private String tigrinyaWord;
    private Integer voteCount;
    private Set<String> votersId;

    public String getContributorId() {
        return contributorId;
    }

    public void setContributorId(String contributorId) {
        this.contributorId = contributorId;
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


    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Set<String> getVotersId() {
        return votersId;
    }

    public void setVotersId(Set<String> votersId) {
        this.votersId = votersId;
    }


}
