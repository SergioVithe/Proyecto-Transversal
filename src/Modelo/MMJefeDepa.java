/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.toedter.calendar.JDateChooser;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sergi
 */
public class MMJefeDepa {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBuscador() {
        return buscador;
    }

    public void setBuscador(String buscador) {
        this.buscador = buscador;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String nombre;
    private String buscador;
    private String Apellidos;
    private String fecha;

    
    private int id;
    
    Modelo.CMConfig con=new Modelo.CMConfig();
    Object[][] dat;
    
    
    public DefaultTableModel llenartabla()
    {
        DefaultTableModel dato=null;
        try
        {
            String[] titulos={"Código","Nombre","Apellidos","Fecha Nacimiento"};
            String consulta;
            consulta = "SELECT * FROM VWJefeDepa";
            String[] columnas={"intidJefe","vchNombre","vchApellidos","dteFechaNac"};
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
            String[] titulos={"Código","Nombre","Apellidos","Fecha Nacimiento"};
            String consulta;
            consulta = "CALL SPSearchJefeDepartamento('"+buscador+"%')";
            String[] columnas={"intidJefe","vchNombre","vchApellidos","dteFechaNac"};
            dat= con.getDatos(consulta, columnas);
            dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
    }
    
    public void guardar()
    {
        try{con.procesos("CALL SPInsertJefeDepartamento('"+nombre+"','"+Apellidos+"','"+fecha+"');");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
     public void Eliminar()
    {
        try{con.procesos("CALL SPDeleteJefeDepartemento("+id+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
     public void Actualizar()
    {
        
        try{con.procesos("CALL SPUpdateJefeDepartamento('"+nombre+"','"+Apellidos+"','"+fecha+"',"+id+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
     
     public  String fechaMySQL(JDateChooser miJDate){ 

        DecimalFormat sdf = new DecimalFormat("00"); 
        int anio = miJDate.getCalendar().get(Calendar. YEAR); 
        int mes = miJDate.getCalendar().get(Calendar. MONTH) + 1; 
        int dia = miJDate.getCalendar(). get(Calendar. DAY_OF_MONTH); 

        return anio+"-"+sdf.format(mes)+"-"+sdf.format(dia); 
        }
    
    
    
    
}
