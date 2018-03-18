/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author sergi
 */
public class MMAcceso {

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    private String usuario;
    private String password;
    private int nivel ;
    
    Modelo.CMConfig con=new Modelo.CMConfig();
    
    public boolean verificausuario()
    {
    boolean x=false;
        if (con.rs!=null) {
            
            try{
            con.consultar("SELECT *  FROM tblusuario WHERE vchNombre='"+usuario+"' AND vchContrasenia='"+password+"' AND intidNivel="+nivel+"");
            
            while(con.rs.next())
            {
            x=true;
            }
            }catch(Exception v)
            {
            }
            
        }
        return x;
    
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
