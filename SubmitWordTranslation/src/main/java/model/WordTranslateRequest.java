package model;

import java.util.Set;

public class WordTranslateRequest {
    private String language;
    private String contributorId;
    private String englishWord;
    private String translatedWord;
    private Integer voteCount;
    private Set<String> votersId;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

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

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
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
