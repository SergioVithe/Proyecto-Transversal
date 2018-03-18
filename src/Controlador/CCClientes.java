/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MMClientes;
import Vista.jdlgClientes;
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
public class CCClientes implements ActionListener, MouseListener, KeyListener{

    public CCClientes(MMClientes mcliente, jdlgClientes vcliente) {
        this.mcliente = mcliente;
        this.vcliente = vcliente;
    }
Modelo.MMClientes mcliente;
Vista.jdlgClientes vcliente;

public void form_load(){
    vcliente.jcmbActivo.removeAllItems();
    combo();
    tabla();
vcliente.jbtnActualizar.addActionListener(this);
vcliente.jbtnActualizar.setActionCommand("Actualizar");
vcliente.jbtnCancelar.addActionListener(this);
vcliente.jbtnCancelar.setActionCommand("Cancelar");
vcliente.jbtnEliminar.addActionListener(this);
vcliente.jbtnEliminar.setActionCommand("Eliminar");
vcliente.jbtnGuardar.setActionCommand("Guardar");
vcliente.jbtnGuardar.addActionListener(this);
vcliente.jbtnSalir.setActionCommand("Salir");
vcliente.jbtnSalir.addActionListener(this);
vcliente.jtxtBuscador.addKeyListener(this);
vcliente.jtableCliente.addMouseListener(this);
vcliente.jbtnActualizar.setEnabled(false);
vcliente.jbtnEliminar.setEnabled(false);
vcliente.jbtnCancelar.setEnabled(false);
vcliente.jtxtBuscador.addKeyListener(this);
vcliente.setVisible(true);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        String opc=e.getActionCommand();
         switch(opc)
         {
         case"Cancelar":
             vcliente.jbtnGuardar.setEnabled(true);
                vcliente.jbtnActualizar.setEnabled(false);
                vcliente.jbtnEliminar.setEnabled(false);
                vaciarvariables();
                vcliente.jbtnCancelar.setEnabled(false);
                mcliente.setId(0);
                tabla();
                JOptionPane.showMessageDialog(null,"Cancelado por el Usuario");    
         break;
         case"Eliminar":
              mcliente.Eliminar();
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"Eliminación Exitosa!!");
                vcliente.jbtnGuardar.setEnabled(true);
                vcliente.jbtnActualizar.setEnabled(false);
                vcliente.jbtnEliminar.setEnabled(false);
                vcliente.jbtnCancelar.setEnabled(false);
         break;
         case"Guardar":
            variables();
             if (mcliente.getNombre().equals("") || mcliente.getApellidos().equals("")|| mcliente.getCorreo().equals("")|| mcliente.getDomicilio().equals("")|| mcliente.getTelefono().equals("")||mcliente.getFechanac().equals(""))
             {JOptionPane.showMessageDialog(null,"Falta rellenar un campo *Obligatorio");}
             else
             {
              if (JOptionPane.showConfirmDialog(null,"Esta a punto de guardar un nuevo registro de empleados, desea continuar?", "Empleados",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
              mcliente.guardar();
              tabla();
              vaciarvariables();
              JOptionPane.showMessageDialog(null,"Salvación de datos Exitosa!!");    
             }
             }
         break;
         case"Actualizar":
             variables();
                mcliente.Actualizar();
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"Actualización Exitosa!!");    
                vcliente.jbtnGuardar.setEnabled(true);
                vcliente.jbtnActualizar.setEnabled(false);
                vcliente.jbtnEliminar.setEnabled(false);
                vcliente.jbtnCancelar.setEnabled(false);
         break;
         case"Salir":
             vcliente.dispose();
         break;
         }
    }
    public void combo(){vcliente.jcmbActivo.setModel(mcliente.cargarCombo("SELECT intIdEstado,vchNombre FROM tblestado","vchNombre","intIdEstado"));}
    public void tabla(){vcliente.jtableCliente.setModel(mcliente.llenartabla());}
    
    public void variables(){
    mcliente.setApellidos(vcliente.jtxtApellidos.getText());
    mcliente.setCorreo(vcliente.jtxtCorreo.getText());
    mcliente.setDomicilio(vcliente.jtxtDomicilio.getText());
    mcliente.setEstado(vcliente.jcmbActivo.getSelectedIndex()+1); //Le sumo 1 para el combo
    try{mcliente.setFechanac(mcliente.fechaMySQL(vcliente.jdteFechaNac));}catch(Exception w){JOptionPane.showMessageDialog(vcliente, "Fecha:Formato incorrecto");}
    
    mcliente.setNombre(vcliente.jtxtNombre.getText());
    mcliente.setTelefono(vcliente.jtxtTelefono.getText());}
    
    public void vaciarvariables(){
    mcliente.setApellidos("");
    mcliente.setCorreo("");
    mcliente.setDomicilio("");
    mcliente.setEstado(0);
    mcliente.setFechanac("");
    mcliente.setNombre("");
    mcliente.setTelefono("");}

    @Override
    public void mouseClicked(MouseEvent e) {
        vcliente.jbtnEliminar.setEnabled(true);//Habilito los botones
        vcliente.jbtnActualizar.setEnabled(true);
        vcliente.jbtnCancelar.setEnabled(true);
        vcliente.jbtnGuardar.setEnabled(false);
       vaciarvariables();
        int row=vcliente.jtableCliente.rowAtPoint(e.getPoint());
        vcliente.jtxtNombre.setText(vcliente.jtableCliente.getValueAt(row, 1).toString());
        vcliente.jtxtApellidos.setText(vcliente.jtableCliente.getValueAt(row, 2).toString());
        vcliente.jtxtCorreo.setText(vcliente.jtableCliente.getValueAt(row, 3).toString());
        vcliente.jtxtDomicilio.setText(vcliente.jtableCliente.getValueAt(row,4).toString());
        vcliente.jtxtTelefono.setText(vcliente.jtableCliente.getValueAt(row, 5).toString());
        mcliente.setId(Integer.parseInt(vcliente.jtableCliente.getValueAt(row, 0).toString()));
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
    mcliente.setBuscador(vcliente.jtxtBuscador.getText());
    vcliente.jtableCliente.setModel(mcliente.Busquedatabla());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
