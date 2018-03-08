/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoraCrosemont.Utils;

import com.modele.classes.Client;
import com.modele.classes.ClientSuccursalemembre;
import com.modele.classes.Succursalemembre;
import java.util.Objects;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicolas
 */
public class ClientSuccursalemembreBuilder {
    
    static Client client;
    
    static ClientSuccursalemembre clientSuccursalemembre = null;
    
    static Succursalemembre succursalemembre;
    
    /**
     * 
     * Build a ClientSuccursalemembre with the numeroClient and idSuccursalemembre given in parameter.
     * Return null if it does not exists in the DB.
     * 
     * @param numeroClient
     * @param idSuccursalemembre
     * @return 
     */
    public static ClientSuccursalemembre BuildClientSuccursalemembre(String numeroClient, int idSuccursalemembre){

        if(!Objects.isNull(numeroClient) && !Objects.isNull(idSuccursalemembre)){
            
            succursalemembre = new Succursalemembre();
            succursalemembre.setId(idSuccursalemembre);

            client = new Client();
            client.setNumero(numeroClient);

            clientSuccursalemembre = new ClientSuccursalemembre();
            clientSuccursalemembre.setIdSuccursale(succursalemembre);
            clientSuccursalemembre.setNumeroClient(client);

        }
        return clientSuccursalemembre;
    }
}
