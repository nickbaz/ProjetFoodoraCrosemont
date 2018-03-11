/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes.application;

import com.classes.CustomerClient;
import com.classes.CustomerSuccursalemembre;
import com.modele.classes.Client;
import com.modele.classes.Succursalemembre;
import java.io.StringReader;
import javax.json.*;

/**
 *
 * @author Nicolas
 */
public class ApplicationTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Test Client
        CustomerClient c1 = new CustomerClient();
        String c1Json = c1.findClient("CT12345");
        JsonReader reader = Json.createReader(new StringReader(c1Json));
        JsonObject jsonObject = reader.readObject();
        Client client1 = new Client();
        client1.setNumero(jsonObject.getString("numero"));
        
        System.out.println("Supposé être : CT12345 | Est en réalité : " + client1.getNumero());
        
        //Test Succursalemembre
        CustomerSuccursalemembre sm = new CustomerSuccursalemembre();
        String smJson = sm.findSuccursalemembre("5");
        reader = Json.createReader(new StringReader(smJson));
        jsonObject = reader.readObject();
        Succursalemembre succMembre = new Succursalemembre();
        succMembre.setId(jsonObject.getInt("id"));
        succMembre.setNom(jsonObject.getString("nom"));
        succMembre.setTauxRemise(Double.parseDouble(jsonObject.getString("tauxRemise")));
        
        System.out.println("Supposé être : 5, Canadian Tire, 0.01 | Est en réalité : "+succMembre.getId()+", "
                + ""+succMembre.getClientSuccursalemembre().getIdSuccursale().getNom()+", "+succMembre.getTauxRemise());

        jsonObject = (Json.createReader(new StringReader(sm.isSuccursaleMembre("7")))).readObject();
        System.out.println("Supposé être : true | Est en réalité : "+jsonObject.getBoolean("isMembre"));
        
        jsonObject = (Json.createReader(new StringReader(sm.getTauxRemise("6")))).readObject();
        System.out.println("Supposé être : 0.02 | Est en réalité : "+Double.parseDouble(jsonObject.getString("tauxRemise")));

    }
    
}
