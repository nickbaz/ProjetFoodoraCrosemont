/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas
 */
@Entity
@Table(name = "succursalemembre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Succursalemembre.findAll", query = "SELECT s FROM Succursalemembre s")
    , @NamedQuery(name = "Succursalemembre.findById", query = "SELECT s FROM Succursalemembre s WHERE s.id = :id")
    , @NamedQuery(name = "Succursalemembre.findByNom", query = "SELECT s FROM Succursalemembre s WHERE s.nom = :nom")
    , @NamedQuery(name = "Succursalemembre.findByTauxRemise", query = "SELECT s FROM Succursalemembre s WHERE s.tauxRemise = :tauxRemise")})
public class Succursalemembre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tauxRemise")
    private double tauxRemise;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idSuccursale")
    private ClientSuccursalemembre clientSuccursalemembre;

    public Succursalemembre() {
    }

    public Succursalemembre(Integer id) {
        this.id = id;
    }

    public Succursalemembre(Integer id, String nom, double tauxRemise) {
        this.id = id;
        this.nom = nom;
        this.tauxRemise = tauxRemise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTauxRemise() {
        return tauxRemise;
    }

    public void setTauxRemise(double tauxRemise) {
        this.tauxRemise = tauxRemise;
    }

    public ClientSuccursalemembre getClientSuccursalemembre() {
        return clientSuccursalemembre;
    }

    public void setClientSuccursalemembre(ClientSuccursalemembre clientSuccursalemembre) {
        this.clientSuccursalemembre = clientSuccursalemembre;
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
        if (!(object instanceof Succursalemembre)) {
            return false;
        }
        Succursalemembre other = (Succursalemembre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modele.classes.Succursalemembre[ id=" + id + " ]";
    }
    
}
