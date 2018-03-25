
package com.nrobillard.controlleur;

import com.nrobillard.modele.FoodoraSuiviWebRequests;
import static com.nrobillard.modele.FoodoraSuiviWebRequests.GetSuiviCommandeByNum;
import org.json.JSONArray;
import org.json.JSONObject;

public class Demo
{

    public static void main(String[] args)
    {
        System.out.println("Liste des suivis de commandes : ");
        JSONArray ListeSuivi = FoodoraSuiviWebRequests.GetAllSuiviCommande().getBody().getArray();
        System.out.println(ListeSuivi);
        System.out.println(ListeSuivi.toString());
        
        System.out.println("suivi de la commande 12345678 : ");
        JSONObject suivi = FoodoraSuiviWebRequests.GetSuiviCommandeByNum("12345678").getBody().getObject();
        System.out.println(suivi.toString());
        
        //fonctionne
        System.out.println("creation de la commande 222222 ");
        System.out.println(FoodoraSuiviWebRequests.PostCreateSuiviCommande("{\"emplacement\":\"42.714224,-63.961452\",\"numero\":222222,\"dateCommande\":\"2018-02-19T08:18:37\",\"dateComplet\":\"2018-02-20T02:19:29\",\"status\":\"En cours\"}"));
        
        //fonctionne
        System.out.println("Count de suivi de commande : ");
        System.out.println(FoodoraSuiviWebRequests.GetCountSuiviCommande().getBody());
        
        //fonctionne
        System.out.println("Delete du suivi de commande numero 222222 : ");
        System.out.println(FoodoraSuiviWebRequests.DeleteSuiviCommande("222222").getBody());
    }
    
}
