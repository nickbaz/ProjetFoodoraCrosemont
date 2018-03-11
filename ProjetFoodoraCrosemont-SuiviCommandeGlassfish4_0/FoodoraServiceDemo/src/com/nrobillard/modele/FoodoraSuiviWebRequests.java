 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrobillard.modele;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Vengor
 */
public class FoodoraSuiviWebRequests
{
    public static HttpResponse<JsonNode> GetSuiviCommandeByNum() {
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
    public static HttpResponse<JsonNode> GetAllSuiviCommande() {
        HttpResponse<JsonNode> response = null;
        try 
        {
            response =
                Unirest.get("http://localhost:8080/FoodoraServiceSuivi/webresources/suivi").
                        header("accept", "application/json")
                        .asJson();
        } 
        catch (UnirestException uex) 
        {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + uex.getMessage());
        }
        return response;
    }
     public static HttpResponse<JsonNode> PostCreateSuiviCommande(){
                 HttpResponse<JsonNode> response = null;
                 JSONObject newSuivi;
        try {
            newSuivi = new JSONObject("{\"emplacement\":\"42.714224,-63.961452\",\"numero\":222222,\"dateCommande\":\"2018-02-19T08:18:37\",\"dateComplet\":\"2018-02-20T02:19:29\",\"status\":\"En cours\"}");
            Unirest.post("http://localhost:8080/FoodoraServiceSuivi/webresources/suivi/")
                    .header("Content-Type", "application/json")
                    .header("accept", "application/json")
                    .body(newSuivi).asJson();
            } catch (JSONException ex) {
            System.out.println("La requête a déclenché une exception.");
            System.out.println("Message: " + ex.getMessage());
            
        } catch (UnirestException ex) {
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
                Unirest.get("http://localhost:8080/FoodoraServiceSuivi/webresources/suivi/count")
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
