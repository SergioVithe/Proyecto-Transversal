/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.toedter.calendar.JDateChooser;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sergi
 */
public class MMClientes {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getBuscador() {
        return buscador;
    }

    public void setBuscador(String buscador) {
        this.buscador = buscador;
    }
    private String buscador;
    private String nombre;
    private int id;
    private String apellidos;
    private String domicilio;
    private String correo;
    private String fechanac="";
    private String telefono;
    private int estado;

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }
    private double imc;
    
     Modelo.CMConfig con=new Modelo.CMConfig();
    Object[][] dat;
    
    
    public DefaultTableModel llenartabla()
    {
        DefaultTableModel dato=null;
        try
        {
            String[] titulos={"Código","Nombre","Apellidos","Domicilio","Correo","Telefono","Fecha Nacimiento","IMC","Estado"};
            String consulta;
            consulta = "SELECT * FROM VWCliente";
            String[] columnas={"intIdCliente","vchNombre","vchApellidos","vchDomicilio","vchCorreo","vchTelefono","dteFechaNac","dbleIMC","intEstado"};
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
            String[] titulos={"Código","Nombre","Apellidos","Domicilio","Correo","Telefono","Fecha Nacimiento","Estado"};
            String consulta;
            consulta = "CALL SPSearchCliente('"+buscador+"%');";
            String[] columnas={"intIdCliente","vchNombre","vchApellidos","vchDomicilio","vchCorreo","vchTelefono","dteFechaNac","intEstado"};
            dat= con.getDatos(consulta, columnas);
            dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
    }
    
    public void guardar()
    {
        
        try{con.procesos("CALL SPInsertCliente('"+nombre+"','"+apellidos+"','"+domicilio+"','"+correo+"','"+telefono+"','"+fechanac+"',"+estado+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
     public void Eliminar()
    {
        try{con.procesos("CALL SPDeleteCliente("+id+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
     public void Actualizar()
    {
        try{con.procesos("CALL SPUpdateCliente('"+nombre+"','"+apellidos+"','"+domicilio+"','"+correo+"','"+telefono+"','"+fechanac+"',"+estado+","+id+");");
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
     
      public DefaultComboBoxModel cargarCombo(String query, String muestra, String selecciona) {
        DefaultComboBoxModel Combo1 = new DefaultComboBoxModel();
        try {
            //con..Consulta(query);
            con.consultar(query);
            while (con.rs.next()) {
                Combo1.addElement(new  CMItem(con.rs.getInt(selecciona),con.rs.getString(muestra)));
         //(CMConexion.resultado.getInt(selecciona),CMConexion.resultado.getString(muestra)));
            }
        } catch(SQLException ex) {}
      return Combo1;
    }
    
    
}
