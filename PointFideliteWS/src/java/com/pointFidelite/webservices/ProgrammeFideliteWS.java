/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointFidelite.webservices;

import com.pointFidelite.webservices.dao.ClientDAO;
import com.pointFidelite.webservices.dao.SuccursaleDAO;
import com.pointFidelite.webservices.modeles.Client;
import com.pointFidelite.webservices.modeles.SuccursaleMembre;

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
    
    
    @WebMethod(operationName = "openTransaction")
    public void openTransaction(String idClient, int idSuccursale){
        client = ClientDAO.getClient(idClient);
        succursaleMembre = SuccursaleDAO.getSuccursale(idSuccursale);
    }
    @WebMethod(operationName = "closeTransaction")
    public void closeTransaction(){
        client = null;
        succursaleMembre = null;
    }
    
    @WebMethod(operationName = "isSuccursaleMembre")
    public boolean isSuccursaleMembre(int id){
        if(SuccursaleDAO.isMembre(id))
            return true;
        return false;
    }
    
    @WebMethod(operationName = "getSoldeClient")
    public long getSoldeClient(String id){
        return ClientDAO.getClient(id).getNombreDePoint();
    }
    
    @WebMethod(operationName = "usePoints")
    public double usePoints(){
        double argentUtilise = client.getSoldeEnArgent();
        client.setNombreDePoint(0);
        client.setSoldeEnArgent(0);
        
        return argentUtilise;
    }
    
    @WebMethod(operationName = "getSoldeArgent")
    public double getSoldeArgent(){
        return client.getSoldeEnArgent();
    }
    
}
