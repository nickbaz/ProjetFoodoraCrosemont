/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrobillard.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vengor
 */
@Entity
@Table(name = "suivi_commande")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SuiviCommande.findAll", query = "SELECT s FROM SuiviCommande s")
    , @NamedQuery(name = "SuiviCommande.findByNumero", query = "SELECT s FROM SuiviCommande s WHERE s.numero = :numero")
    , @NamedQuery(name = "SuiviCommande.findByStatus", query = "SELECT s FROM SuiviCommande s WHERE s.status = :status")
    , @NamedQuery(name = "SuiviCommande.findByEmplacement", query = "SELECT s FROM SuiviCommande s WHERE s.emplacement = :emplacement")
    , @NamedQuery(name = "SuiviCommande.findByDateCommande", query = "SELECT s FROM SuiviCommande s WHERE s.dateCommande = :dateCommande")
    , @NamedQuery(name = "SuiviCommande.findByDateComplet", query = "SELECT s FROM SuiviCommande s WHERE s.dateComplet = :dateComplet")
})
public class SuiviCommande implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Numero")
    private Integer numero;
    @Column(name = "Status", length = 100)
    private String status;
    @Basic(optional = false)
    @Column(name = "Emplacement", length = 100)
    private String emplacement;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "DateCommande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "DateComplet")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateComplet;

    public SuiviCommande()
    {
    }

    public SuiviCommande(Integer numero)
    {
        this.numero = numero;
    }

    public SuiviCommande(Integer numero, String status, String emplacement, Date dateCommande, Date dateComplet)
    {
        this.numero = numero;
        this.status = status;
        this.emplacement = emplacement;
        this.dateCommande = dateCommande;
        this.dateComplet = dateComplet;
    }

    public Integer getNumero()
    {
        return numero;
    }

    public void setNumero(Integer numero)
    {
        this.numero = numero;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getEmplacement()
    {
        return emplacement;
    }

    public void setEmplacement(String emplacement)
    {
        this.emplacement = emplacement;
    }

    public Date getDateCommande()
    {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande)
    {
        this.dateCommande = dateCommande;
    }

    public Date getDateComplet()
    {
        return dateComplet;
    }

    public void setDateComplet(Date dateComplet)
    {
        this.dateComplet = dateComplet;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuiviCommande))
        {
            return false;
        }
        SuiviCommande other = (SuiviCommande) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.nrobillard.modele.SuiviCommande[ numero=" + numero + " ]";
    }
    
}
