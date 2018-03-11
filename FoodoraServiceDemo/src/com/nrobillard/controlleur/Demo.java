
package com.nrobillard.controlleur;

import com.nrobillard.modele.FoodoraSuiviWebRequests;
import static com.nrobillard.modele.FoodoraSuiviWebRequests.GetSuiviCommandeByNum;
import static com.nrobillard.modele.FoodoraSuiviWebRequests.ModifySuiviCommande;
import org.json.JSONArray;
import org.json.JSONObject;

public class Demo
{

    public static void main(String[] args)
    {
        System.out.println("JSON de la commmande 12345678 : ");
        JSONObject ResultatJSON = GetSuiviCommandeByNum().getBody().getObject();
        System.out.println(ResultatJSON);
        System.out.println(ResultatJSON.toString());
        
        System.out.println("Liste des suivis de commandes : ");
        JSONArray ListeSuivi = FoodoraSuiviWebRequests.GetAllSuiviCommande().getBody().getArray();
        System.out.println(ListeSuivi);
        System.out.println(ListeSuivi.toString());
        
        System.out.println("Modification du status de la commande 12345678 de Complet à En Cours: ");
        JSONObject NewSuivi = 
            new JSONObject("{\"emplacement\":\"40.714224,-73.961452\",\"numero\":\"doop\",\"dateCommande\":\"2018-02-19T08:18:37\",\"dateComplet\":\"dddd-02-20T02:19:29\",\"status\":\"Complet\"}");
        int ResultatModif = ModifySuiviCommande(NewSuivi).getStatus();
        System.out.println("Résultat de la modification: ");
        System.out.println(ResultatModif);
        
        System.out.println("JSON de la commmande 12345678 : ");
        ResultatJSON = GetSuiviCommandeByNum().getBody().getObject();
        System.out.println(ResultatJSON);
        System.out.println(ResultatJSON.toString());
    }
    
} 
