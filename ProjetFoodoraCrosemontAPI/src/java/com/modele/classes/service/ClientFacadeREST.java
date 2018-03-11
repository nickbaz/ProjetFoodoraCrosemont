/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele.classes.service;

import com.foodoraCrosemont.Utils.ClientSuccursalemembreBuilder;
import com.modele.classes.Client;
import com.modele.classes.ClientSuccursalemembre;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
@Path("com.modele.classes.client")
public class ClientFacadeREST extends AbstractFacade<Client> {

    @EJB
    private SuccursalemembreFacadeREST succursalemembreFacadeREST;
    
    @EJB
    private ClientSuccursalemembreFacadeREST clientSuccursalemembreFacadeREST;
    
    @PersistenceContext(unitName = "ProgrammeFidelitePU")
    private EntityManager em;

    public ClientFacadeREST() {
        super(Client.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Client entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Client entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("getClient/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findClient(@PathParam("id") String id) {
        Client client = super.find(id);
        return "{\"numero\" : \""+client.getNumero()+"\"}";

    }

    @GET
    @Path("getAllClient")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllClient() { 
        StringBuilder json = new StringBuilder();
        json.append("[");
        for(Client client : super.findAll()){
            json.append("{\"numero\" : \""+client.getNumero()+"\"},");
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
    @Path("makeTransaction/{costBeforeTaxes}/{numeroClient}/{succursaleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String makeTransaction(@PathParam("costBeforeTaxes") Double cost, @PathParam("succursaleId") Integer idSuccursale, @PathParam("numeroClient") String numeroClient) {

        ClientSuccursalemembre csm = ClientSuccursalemembreBuilder.BuildClientSuccursalemembre(numeroClient, idSuccursale);
        if(Objects.isNull(csm))
            return "";
        csm = FindClientSuccursalemembreByForeignKey(csm);
        if(Objects.nonNull(csm)){
            double nbrArgent = (csm.getIdSuccursale().getTauxRemise()*100) * cost;
            int nbrPts = (int) nbrArgent;
         
            int ancienSoldePts = csm.getSoldePointsclient();
            int nouveauSoldePts = ancienSoldePts+nbrPts;  

            csm.setSoldePointsclient(nouveauSoldePts);
            csm.setSoldeArgentclient(nouveauSoldePts/100);
            clientSuccursalemembreFacadeREST.edit(csm);
            return("[{\"soldeArgent\" : \""+nouveauSoldePts+"\"}]");

        }

        return "";
    }
    
    @GET
    @Path("getSoldePoints/{numeroClient}/{idSuccursale}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getSoldePoints(@PathParam("numeroClient") String numeroClient, @PathParam("idSuccursale") Integer idSuccursale){
        
        ClientSuccursalemembre csm = ClientSuccursalemembreBuilder.BuildClientSuccursalemembre(numeroClient, idSuccursale);
        csm = FindClientSuccursalemembreByForeignKey(csm);
        
        if(Objects.nonNull(csm))
            return "[{\"soldePoints\" : \""+csm.getSoldePointsclient()+"\"}]";
        else
            return "";
    }
    
    @GET
    @Path("getSoldeArgent/{numeroClient}/{idSuccursale}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getSoldeArgent(@PathParam("numeroClient") String numeroClient, @PathParam("idSuccursale") Integer idSuccursale){
        
        ClientSuccursalemembre csm = ClientSuccursalemembreBuilder.BuildClientSuccursalemembre(numeroClient, idSuccursale);
        csm = FindClientSuccursalemembreByForeignKey(csm);
        
        if(Objects.nonNull(csm))
            return "{\"soldeArgent\" : \""+csm.getSoldeArgentclient()+"\"}";        
        else
            return "";
    }
    
    @GET
    @Path("addPoints/{numeroClient}/{idSuccursale}/{pts}")
    @Produces({MediaType.APPLICATION_JSON})
    public String addPoints(@PathParam("numeroClient") String numeroClient, @PathParam("idSuccursale") Integer idSuccursale, @PathParam("pts") int pts){
        ClientSuccursalemembre csm = ClientSuccursalemembreBuilder.BuildClientSuccursalemembre(numeroClient, idSuccursale);
        csm = FindClientSuccursalemembreByForeignKey(csm);

        if(Objects.nonNull(csm)){            
            int ancienSoldePts = csm.getSoldePointsclient();
            int nouveauSoldePts = ancienSoldePts+pts;  

            csm.setSoldePointsclient(nouveauSoldePts);
            csm.setSoldeArgentclient(nouveauSoldePts/100);
            clientSuccursalemembreFacadeREST.edit(csm);
            return("[{\"soldePoints\" : \""+nouveauSoldePts+"\"}]");
        }
        return("");
    }
    
    @GET
    @Path("usePoints/{numeroClient}/{idSuccursale}")
    @Produces({MediaType.APPLICATION_JSON})
    public String usePoints(@PathParam("numeroClient") String numeroClient, @PathParam("idSuccursale") Integer idSuccursale){
        ClientSuccursalemembre csm = ClientSuccursalemembreBuilder.BuildClientSuccursalemembre(numeroClient, idSuccursale);
        csm = FindClientSuccursalemembreByForeignKey(csm);
        
        double soldeArgent = csm.getSoldeArgentclient();
        csm.setSoldePointsclient(0);
        csm.setSoldeArgentclient(0.00);
        clientSuccursalemembreFacadeREST.edit(csm);
        
        return "[{\"soldeArgent\" : \""+soldeArgent+"\"}]";
    }
    
    public ClientSuccursalemembre FindClientSuccursalemembreByForeignKey(ClientSuccursalemembre csm){
        TypedQuery<ClientSuccursalemembre> query = em.createNamedQuery("ClientSuccursalemembre.findByForeignKey",ClientSuccursalemembre.class);
        query.setParameter("numeroClient", csm.getNumeroClient());
        query.setParameter("idSuccursale", csm.getIdSuccursale());
        return query.getSingleResult();
    }
}
