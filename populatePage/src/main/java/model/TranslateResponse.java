package model;

import java.util.ArrayList;
import java.util.List;

public class TranslateResponse {
    private String translatedWord;
    private String contributorId;
    private Integer voteCount;
    private List<String> votersList = new ArrayList<>();
    //private VotesResponse votesResponse;

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getContributorId() {
        return contributorId;
    }

    public void setContributorId(String contributorId) {
        this.contributorId = contributorId;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public List<String> getVotersList() {
        return votersList;
    }

    public void setVotersList(List<String> votersList) {
        this.votersList = votersList;
    }
/*
    public VotesResponse getVotesResponse() {
        return votesResponse;
    }

    public void setVotesResponse(VotesResponse votesResponse) {
        this.votesResponse = votesResponse;
    }*/
}
