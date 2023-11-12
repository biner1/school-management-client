package main.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    private final PrintWriter out;
    private final BufferedReader in;
    private final Gson gson;
    private final ObjectMapper objectMapper;
    private final HashMap<String, Map<String, String>> request;
    private final HashMap<String, String> resource;



    public RequestHandler(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
        this.gson = new Gson();
        this.objectMapper = new ObjectMapper();
        this.request = new HashMap<>();
        this.resource = new HashMap<>();
    }

    public Map<String, Map<String, String>> sendRequest(String endpoint) {
//        resource.clear();
        resource.put("method", "GET");
        resource.put("resource", endpoint);

        request.put("header", resource);
        String json = gson.toJson(request);
        out.println(json);
        System.out.println("this is from req handler: " + json);
        return getResponse();

    }

    public Map<String, Map<String, String>> sendRequest(String endpoint, String method) {
        resource.put("method", method.toUpperCase());
        resource.put("resource", endpoint);

        request.put("header", resource);
        String json = gson.toJson(request);
        out.println(json);
        System.out.println("this is from req handler: " + json);
        return getResponse();
    }

    public void setRequest(String key, Map<String, String> value) {
        this.request.put(key, value);
    }

    public Map<String, Map<String, String>> getResponse() {
        Map<String, Map<String, String>> response;
        String json;
        try {
            System.out.println(" this is from getResponse "+ (json = in.readLine()));
            response = objectMapper.readValue(json, Map.class);
            return response;
        } catch (JsonMappingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
