/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sergi
 */
public class MMModoPago {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuscador() {
        return buscador;
    }

    public void setBuscador(String buscador) {
        this.buscador = buscador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    private int id;
    private String buscador;
    private String nombre;
    private String descripcion;
    
    
    Modelo.CMConfig con=new Modelo.CMConfig();
    Object[][] dat;
    
    
    public DefaultTableModel llenartabla()
    {
        DefaultTableModel dato=null;
        try
        {
            String[] titulos={"CÓDIGO","NOMBRE","Detalles"};
            String consulta;
            consulta = "select * from tblmodopago";
            String[] columnas={"intIdModoPago","vchNombre","vchOtroDetalle"};
            dat= con.getDatos(consulta, columnas);
            dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
    }
    
    public DefaultTableModel Busquedatabla()
    {
        DefaultTableModel dato=null;
        try
        {
            String[] titulos={"Código","Nombre","Detalles"};
            String consulta;
            consulta = "select * from tblmodopago where vchNombre like'"+buscador+"%'";
            String[] columnas={"intIdModoPago","vchNombre","vchOtroDetalle"};
            dat= con.getDatos(consulta, columnas);
            dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
    }
    
    public void guardar()
    {
        try{con.procesos("INSERT INTO tblmodopago( vchNombre,vchOtroDetalle) VALUES('"+nombre+"','"+descripcion+"');");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
     public void Eliminar()
    {
        try{con.procesos("DELETE FROM tblmodopago WHERE intIdModoPago="+id+";");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
     public void Actualizar()
    {
        try{con.procesos("UPDATE tblmodopago SET vchNombre='"+nombre+"', vchOtroDetalle='"+descripcion+"' WHERE intIdModoPago="+id+";");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
    
}
