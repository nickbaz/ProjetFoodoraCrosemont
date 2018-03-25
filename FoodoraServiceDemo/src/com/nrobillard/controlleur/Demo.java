//Programme de démonstration qui effectue quelques requêtes pour montrer
//le fonctionnement du service REST FoodoraServiceSuivi
package com.nrobillard.controlleur;

import com.mashape.unirest.http.HttpResponse;
import com.nrobillard.modele.FoodoraSuiviWebRequests;
import static com.nrobillard.modele.FoodoraSuiviWebRequests.GetSuiviCommandeByNum;
import static com.nrobillard.modele.FoodoraSuiviWebRequests.ModifySuiviCommande;
import org.json.JSONArray;
import org.json.JSONObject;

public class Demo
{

    public static void main(String[] args)
    {
        System.out.println("\nJSON de la commmande 12345678 : ");
        JSONObject ResultatJSON = GetSuiviCommandeByNum(12345678).getBody().getObject();
        System.out.println(ResultatJSON);
        
        System.out.println("\nListe des suivis de commandes : ");
        JSONArray ListeSuivi = FoodoraSuiviWebRequests.GetAllSuiviCommande().getBody().getArray();
        System.out.println(ListeSuivi);
        
        System.out.println("\nModification du status de la commande 12345678 de Complet à En Cours: ");
        JSONObject NewSuivi = 
            new JSONObject("{\"emplacement\":\"40.714224,-73.961452\",\"numero\":\"12345678\",\"dateCommande\":\"2018-02-19T08:18:37\",\"dateComplet\":\"2018-02-20T02:19:29\",\"status\":\"En Cours\"}");
        HttpResponse<String> ResultatModif = ModifySuiviCommande(NewSuivi, 12345678);
        System.out.println("Résultat de la modification: ");
        //Une modification réussi devrait retourner 204 No Content (Parce qu'il n'y a aucun retour)
        System.out.println(ResultatModif.getStatus() + ":" + ResultatModif.getStatusText());
        
        System.out.println("\nJSON de la commmande 12345678 après modification : ");
        ResultatJSON = GetSuiviCommandeByNum(12345678).getBody().getObject();
        System.out.println(ResultatJSON);
        
        System.out.println("\nModification invalide (date invalide): ");
        NewSuivi = 
            new JSONObject("{\"emplacement\":\"40.714224,-73.961452\",\"numero\":\"12345678\",\"dateCommande\":\"2018-Fevrier-19T08:18:37\",\"dateComplet\":\"2018-Fevrier-20T02:19:29\",\"status\":\"En Cours\"}");
        ResultatModif = ModifySuiviCommande(NewSuivi, 12345678);
        //Une modification invalide retournera une status 400 ou 500
        System.out.println("Résultat de la modification: ");
        System.out.println(ResultatModif.getStatus() + " : " + ResultatModif.getStatusText());
        
        System.out.println("\nCreation de la commande 222222");
        FoodoraSuiviWebRequests.PostCreateSuiviCommande("{\"emplacement\":\"42.714224,-63.961452\",\"numero\":222222,\"dateCommande\":\"2018-02-19T08:18:37\",\"dateComplet\":\"2018-02-20T02:19:29\",\"status\":\"En cours\"}");
        
        System.out.println("\nCount de suivi de commande après création: ");
        System.out.println("Résultat: " + FoodoraSuiviWebRequests.GetCountSuiviCommande().getBody());
        
        System.out.println("\nDelete du suivi de commande numero 222222");
        FoodoraSuiviWebRequests.DeleteSuiviCommande("222222").getStatus();
        
        System.out.println("\nCount de suivi de commande après la suppression: ");
        System.out.println(FoodoraSuiviWebRequests.GetCountSuiviCommande().getBody());
    }
    
} 
