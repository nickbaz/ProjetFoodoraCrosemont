/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele.classes.service;

import com.modele.classes.Client;
import com.modele.classes.Succursalemembre;
import java.util.List;
import javax.ejb.EJB;
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
 * @author utilisateur
 */
@Stateless
@Path("com.modele.classes.client")
public class ClientFacadeREST extends AbstractFacade<Client> {

    @EJB
    private SuccursalemembreFacadeREST succursalemembreFacadeREST;

    @PersistenceContext(unitName = "ProgrammeFidelitePU")
    private EntityManager em;

    public ClientFacadeREST() {
        super(Client.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Client entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Client entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Client find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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
    
    private void addPoints(Integer clientId, int nbrPts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @GET
    @Path("makeTransaction/{costBeforeTaxes}/{succursaleId}/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int makeTransaction(@PathParam("costBeforeTaxes") Double cost, @PathParam("succursale") Integer succursaleId, @PathParam("clientId") Integer clientId) {
        if (succursalemembreFacadeREST.isSuccursaleMembre(succursaleId)) {
            Succursalemembre succMembre = succursalemembreFacadeREST.find(succursaleId);
            double nbrArgent = succMembre.getTauxRemise() * cost;
            int nbrPts = (int) nbrArgent * 100;
            addPoints(clientId, nbrPts);
            return nbrPts;
        }
        return 0;
    }
}
