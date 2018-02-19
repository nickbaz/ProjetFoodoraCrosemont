/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointFidelite.webservices.dao;

import com.pointFidelite.webservices.modeles.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author utilisateur
 */
public class ClientDAO extends DAO<Client> {

    public ClientDAO(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(Client x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Client getClient(String id) {
        PreparedStatement stm = null;
        ResultSet r = null;
        try 
        {
            stm = cnx.prepareStatement("SELECT * FROM client WHERE numero = ? ");
            stm.setString(1, id);
            r = stm.executeQuery();
            if (r.next())
            {
                Client c = new Client();
                c.setIdClient(r.getString("numero"));
                c.setNombreDePoint(r.getLong("soldePoints"));
                c.setSoldeEnArgent(r.getDouble("soldeArgent"));				
                r.close();
                stm.close();
                return c;
            }
        }
        catch (SQLException exp)
        {
        }
        finally
        {
            if (stm!=null)
            try {
                r.close();
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }			
        }
        return null;
    }
  
    @Override
    public boolean update(Client x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Client x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Client> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
