/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointFidelite.webservices.modeles;

/**
 *
 * @author nbazinet
 */
public class Client {
    
    private String idClient;
    private long nombreDePoint;
    private double soldeEnArgent;

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public long getNombreDePoint() {
        return nombreDePoint;
    }

    public void setNombreDePoint(long nombreDePoint) {
        this.nombreDePoint = nombreDePoint;
    }

    public double getSoldeEnArgent() {
        return soldeEnArgent;
    }

    public void setSoldeEnArgent(double soldeEnArgent) {
        this.soldeEnArgent = soldeEnArgent;
    }
    
}
