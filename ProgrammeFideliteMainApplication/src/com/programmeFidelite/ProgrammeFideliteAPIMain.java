/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.programmeFidelite;

import com.modele.classes.Client;
import com.modele.classes.service.ClientFacadeREST;
import com.modele.classes.service.ClientSuccursalemembreFacadeREST;

/**
 *
 * @author Nicolas
 */
public class ProgrammeFideliteAPIMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomerClient client = new CustomerClient();
        Client clientAPI = client.find(Client.class, "CT12345");
//        System.out.println(clientAPI.getNumero());
//        ClientFacadeREST cfr = new ClientFacadeREST();
//        System.out.println(cfr.getSoldePoints(clientAPI.getNumero(), 5));
//        cfr.addPoints(clientAPI.getNumero(), 5, 50);
//        System.out.println(cfr.getSoldePoints(clientAPI.getNumero(), 5));
    }
    
}
