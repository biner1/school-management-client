package main.utility;

import java.util.LinkedList;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToList {
    public static LinkedList<Map<String, String>> jsonListToList(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        LinkedList<Map<String, String>> staffList = null;
        try {
            staffList = objectMapper.readValue(json, LinkedList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffList;
    }// end of jsonListToList()

}
