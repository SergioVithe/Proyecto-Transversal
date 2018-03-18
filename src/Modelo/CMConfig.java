package Modelo;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;
public class CMConfig 
{
 
    private String usuario="root";
    private String password="";
    private String url="jdbc:mysql://localhost:3306/bdService";//Para acceder a los datos de la tabla, despues de la diagonal se escribe el nombre de la base de datos
    public Connection con;
    public Statement stmt;
    ResultSet rs;
    public ResultSetMetaData rsMeta;
    PreparedStatement preparedstament;
    
    public CMConfig()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");// crargamos el driver la poder conecctarse al manejador de la base
            con =DriverManager.getConnection(url,usuario,password); //Agregamos el url del  servodor y el usuario y password para la conexion
            //JOptionPane.showMessageDialog(null,"conexion exitosa");                
        }
            catch(SQLException e)
            {
                 JOptionPane.showMessageDialog(null, "ERROR CONSULTA " + e.getMessage(), "DATAGRID", JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(null,e.toString());
            }
            catch(ClassNotFoundException ex){}
    }
    
    
    
   public ResultSet consultar(String sql)//Hacer consultas
    {
       try 
       {
            con =DriverManager.getConnection(url,usuario,password); //Se conecta a la base  de datos
            stmt = con.createStatement(); ///creamos la  sesion para el proceso
            rs = stmt.executeQuery(sql);  ///se almacena Resulset... del proceso que se ejecutara
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR CONSULTA " + ex.getMessage(), "DATAGRID", JOptionPane.INFORMATION_MESSAGE);
        }
        return rs; //se retorna el valor guardado en resultset
    }
    
    
    
    
    public void procesos(String sql) throws Exception
    {
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de proceso: "+ ex);
           //System.out.println("Error de proceso: "+ ex);
        }     
    }
    
    public Object [][] getDatos(String comand2,String[] colu) {
      //int registros = 0;
      Object[][] data= new Object[0][0];
          int filas=0;
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
          stmt = con.createStatement();
          rs = stmt.executeQuery(comand2);
         while(rs.next()){
             filas++;
         }
          rs.close();
          stmt = con.createStatement();
          rs = stmt.executeQuery(comand2);
          
          data= new Object[filas][colu.length];
         int i = 0,y;
         while(rs.next()){
             for(y=0;y<colu.length;y++)
             {
                 data[i][y] = rs.getString(colu[y]);
             }
            i++;
         }
         rs.close();
         //return data;
          }catch(SQLException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }   
    
    //Metodo para el llendo del combobox    
    public String[][] LlenarCombos(String cons)
     {
        String [][] datoscombo = new String [0][0];
        int rows=0, cols, f,c;
        try
        {
           stmt=con.createStatement();
          rs = stmt.executeQuery(cons);
          
            while(rs.next())
            {            
                rows++;
            }
            rs.beforeFirst();
            rsMeta = rs.getMetaData();
            cols = rsMeta.getColumnCount();
            datoscombo = new String[rows][cols];
            f=0;
            while(rs.next())
            {
                for(c=0;c<cols;c++)
                {
                    datoscombo[f][c]=rs.getString(c+1);
                }
                f++;
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return datoscombo;
    }
    
    public int ultimoId(String consulta) throws ClassNotFoundException, SQLException{
    int id=0;
    try 
    {
        stmt = con.createStatement();
        int registro=0;
        rs = stmt.executeQuery(consulta);
                
        while(rs.next())
        {
        registro++;
        }
        rs.close();
        rs = stmt.executeQuery(consulta);
               
                //Mostramos los resultados de la consulta
        int i=0;
        while(rs.next())
        {
            id=Integer.parseInt(rs.getString(1));
            i++;
        }
              
        } catch (SQLException | NullPointerException ex) {
        System.out.println(ex.getMessage());
           
        }
        catch (NumberFormatException e) {
        }
        return id;
     }
    
    
}

