package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VotesResponse {
    //private Set<String> votersId;
    private Integer voteCount;
    private Map<String,String> votersMap = new HashMap<>();

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

    public Map<String, String> getVotersMap() {
        return votersMap;
    }

    public void setVotersMap(Map<String, String> votersMap) {
        this.votersMap = votersMap;
    }

    @Override
    public String toString() {
        return "VotesResponse{" +
                "voteCount=" + voteCount +
                ", votersMap=" + votersMap +
                '}';
    }
}
