/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Seres;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Estudiante
 */
public class SeresCtrl {
    private final Connection conn;

    public SeresCtrl() {
        this.conn = new Conexion().getConn();
    }
    
    public void llenarCombo(JComboBox<Seres> comboSeres){
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM seresvivos");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {    
                Seres obj = new Seres();
                comboSeres.addItem(new Seres(rs.getInt(1), rs.getString(2), rs.getString(3), obj.getCodi_refe_sere()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(this.conn != null){
                    if(!this.conn.isClosed()){
                        this.conn.close();
                    }
                }
            } catch (SQLException E) {
                System.err.println("Error al cerrar la conexion");
            }
        }
    }
    
    public List<Seres> consTodo(){
        List<Seres> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT nomb_sere, desc_sere, codi_refe_sere FROM seresvivos");
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                Seres obj = new Seres();
                resp.add(new Seres(rs.getInt(1), rs.getString(2), rs.getString(3), obj.getCodi_refe_sere()));
            }
        } catch(SQLException ex)
        {
            System.err.println("Error al consultar Equipos: " + ex.getMessage());
        }
        finally{
            try {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return resp;
    }
    
     public boolean guardar(String nom_seres, String desc_seres, String referencias){
        boolean resp = false;
        Seres obj = new Seres();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO seresvivos VALUES(NULL, ?, ?, ?)");
            cmd.setString(1, nom_seres);
            cmd.setString(2, desc_seres);
            cmd.setString(3, referencias);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(this.conn != null){
                    if(!this.conn.isClosed()){
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return resp;
    }
     
      public boolean eliminar(int codSeres){
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM seresvivos WHERE codi_sere = ?");
            cmd.setInt(1, codSeres);
            cmd.executeUpdate();
            resp=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(this.conn != null){
                    if(!this.conn.isClosed()){
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        
        return resp;
    }
      
      public boolean editar(String nombreSeres, String desSeres, Seres refeSere){
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE seresvivos SET nomb_sere = ?, desc_sere= ? WHERE codi_sere = ?");
            cmd.setString(1, nombreSeres);
            cmd.setString(2, desSeres);
            cmd.setInt(3, 0);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(this.conn != null){
                    if(!this.conn.isClosed()){
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        
        return resp;
    }
}
