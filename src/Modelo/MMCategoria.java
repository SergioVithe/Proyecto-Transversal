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
public class MMCategoria {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusqueda() {
        return Busqueda;
    }

    public void setBusqueda(String Busqueda) {
        this.Busqueda = Busqueda;
    }

    
    private String nombre;
    private String descripcion;
    private int id;
    private String Busqueda;
  
    
    
    Modelo.CMConfig con=new Modelo.CMConfig();
    Object[][] dat;
    
    
    public DefaultTableModel llenartabla()
    {
        DefaultTableModel dato=null;
        try
        {
            String[] titulos={"CÓDIGO","NOMBRE","DESCRIPCIÓN"};
            String consulta;
            consulta = "SELECT * FROM VWCategoria";
            String[] columnas={"intIdCategoria","vchNombre","vchDescripcion"};
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
            String[] titulos={"CÓDIGO","NOMBRE","DESCRIPCIÓN"};
            String consulta;
            consulta = "CALL SPSearchCategoria('"+Busqueda+"%');";
            String[] columnas={"intIdCategoria","vchNombre","vchDescripcion"};
            dat= con.getDatos(consulta, columnas);
            dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
    }
    
    public void guardar()
    {
        try{con.procesos("CALL SPInsertCategoria('"+nombre+"','"+descripcion+"');");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
     public void Eliminar()
    {
        try{con.procesos("CALL SPDeleteCategoria("+id+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
     public void Actualizar()
    {
        try{con.procesos("CALL SPUpdateCategoria('"+nombre+"','"+descripcion+"',"+id+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
}
