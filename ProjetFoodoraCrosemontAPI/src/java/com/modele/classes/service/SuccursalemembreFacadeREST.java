/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele.classes.service;

import com.modele.classes.Client;
import com.modele.classes.Succursalemembre;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
@Path("com.modele.classes.succursalemembre")
public class SuccursalemembreFacadeREST extends AbstractFacade<Succursalemembre> {

    @PersistenceContext(unitName = "ProgrammeFidelitePU")
    private EntityManager em;

    public SuccursalemembreFacadeREST() {
        super(Succursalemembre.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Succursalemembre entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Succursalemembre entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findSuccursalemembre(@PathParam("id") Integer id) {
        Succursalemembre succMembre = super.find(id);
        
        return "{\"id\" : "+succMembre.getId()+",\"nom\" : \""+succMembre.getNom()+"\",\"tauxRemise\" : "+succMembre.getTauxRemise()+"}";
    }

    @GET
    @Path("getAllSuccursalemembre")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllSuccursalemembre() {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for(Succursalemembre succMembre : super.findAll()){
            json.append("{\"id\" : "+succMembre.getId()+",\"nom\" : \""+succMembre.getNom()+"\",\"tauxRemise\" : "+succMembre.getTauxRemise()+"},");
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("isSuccursaleMembre/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String isSuccursaleMembre(@PathParam("id") Integer id) {
        if(Objects.nonNull(find(id))){
            return "{\"isMembre\" : true}";
        } else {
            return "";
        }
    }

    @GET
    @Path("getTauxRemise/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTauxRemise(@PathParam("id") Integer id) {
        Succursalemembre succMembre = super.find(id);
        return "{\"tauxRemise\" : \""+succMembre.getTauxRemise()+"\"}";
    }
    
}
