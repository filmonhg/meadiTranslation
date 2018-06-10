package model;


import java.util.ArrayList;
import java.util.List;

public class WordResponse {
    private String word;
    private String wordId;

    private List<TranslateResponse> translationResponses= new ArrayList<TranslateResponse>();


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public List<TranslateResponse> getTranslationResponses() {
        return translationResponses;
    }

    public void setTranslationResponses(List<TranslateResponse> translationResponses) {
        this.translationResponses = translationResponses;
    }

    public void addTranslateResponse(TranslateResponse translateResponse)
    {
        translationResponses.add(translateResponse);
    }
}
