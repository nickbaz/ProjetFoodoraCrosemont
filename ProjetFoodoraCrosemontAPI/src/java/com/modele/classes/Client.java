/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author utilisateur
 */
@Entity
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findByNumero", query = "SELECT c FROM Client c WHERE c.numero = :numero")
    , @NamedQuery(name = "Client.findBySoldePoints", query = "SELECT c FROM Client c WHERE c.soldePoints = :soldePoints")
    , @NamedQuery(name = "Client.findBySoldeArgent", query = "SELECT c FROM Client c WHERE c.soldeArgent = :soldeArgent")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soldePoints")
    private int soldePoints;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soldeArgent")
    private double soldeArgent;

    public Client() {
    }

    public Client(String numero) {
        this.numero = numero;
    }

    public Client(String numero, int soldePoints, double soldeArgent) {
        this.numero = numero;
        this.soldePoints = soldePoints;
        this.soldeArgent = soldeArgent;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getSoldePoints() {
        return soldePoints;
    }

    public void setSoldePoints(int soldePoints) {
        this.soldePoints = soldePoints;
    }

    public double getSoldeArgent() {
        return soldeArgent;
    }

    public void setSoldeArgent(double soldeArgent) {
        this.soldeArgent = soldeArgent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modele.classes.Client[ numero=" + numero + " ]";
    }
    
}
