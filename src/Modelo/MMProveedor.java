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
public class MMProveedor {

    

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private int id;
    private String buscador;
    private String nombre;
    private String ubicacion;
    private String rfc;
    private String cp;
    
     Modelo.CMConfig con=new Modelo.CMConfig();
    Object[][] dat;
    
    
    public DefaultTableModel llenartabla()
    {
        DefaultTableModel dato=null;
        try
        {
            String[] titulos={"Clave","Nombre","Ubicación","RFC","Código Postal"};
            String consulta;
            consulta = "select * from tblproveedor";
            String[] columnas={"intIdProveedor","vchNombre","vchUbicacion","vchRFC","vchCP"};
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
            String[] titulos={"Clave","Nombre","Ubicación","RFC","Código Postal"};
            String consulta;
            consulta = "select * from tblproveedor  where vchNombre like '"+buscador+"%';";
            String[] columnas={"intIdProveedor","vchNombre","vchUbicacion","vchRFC","vchCP"};
            dat= con.getDatos(consulta, columnas);
            dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
    }
    
    public void guardar()
    {
        try{con.procesos("INSERT INTO tblproveedor(vchNombre,vchUbicacion,vchRFC,vchCP) VALUES ('"+nombre+"','"+ubicacion+"','"+rfc+"','"+cp+"');");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
     public void Eliminar()
    {
        try{con.procesos("delete from tblproveedor where intIdProveedor="+id+";");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
     public void Actualizar()
    {
        try{con.procesos("UPDATE tblproveedor SET vchNombre='"+nombre+"',vchUbicacion='"+ubicacion+"',vchRFC='"+rfc+"',vchCP='"+cp+"' WHERE intIdProveedor="+id+";");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
    
}
