/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MMEmpleado;
import Vista.jdlgEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class CCEmpleado implements ActionListener, MouseListener,KeyListener{

    public CCEmpleado(MMEmpleado mempleado, jdlgEmpleados vempleado) {
        this.mempleado = mempleado;
        this.vempleado = vempleado;
    }
Modelo.MMEmpleado mempleado;
Vista.jdlgEmpleados vempleado;
Modelo.CMConfig con=new Modelo.CMConfig();
public void form_load()
{   vempleado.jcmbEstado.removeAllItems();
    combo();
    tabla();
     vempleado.jbtnGuardar.addActionListener(this);//se debe inicializar el ó los botones a utilizar dentro de la ventana
    vempleado.jbtnGuardar.setActionCommand("Guardar");
    vempleado.jbtnActualizar.setActionCommand("Actualizar");
    vempleado.jbtnActualizar.addActionListener(this);
    vempleado.jbtnEliminar.addActionListener(this);
    vempleado.jbtnEliminar.setActionCommand("Eliminar");
    vempleado.jbtnSalir.addActionListener(this);//en caso de que es un botón y solo es presionado, por ese motivo se agrega el SetActionListener
    vempleado.jbtnSalir.setActionCommand("Salir");
    vempleado.jtableEmpleado.addMouseListener(this);//se agregará el tipo de accion, dependiendo de quien lo utilice, en caso contrario no podra ejecutarse correctamente
    vempleado.jbtnCancelar.addActionListener(this);
    vempleado.jbtnCancelar.setActionCommand("Cancelar");
     vempleado.jbtnActualizar.setEnabled(false);
     vempleado.jbtnEliminar.setEnabled(false);
     vempleado.jbtnCancelar.setEnabled(false);
     vempleado.jtxtBuscador.addKeyListener(this);
     vempleado.jtxtNombre.requestFocus();
    vempleado.setVisible(true);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        String opc=e.getActionCommand();
        switch(opc)
        {
        case"Guardar":
             
             variables();
           //mensaje que muestra el valor de las variables // JOptionPane.showMessageDialog(null,"1-"+mempleado.getNombre()+", 2-"+mempleado.getApellidos()+", 3-"+mempleado.getFechaNacimiento()+", 4-"+mempleado.getSalario()+", 5-"+mempleado.getEstado());    
             if(vempleado.jtxtNombre.getText().equals("") ||vempleado.jtxtApellidos.getText().equals("")||vempleado.jtxtSalario.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(null,"Falta rellenar un campo *Obligatorio");    
             }
             else
             {
                 if (JOptionPane.showConfirmDialog(null,"Esta a punto de guardar un nuevo registro de empleados, desea continuar?", "Empleados",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                 mempleado.guardar();
                 tabla();
                 vaciarvariables();
                 JOptionPane.showMessageDialog(null,"Salvación de datos Exitosa!!");    
                 //JOptionPane.showMessageDialog(null,"1-"+mempleado.getNombre()+", 2-"+mempleado.getApellidos()+", 3-"+mempleado.getFechaNacimiento()+", 4-"+mempleado.getSalario()+", 5-"+mempleado.getEstado());    
                 //JOptionPane.showMessageDialog(null,"1-"+mempleado.getNombre().trim()+", 2-"+mempleado.getApellidos().trim()+", 3-"+mempleado.getFechaNacimiento().trim()+", 4-"+mempleado.getSalario()+", 5-"+mempleado.getEstado());    
                 }
             }
             
            
            break;
            
            
            case"Eliminar":
                mempleado.Eliminar();
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"Eliminación Exitosa!!");
                vempleado.jbtnGuardar.setEnabled(true);
                vempleado.jbtnActualizar.setEnabled(false);
                vempleado.jbtnEliminar.setEnabled(false);
                vempleado.jbtnCancelar.setEnabled(false);
                break;
                case"Actualizar":
                variables();
                mempleado.Actualizar();
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"Actualización Exitosa!!");    
                vempleado.jbtnGuardar.setEnabled(true);
                vempleado.jbtnActualizar.setEnabled(false);
                vempleado.jbtnEliminar.setEnabled(false);
                vempleado.jbtnCancelar.setEnabled(false);
                    
                    break;
                case"Cancelar":
                vempleado.jbtnGuardar.setEnabled(true);
                vempleado.jbtnActualizar.setEnabled(false);
                vempleado.jbtnEliminar.setEnabled(false);
                vaciarvariables();
                vempleado.jbtnCancelar.setEnabled(false);
                mempleado.setId(0);
                tabla();
                JOptionPane.showMessageDialog(null,"Cancelado por el Usuario");    
                 break;
                 case"Salir":
                     vempleado.dispose();
                     break;
        }
    }
    
    //Todos los metodos que se crearon a partir de la superclase
    public void combo() {
        vempleado.jcmbEstado.setModel(mempleado.cargarCombo("SELECT intIdEstado,vchNombre FROM tblestado","vchNombre","intIdEstado"));
        
    }
    
    public void tabla()
    {
        vempleado.jtableEmpleado.setModel(mempleado.llenartabla());
    }
    
    public void variables()
    {
    
    try {
        mempleado.setFechaNacimiento(mempleado.fechaMySQL(vempleado.jdatechooser));
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Al menos elija una fecha valida", "Error..!!", JOptionPane.ERROR_MESSAGE);

    }

     mempleado.setApellidos(vempleado.jtxtApellidos.getText());
     mempleado.setBusqueda(vempleado.jtxtBuscador.getText());
     mempleado.setEstado(vempleado.jcmbEstado.getSelectedIndex()+1);
     mempleado.setNombre(vempleado.jtxtNombre.getText());
     try{
     mempleado.setSalario(Double.parseDouble(vempleado.jtxtSalario.getText()));
     }catch(Exception x)
     {
         JOptionPane.showMessageDialog(null, "Salario: Debe ser una valor entero o decimal", "Error..!!", JOptionPane.ERROR_MESSAGE);
     }
    }
    
    public void vaciarvariables()
    {
        mempleado.setApellidos("");
        mempleado.setBusqueda("");
        mempleado.setEstado(0);
        mempleado.setNombre("");
        mempleado.setFechaNacimiento("");
        mempleado.setSalario(0);
        vempleado.jtxtApellidos.setText("");
        vempleado.jtxtBuscador.setText("");
        vempleado.jtxtNombre.setText("");
        vempleado.jtxtSalario.setText("");
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        vempleado.jbtnEliminar.setEnabled(true);//Habilito los botones
        vempleado.jbtnActualizar.setEnabled(true);
        vempleado.jbtnCancelar.setEnabled(true);
        int row=vempleado.jtableEmpleado.rowAtPoint(e.getPoint());
        vempleado.jtxtSalario.setText(vempleado.jtableEmpleado.getValueAt(row, 5).toString());
        vempleado.jtxtNombre.setText(vempleado.jtableEmpleado.getValueAt(row, 2).toString());
        vempleado.jtxtApellidos.setText(vempleado.jtableEmpleado.getValueAt(row, 3).toString());
        vempleado.jbtnGuardar.setEnabled(false);
        mempleado.setId(Integer.parseInt(vempleado.jtableEmpleado.getValueAt(row, 0).toString()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
     
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mempleado.setBusqueda(vempleado.jtxtBuscador.getText());
     vempleado.jtableEmpleado.setModel(mempleado.Busquedatabla());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
