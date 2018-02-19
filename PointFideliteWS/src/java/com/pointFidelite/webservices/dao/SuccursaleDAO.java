/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointFidelite.webservices.dao;

import com.pointFidelite.webservices.modeles.Client;
import com.pointFidelite.webservices.modeles.SuccursaleMembre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author utilisateur
 */
public class SuccursaleDAO extends DAO<SuccursaleMembre>{

    public SuccursaleDAO(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(SuccursaleMembre x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public SuccursaleMembre getSuccursale(int id) {
        PreparedStatement stm = null;
        ResultSet r = null;
        try 
        {
            stm = cnx.prepareStatement("SELECT * FROM succursalemembre WHERE id = ? ");
            stm.setInt(1, id);
            r = stm.executeQuery();
            if (r.next())
            {
                SuccursaleMembre sm = new SuccursaleMembre();
                sm.setIdSuccursale(r.getInt("id"));
                sm.setNomSuccursale(r.getString("nom"));
                sm.setTauxRemise(r.getDouble("tauxRemise"));
                r.close();
                stm.close();
                return sm;
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
    
    public boolean isMembre(int id){
        PreparedStatement stm = null;
        ResultSet r = null;
        try 
        {
            stm = cnx.prepareStatement("SELECT * FROM succursalemembre WHERE id = ? ");
            stm.setInt(1, id);
            r = stm.executeQuery();
            if (r != null)
            {
                return true;
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
        return false;
    }
    
    @Override
    public boolean update(SuccursaleMembre x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(SuccursaleMembre x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuccursaleMembre> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
