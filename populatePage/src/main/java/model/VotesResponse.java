package model;

import java.util.*;

public class VotesResponse {
    //private Set<String> votersId;
    private Integer voteCount;
    private List<String> votersList = new ArrayList<>();

    /*public Set<String> getVotersId() {
        return votersId;
    }

    public void setVotersId(Set<String> votersId) {
        this.votersId = votersId;
    }

*/
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

    @Override
    public String toString() {
        return "VotesResponse{" +
                "voteCount=" + voteCount +
                ", votersList=" + votersList +
                '}';
    }
}
