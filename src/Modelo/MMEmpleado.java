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
public class MMEmpleado {

   

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    private int id;
    private String busqueda;
    private String fecha;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private double salario;
    private int estado;

    
    
     Modelo.CMConfig con=new Modelo.CMConfig();
    Object[][] dat;
    
    
    public DefaultTableModel llenartabla()
    {
        DefaultTableModel dato=null;
        try
        {
            String[] titulos={"Clave","Fecha Registro","Nombre","Apellidos","Fecha Nacimiento","Salario","Estado"};
            String consulta;
            consulta = "SELECT * FROM VWEmpleados;";
            String[] columnas={"intIdEmpleado","dteFechaRegistro","vchNombre","vchApellidos","dteFechaNac","dbeSalario","intEstado"};
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
            String[] titulos={"Clave","Fecha de Registro","Nombre","Apellidos","Salario","Estado"};
            String consulta;
            consulta = "CALL SPSearchEmpleado('"+busqueda+"%');";
            String[] columnas={"intIdEmpleado","dteFechaRegistro","vchNombre","vchApellidos","dteFechaNac","dbeSalario","intEstado"};
            dat= con.getDatos(consulta, columnas);
            dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
    }
    
    public void guardar()
    {String a="CALL SPInsertEmpleado('"+nombre+"','"+apellidos+"','"+fechaNacimiento+"',"+salario+","+estado+");";
        try{con.procesos(a);
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
    
     public void Eliminar()
    {
        try{con.procesos("CALL SPDeleteEmpleado("+id+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
    }
     
     public void Actualizar()
    {
        try{con.procesos("CALL SPUpdateEmpleado('"+nombre+"','"+apellidos+"','"+fechaNacimiento+"',"+salario+","+estado+","+id+");");
        }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error en el metodo:"+e);
                }
    
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


        public  String fechaMySQL(JDateChooser miJDate){ 

        DecimalFormat sdf = new DecimalFormat("00"); 
        int anio = miJDate.getCalendar().get(Calendar. YEAR); 
        int mes = miJDate.getCalendar().get(Calendar. MONTH) + 1; 
        int dia = miJDate.getCalendar(). get(Calendar. DAY_OF_MONTH); 

        return anio+"-"+sdf.format(mes)+"-"+sdf.format(dia); 
        }
    
    
}
