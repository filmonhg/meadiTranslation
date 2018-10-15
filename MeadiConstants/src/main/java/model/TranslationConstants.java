package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TranslationConstants {

    public static final Map<String,TableDetails> languageInfo = new HashMap<String,TableDetails>();
    private static final String Tigrinya="{\"tableName\":\"tigrinya_translate_vote\",\"columnName\":\"translatedWord\"}";
    private static final String Tigre="{\"tableName\":\"tigre_translate_vote\",\"columnName\":\"translatedWord\"}";
    private static final String Bilen="{\"tableName\":\"bilen_translate_vote\",\"columnName\":\"translatedWord\"}";

    static {
            ObjectMapper mapper = new ObjectMapper();
        try {
            languageInfo.put("tigrinya",mapper.readValue(Tigrinya,TableDetails.class));
            languageInfo.put("tigre",mapper.readValue(Tigre,TableDetails.class));
            languageInfo.put("bilen",mapper.readValue(Bilen,TableDetails.class));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


