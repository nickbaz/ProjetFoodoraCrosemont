
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
        JSONObject suivi = FoodoraSuiviWebRequests.GetSuiviCommandeByNum().getBody().getObject();
        System.out.println(suivi.toString());
        
        //System.out.println("creation de la commande 222222 ");
        //JSONObject creation = FoodoraSuiviWebRequests.PostCreateSuiviCommande().getBody().getObject();
        //System.out.println(FoodoraSuiviWebRequests.PostCreateSuiviCommande());
        
        System.out.println("Count de suivi de commande : ");
        //JSONObject compte = FoodoraSuiviWebRequests.GetCountSuiviCommande().getBody().getObject();
        System.out.println(FoodoraSuiviWebRequests.GetCountSuiviCommande().getBody());
    }
    
}
