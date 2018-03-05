
package com.nrobillard.controlleur;

import static com.nrobillard.modele.FoodoraSuiviWebRequests.GetSuiviCommandeByNum;
import org.json.JSONObject;

public class Demo
{

    public static void main(String[] args)
    {
        System.out.println("JSON de la commmande 12345678 : ");
        JSONObject ResultatJSON = GetSuiviCommandeByNum().getBody().getObject();
        System.out.println(ResultatJSON);
    }
    
}
