/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointFidelite.webservices;

import com.pointFidelite.webservices.dao.ClientDAO;
import com.pointFidelite.webservices.dao.Connexion;
import com.pointFidelite.webservices.dao.SuccursaleDAO;
import com.pointFidelite.webservices.modeles.Client;
import com.pointFidelite.webservices.modeles.SuccursaleMembre;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author nbazinet
 */
@WebService(serviceName = "ProgrammeFideliteWS")
public class ProgrammeFideliteWS {
    
    private Client client;
    private SuccursaleMembre succursaleMembre;
    private final Connection cnx = this.setCnx();
    private final ClientDAO cDao = new ClientDAO(cnx);
    private final SuccursaleDAO sDao = new SuccursaleDAO(cnx);
    
    
    
    @WebMethod(operationName = "openTransaction")
    public void openTransaction(String idClient, int idSuccursale){
        client = cDao.getClient(idClient);
        succursaleMembre = sDao.getSuccursale(idSuccursale);
    }
    @WebMethod(operationName = "closeTransaction")
    public void closeTransaction(){
        client = null;
        succursaleMembre = null;
    }
    
    @WebMethod(operationName = "isSuccursaleMembre")
    public boolean isSuccursaleMembre(int id){
        if(sDao.isMembre(id))
            return true;
        return false;
    }
    
    @WebMethod(operationName = "getSoldeClient")
    public long getSoldeClient(String id){
        return client.getNombreDePoint();
    }
    
    @WebMethod(operationName = "usePoints")
    public double usePoints(){
        double argentUtilise = client.getSoldeEnArgent();
        client.setNombreDePoint(0);
        client.setSoldeEnArgent(0);
        cDao.update(client);
        return argentUtilise;
    }
    
    @WebMethod(operationName = "getSoldeArgent")
    public double getSoldeArgent(){
        return client.getSoldeEnArgent();
    }
    
    public Connection setCnx(){        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connexion.setUrl("jdbc:mysql://localhost/programmefidelite?user=root&password=root");
            Connection cnt = Connexion.getInstance();
            return cnt;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }
    
}
