package model;

public class TranslateResponse {
    private String tigrinyaWord;
    private String contributorId;
    private VotesResponse votesResponse;

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

    public VotesResponse getVotesResponse() {
        return votesResponse;
    }

    public void setVotesResponse(VotesResponse votesResponse) {
        this.votesResponse = votesResponse;
    }
}
