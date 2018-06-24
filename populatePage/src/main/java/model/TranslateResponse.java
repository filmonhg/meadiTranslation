package model;

import java.util.HashMap;
import java.util.Map;

public class TranslateResponse {
    private String tigrinyaWord;
    private String contributorId;
    private Integer voteCount;
    private Map<String,String> votersMap = new HashMap<>();
    //private VotesResponse votesResponse;

    public String getTigrinyaWord() {
        return tigrinyaWord;
    }

    public void setTigrinyaWord(String tigrinyaWord) {
        this.tigrinyaWord = tigrinyaWord;
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

    public Map<String, String> getVotersMap() {
        return votersMap;
    }

    public void setVotersMap(Map<String, String> votersMap) {
        this.votersMap = votersMap;
    }
/*
    public VotesResponse getVotesResponse() {
        return votesResponse;
    }

    public void setVotesResponse(VotesResponse votesResponse) {
        this.votesResponse = votesResponse;
    }*/
}
