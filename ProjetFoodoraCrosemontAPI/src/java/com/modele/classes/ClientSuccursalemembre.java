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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas
 */
@Entity
@Table(name = "client_succursalemembre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientSuccursalemembre.findAll", query = "SELECT c FROM ClientSuccursalemembre c")
    , @NamedQuery(name = "ClientSuccursalemembre.findById", query = "SELECT c FROM ClientSuccursalemembre c WHERE c.id = :id")
    , @NamedQuery(name = "ClientSuccursalemembre.findBySoldeArgentclient", query = "SELECT c FROM ClientSuccursalemembre c WHERE c.soldeArgentclient = :soldeArgentclient")
    , @NamedQuery(name = "ClientSuccursalemembre.findBySoldePointsclient", query = "SELECT c FROM ClientSuccursalemembre c WHERE c.soldePointsclient = :soldePointsclient")
    , @NamedQuery(name = "ClientSuccursalemembre.findByForeignKey", query = "SELECT c FROM ClientSuccursalemembre c WHERE c.numeroClient = :numeroClient AND c.idSuccursale = :idSuccursale")})
public class ClientSuccursalemembre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soldeArgent_client")
    private double soldeArgentclient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soldePoints_client")
    private int soldePointsclient;
    @JoinColumn(name = "numero_client", referencedColumnName = "numero")
    @OneToOne(optional = false)
    private Client numeroClient;
    @JoinColumn(name = "id_succursale", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Succursalemembre idSuccursale;

    public ClientSuccursalemembre() {
    }

    public ClientSuccursalemembre(Integer id) {
        this.id = id;
    }

    public ClientSuccursalemembre(Integer id, double soldeArgentclient, int soldePointsclient) {
        this.id = id;
        this.soldeArgentclient = soldeArgentclient;
        this.soldePointsclient = soldePointsclient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSoldeArgentclient() {
        return soldeArgentclient;
    }

    public void setSoldeArgentclient(double soldeArgentclient) {
        this.soldeArgentclient = soldeArgentclient;
    }

    public int getSoldePointsclient() {
        return soldePointsclient;
    }

    public void setSoldePointsclient(int soldePointsclient) {
        this.soldePointsclient = soldePointsclient;
    }

    public Client getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(Client numeroClient) {
        this.numeroClient = numeroClient;
    }

    public Succursalemembre getIdSuccursale() {
        return idSuccursale;
    }

    public void setIdSuccursale(Succursalemembre idSuccursale) {
        this.idSuccursale = idSuccursale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientSuccursalemembre)) {
            return false;
        }
        ClientSuccursalemembre other = (ClientSuccursalemembre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modele.classes.ClientSuccursalemembre[ id=" + id + " ]";
    }
    
}
