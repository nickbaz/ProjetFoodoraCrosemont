/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes.application;

import com.classes.CustomerClient;
import com.classes.CustomerClientSuccursalemembre;
import com.classes.CustomerSuccursalemembre;
import com.modele.classes.Client;
import com.modele.classes.ClientSuccursalemembre;
import com.modele.classes.Succursalemembre;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        System.out.println("Début des test pour la classe Client."
                + "\n===============================================");
        
        CustomerClient c1 = new CustomerClient();
        String c1Json = c1.findClient("CT12345");
        JsonReader reader = Json.createReader(new StringReader(c1Json));
        JsonObject jsonObject = reader.readObject();
        Client client1 = new Client();
        client1.setNumero(jsonObject.getString("numero"));
        
        //Test Find
        System.out.println("Supposé être : CT12345 | Est en réalité : " + client1.getNumero());
        
        //Test getSoldeArgent
        c1.usePoints("CT12345", "5");
        jsonObject = (Json.createReader(new StringReader(c1.getSoldeArgent("CT12345", "5")))).readObject();
        System.out.println("Supposé être : 0.0 | Est en réalité : "+jsonObject.getString("soldeArgent"));        
        
        
        //Test getSoldePoints 
        jsonObject = (Json.createReader(new StringReader(c1.getSoldePoints("CT12345", "5")))).readObject();
        System.out.println("Supposé être : 0 | Est en réalité : "+jsonObject.getString("soldePoints"));
        
        //Test addPoints - getSoldePoints - getSoldeArgent 
        c1.addPoints("CT12345", "5", "400");
        jsonObject = (Json.createReader(new StringReader(c1.getSoldePoints("CT12345", "5")))).readObject();
        System.out.println("Supposé être : 400 | Est en réalité : "+jsonObject.getString("soldePoints"));
        jsonObject = (Json.createReader(new StringReader(c1.getSoldeArgent("CT12345", "5")))).readObject();
        System.out.println("Supposé être : 4.0 | Est en réalité : "+jsonObject.getString("soldeArgent"));
        
        //Test MakeTransaction
        c1.makeTransaction("50", "CT12345", "5");
        jsonObject = (Json.createReader(new StringReader(c1.getSoldePoints("CT12345", "5")))).readObject();
        System.out.println("Supposé être : 450 | Est en réalité : "+jsonObject.getString("soldePoints"));
        jsonObject = (Json.createReader(new StringReader(c1.getSoldeArgent("CT12345", "5")))).readObject();
        System.out.println("Supposé être : 4.5 | Est en réalité : "+jsonObject.getString("soldeArgent"));
        
        System.out.println("===============================================");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException("Error during the sleep of the thread.", ex);
        }
        
        
        //Test Succursalemembre
        System.out.println("Début des test pour la classe Succursalemembre."
                + "\n===============================================");
                
        CustomerSuccursalemembre sm = new CustomerSuccursalemembre();
        String smJson = sm.findSuccursalemembre("5");
        reader = Json.createReader(new StringReader(smJson));
        jsonObject = reader.readObject();
        Succursalemembre succMembre = new Succursalemembre();
        succMembre.setId(jsonObject.getInt("id"));
        succMembre.setNom(jsonObject.getString("nom"));
        succMembre.setTauxRemise((jsonObject.getJsonNumber("tauxRemise")).doubleValue());
        
        //Test find
        System.out.println("Supposé être : 5, Canadian Tire, 0.01 | Est en réalité : "+succMembre.getId()+", "
                + ""+succMembre.getNom()+", "+succMembre.getTauxRemise());

        //Test isSuccursaleMembre
        jsonObject = (Json.createReader(new StringReader(sm.isSuccursaleMembre("7")))).readObject();
        System.out.println("Supposé être : true | Est en réalité : "+jsonObject.getBoolean("isMembre"));
        
        //Test getTauxRemise
        jsonObject = (Json.createReader(new StringReader(sm.getTauxRemise("6")))).readObject();
        System.out.println("Supposé être : 0.02 | Est en réalité : "+Double.parseDouble(jsonObject.getString("tauxRemise")));
        
        System.out.println("===============================================");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException("Error during the sleep of the thread.", ex);
        }
        
        //Test ClientSuccursalemembre
        //Test Succursalemembre
        System.out.println("Début des test pour la classe ClientSuccursalemembre."
                + "\n===============================================");
        
        CustomerClientSuccursalemembre csm = new CustomerClientSuccursalemembre();
        String csmJson = csm.findClientSuccursalemembre("CT12345", "5");
        reader = Json.createReader(new StringReader(csmJson));
        jsonObject = reader.readObject();
        ClientSuccursalemembre clientSuccMembre = new ClientSuccursalemembre();
        clientSuccMembre.setId(jsonObject.getInt("id"));
        clientSuccMembre.setIdSuccursale(succMembre);
        clientSuccMembre.setNumeroClient(client1);
        clientSuccMembre.setSoldePointsclient(jsonObject.getInt("soldePoints_client"));
        clientSuccMembre.setSoldeArgentclient(jsonObject.getInt("soldeArgent_client"));
        
        //Test find
        System.out.println("Supposé être : 1, CT12345, 5, 4, 450 | Est en réalité : "+clientSuccMembre.getId()+", "
                + ""+clientSuccMembre.getNumeroClient().getNumero()+", "+clientSuccMembre.getIdSuccursale().getId()+", "+clientSuccMembre.getSoldeArgentclient()+", "
                + ""+clientSuccMembre.getSoldePointsclient());
        
        //Test isSuccursaleMembre
        jsonObject = (Json.createReader(new StringReader(csm.isClientMembre("CT12345","5")))).readObject();
        System.out.println("Supposé être : true | Est en réalité : "+jsonObject.getBoolean("isClientMembre"));
        
        System.out.println("===============================================");

    }
    
}
