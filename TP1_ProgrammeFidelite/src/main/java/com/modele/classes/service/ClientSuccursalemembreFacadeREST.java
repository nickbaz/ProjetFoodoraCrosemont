/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele.classes.service;

import com.foodoraCrosemont.Utils.ClientSuccursalemembreBuilder;
import com.modele.classes.Client;
import com.modele.classes.ClientSuccursalemembre;
import com.modele.classes.Succursalemembre;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nicolas
 */
@Stateless
@Path("clientSuccursaleMembre")
public class ClientSuccursalemembreFacadeREST extends AbstractFacade<ClientSuccursalemembre> {
    
    private SuccursalemembreFacadeREST succursalemembreFacadeREST = null;
    
    private ClientFacadeREST clientFacadeREST = null;
    
    private EntityManager em;

    public ClientSuccursalemembreFacadeREST() {
        super(ClientSuccursalemembre.class);
        em = Persistence.createEntityManagerFactory("com.tp1.programmeFidelite_TP1_ProgrammeFidelite_war_1.0-SNAPSHOTPU").createEntityManager();
        if(succursalemembreFacadeREST == null)
            succursalemembreFacadeREST = new SuccursalemembreFacadeREST();
        if(clientFacadeREST == null)
            clientFacadeREST = new ClientFacadeREST();        
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(ClientSuccursalemembre entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ClientSuccursalemembre entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("find/{numeroClient}/{idSuccursale}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findClientSuccursalemembre(@PathParam("numeroClient") String numeroClient, @PathParam("idSuccursale") Integer idSuccursale) {
        ClientSuccursalemembre csm = ClientSuccursalemembreBuilder.BuildClientSuccursalemembre(numeroClient, idSuccursale);
        csm = clientFacadeREST.FindClientSuccursalemembreByForeignKey(csm);
        
        return "{\"id\" : "+csm.getId()+", \"numero_client\" : \""+csm.getNumeroClient().getNumero()+"\", \"id_succursale\" : "
                + csm.getIdSuccursale().getId()+", \"soldeArgent_client\" : "+csm.getSoldeArgentclient()+", \"soldePoints_client\" : "
                + csm.getSoldePointsclient()+"}";
    }

    @GET
    @Path("findAllClientSuccursale")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllClientSuccursale() {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for(ClientSuccursalemembre csm : super.findAll()){
            json.append("{\"id\" : "+csm.getId()+", \"numero_client\" : \""+csm.getNumeroClient().getNumero()+"\", \"id_succursale\" : "
                + csm.getIdSuccursale().getId()+", \"soldeArgent_client\" : "+csm.getSoldeArgentclient()+", \"soldePoints_client\" : "
                + csm.getSoldePointsclient()+"},");
        }
        json.deleteCharAt(json.length()-1);
        json.append("]");
        return json.toString();        
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("isClientMembre/{idClient}/{idSuccursale}")
    @Produces(MediaType.APPLICATION_JSON)
    public String isClientMembre(@PathParam("idClient") String numeroClient, @PathParam("idSuccursale") Integer idSuccursale){

        ClientSuccursalemembre csm = ClientSuccursalemembreBuilder.BuildClientSuccursalemembre(numeroClient, idSuccursale);

        csm = clientFacadeREST.FindClientSuccursalemembreByForeignKey(csm);
        if(Objects.nonNull(csm))
            return "{\"isClientMembre\" : true}";
        else
        return "";
    }
    
    @GET
    @Path("getSuccursaleByClient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSuccursaleByClient(@PathParam("id") String id) {
        List<ClientSuccursalemembre> listeClientSucc = new ArrayList<ClientSuccursalemembre>();
        List<Succursalemembre> listeSucc = new ArrayList<Succursalemembre>();
        
        listeClientSucc = super.findAll();
        
        for(ClientSuccursalemembre clientSuccMembre : listeClientSucc) {
            if(id.equals(clientSuccMembre.getNumeroClient().getNumero())) {
                listeSucc.add(clientSuccMembre.getIdSuccursale());
            }
        }
        
        StringBuilder stringJson = new StringBuilder();
        stringJson.append("[");
        for(Succursalemembre succursale : listeSucc) {
            stringJson.append("{\"id\" : ");
            stringJson.append(succursale.getId());
            stringJson.append(", \"nom\" : \"");
            stringJson.append(succursale.getNom());
            stringJson.append("\", \"tauxRemise\" : ");
            stringJson.append(succursale.getTauxRemise());
            stringJson.append("},");
        }
        
        stringJson.deleteCharAt(stringJson.length()-1);
        if(stringJson.length() == 0) {
            return "";
        }
        stringJson.append("]");
        
        return stringJson.toString(); 
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
