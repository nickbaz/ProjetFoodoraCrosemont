//Classe qui contient les méthodes utilisés pour faire des requêtes à l'API REST FoodoraServiceSuivi
//Utilise la librairie Unirest pour faire les requêtes
package com.nrobillard.modele;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;


public class FoodoraSuiviWebRequests
{
    public static HttpResponse<JsonNode> GetSuiviCommandeByNum(int Id) {
        HttpResponse<JsonNode> response = null;
        try 
        {
            response =  
                Unirest.get("http://localhost:8080/FoodoraServiceSuiviApache/webapi/suivi/" + Id)
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
    
    public static HttpResponse<JsonNode> GetAllSuiviCommande() {
        HttpResponse<JsonNode> response = null;
        try 
        {
            response =
                Unirest.get("http://localhost:8080/FoodoraServiceSuiviApache/webapi/suivi")
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
    
    public static HttpResponse<String> ModifySuiviCommande(JSONObject NewSuivi, int Id) {
        HttpResponse<String> response = null;
        try 
        {
            
            response =  
                Unirest.put("http://localhost:8080/FoodoraServiceSuiviApache/webapi/suivi/" + Id)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(NewSuivi)
                .asString();
            
        } 
        catch (UnirestException uex) 
        {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + uex.getMessage());
            System.out.println("La modification a été annulé");
        }
        return response;
    }
    
    public static HttpResponse<JsonNode> PostCreateSuiviCommande(String entity){
                 
        HttpResponse<JsonNode> response = null;
        JSONObject newSuivi;
        try {
            newSuivi = new JSONObject(entity);
            Unirest.post("http://localhost:8080/FoodoraServiceSuiviApache/webapi/suivi/")
                    .header("Content-Type", "application/json")
                    .header("accept", "application/json")
                    .body(newSuivi).asJson();
            } catch (JSONException | UnirestException ex) {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + ex.getMessage());
            
        }
        return response;
     }
     
     public static HttpResponse<String> GetCountSuiviCommande() {
        HttpResponse<String> response = null;
        try 
        {
            response =
                Unirest.get("http://localhost:8080/FoodoraServiceSuiviApache/webapi/suivi/count")
                        .header("Content-Type", "text/html")
                        .asString();
        } 
        catch (UnirestException uex) 
        {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + uex.getMessage());
        }
        return response;
    }
     
     public static HttpResponse<String> DeleteSuiviCommande(String id) {
        HttpResponse<String> response = null;
        try 
        {
            response =
                    Unirest.delete("http://localhost:8080/FoodoraServiceSuiviApache/webapi/suivi/".concat(id))
                    .header("Content-Type", "text/html")
                    .asString();
        } 
        catch (UnirestException uex) 
        {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + uex.getMessage());
        }
        return response;
    }
    
    
}
