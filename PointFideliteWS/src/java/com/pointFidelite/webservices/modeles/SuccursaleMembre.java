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
public class SuccursaleMembre {
    
    private int idSuccursale;
    private String nomSuccursale;
    private long tauxRemise;

    public int getIdSuccursale() {
        return idSuccursale;
    }

    public void setIdSuccursale(int idSuccursale) {
        this.idSuccursale = idSuccursale;
    }

    public String getNomSuccursale() {
        return nomSuccursale;
    }

    public void setNomSuccursale(String nomSuccursale) {
        this.nomSuccursale = nomSuccursale;
    }

    public long getTauxRemise() {
        return tauxRemise;
    }

    public void setTauxRemise(long pourcentageRemise) {
        this.tauxRemise = pourcentageRemise;
    }
}
