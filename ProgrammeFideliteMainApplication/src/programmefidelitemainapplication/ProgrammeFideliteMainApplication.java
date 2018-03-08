/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmefidelitemainapplication;

import com.modele.classes.Client;

/**
 *
 * @author Nicolas
 */
public class ProgrammeFideliteMainApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomerClient cc = new CustomerClient();
        Client c1 = cc.find(Client.class, "12345");
        System.out.println(c1.getNumero());
        cc.close();
    }
    
}
