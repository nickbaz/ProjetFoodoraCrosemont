 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrobillard.modele;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import org.json.JSONObject;
import 

/**
 *
 * @author Vengor
 */
public class FoodoraSuiviWebRequests
{
    private static final String url_suivi = 
        "http://localhost:8080/FoodoraServiceSuivi/webresources/suivi";
               
    public FoodoraSuiviWebRequests() 
    {
        Unirest.setObjectMapper(new ObjectMapper() 
        {
          private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper =
              new com.fasterxml.jackson.databind.ObjectMapper();

          public <T> T readValue(String value, Class<T> valueType) 
          {
            try 
            {
              return jacksonObjectMapper.readValue(value, valueType);
            } 
            catch (IOException e) 
            {
              throw new RuntimeException(e);
            }
          }

          public String writeValue(Object value) 
          {
            try 
            {
              return jacksonObjectMapper.writeValueAsString(value);
            } 
            catch (JsonProcessingException e) 
            {
              throw new RuntimeException(e);
            }
          }
        });
    }
    
    public HttpResponse<SuiviCommande> GetSuiviCommandeByNum() {
        HttpResponse<JsonNode> response = null;
        try 
        {
            response =  
                Unirest.get("http://localhost:8080/FoodoraServiceSuivi/webresources/suivi/12345678")
                .header("accept", "application/json")
                .asJson();
        } 
        catch (UnirestException uex) 
        {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + uex.getMessage());
        }
        return response;
    }
    
    public HttpResponse<JsonNode> GetAllSuiviCommande() {
        HttpResponse<JsonNode> response = null;
        try 
        {
            response =
                Unirest.get("http://localhost:8080/FoodoraServiceSuivi/webresources/suivi")
                .header("accept", "application/json")
                .asJson();
        } 
        catch (UnirestException uex) 
        {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + uex.getMessage());
        }
        return response;
    }
    
    public HttpResponse<JsonNode> ModifySuiviCommande(JSONObject NewSuivi) {
        HttpResponse<JsonNode> response = null;
        try 
        {
            response =  
                Unirest.put("http://localhost:8080/FoodoraServiceSuivi/webresources/suivi/12345678")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(NewSuivi)
                .asJson();
        } 
        catch (UnirestException uex) 
        {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + uex.getMessage());
            System.out.println("La modification a été annulé");
        }
        return response;
    }
    
    
}
