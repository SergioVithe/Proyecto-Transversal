/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MMJefeDepa;
import Vista.jdlgJefeDep;
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
public class CCJefeDepa implements ActionListener, MouseListener,KeyListener{

    public CCJefeDepa(MMJefeDepa mjefedepa, jdlgJefeDep vjefedepa) {
        this.mjefedepa = mjefedepa;
        this.vjefedepa = vjefedepa;
    }
Modelo.MMJefeDepa mjefedepa;
Vista.jdlgJefeDep vjefedepa;


public void form_load()
{
    tabla();
    vjefedepa.jbtnGuardar.addActionListener(this);
    vjefedepa.jbtnGuardar.setActionCommand("Guardar");
    vjefedepa.jtableJefe.addMouseListener(this);
    vjefedepa.jbtnActualizar.addActionListener(this);
    vjefedepa.jbtnActualizar.setActionCommand("Actualizar");
    vjefedepa.jbtnEliminar.addActionListener(this);
    vjefedepa.jbtnEliminar.setActionCommand("Eliminar");
    vjefedepa.jbtnSalir.addActionListener(this);
    vjefedepa.jbtnSalir.setActionCommand("Salir");
    vjefedepa.jtxtBuscador.addKeyListener(this);
    vjefedepa.jbtnCancelar.addActionListener(this);
    vjefedepa.jbtnCancelar.setActionCommand("Cancelar");
    vjefedepa.jbtnActualizar.setEnabled(false);
     vjefedepa.jbtnEliminar.setEnabled(false);
     vjefedepa.jbtnCancelar.setEnabled(false);
    vjefedepa.jtxtNombre.requestFocus();
    vjefedepa.setVisible(true);
    
    
}
    @Override
    public void actionPerformed(ActionEvent e) {//botones
        String opc=e.getActionCommand();
        
        switch(opc)
        {
            
        case"Guardar":
            variables();
            if (mjefedepa.getApellidos().equals("")||mjefedepa.getFecha().equals("")||mjefedepa.getNombre().equals("") ) {
                JOptionPane.showMessageDialog(vjefedepa, "Falta un ingresar datos");
            }else
            {
                
            mjefedepa.guardar();
            JOptionPane.showMessageDialog(vjefedepa, "Guardado con exito!!");
            vaciarvariables();
            tabla();
            }
            
        break;
        case"Actualizar":
            variables();
            if (mjefedepa.getApellidos().equals("")||mjefedepa.getFecha().equals("")||mjefedepa.getNombre().equals("") ) {
              JOptionPane.showMessageDialog(vjefedepa, "Falta un ingresar datos");
            
            }else
            {
                mjefedepa.Actualizar();
            JOptionPane.showMessageDialog(vjefedepa, "Actualizado con exito!!");
            vaciarvariables();
            tabla();
            vjefedepa.jbtnGuardar.setEnabled(true);
            vjefedepa.jbtnActualizar.setEnabled(false);
            vjefedepa.jbtnEliminar.setEnabled(false);
            vjefedepa.jbtnCancelar.setEnabled(false);
            }
        break;
        case"Eliminar":
            mjefedepa.Eliminar();
            JOptionPane.showMessageDialog(vjefedepa, "Registro eliminado con exito!!");
            vaciarvariables();
            tabla();
            vjefedepa.jbtnGuardar.setEnabled(true);
            vjefedepa.jbtnActualizar.setEnabled(false);
            vjefedepa.jbtnEliminar.setEnabled(false);
            vjefedepa.jbtnCancelar.setEnabled(false);
        break;
        case"Salir":
            vjefedepa.dispose();
        break;
        case "Cancelar":
            vjefedepa.jbtnGuardar.setEnabled(true);
            vjefedepa.jbtnActualizar.setEnabled(false);
            vjefedepa.jbtnEliminar.setEnabled(false);
            vaciarvariables();
            vjefedepa.jbtnCancelar.setEnabled(false);
            mjefedepa.setId(0);
            tabla();//Lo mando a llamar, por si se realizo una busqueda, para que vuelva a la normalidad
            JOptionPane.showMessageDialog(vjefedepa, "Cancelado por el usuario");
        break;
        }
        
        
    }
    
    public void tabla(){
    
    vjefedepa.jtableJefe.setModel(mjefedepa.llenartabla());
    }
    
    public void variables()
    {
    mjefedepa.setNombre(vjefedepa.jtxtNombre.getText());
    mjefedepa.setApellidos(vjefedepa.jtxtApellidos.getText());
    try {
        mjefedepa.setFecha(mjefedepa.fechaMySQL(vjefedepa.jdteFecha));
    }
    catch(Exception x)
    {
        JOptionPane.showMessageDialog(vjefedepa, "Formato de fecha invalido, Ingrese una fecha valida");
    }
    
    }
    
    public void vaciarvariables(){
    vjefedepa.jtxtNombre.setText("");
    vjefedepa.jtxtApellidos.setText("");
    }
    
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        vjefedepa.jbtnEliminar.setEnabled(true);//Habilito los botones
        vjefedepa.jbtnActualizar.setEnabled(true);
        vjefedepa.jbtnCancelar.setEnabled(true);
        vjefedepa.jbtnGuardar.setEnabled(false);
        int row=vjefedepa.jtableJefe.rowAtPoint(e.getPoint());
        vjefedepa.jtxtNombre.setText(vjefedepa.jtableJefe.getValueAt(row, 1).toString());
        vjefedepa.jtxtApellidos.setText(vjefedepa.jtableJefe.getValueAt(row, 2).toString());
        mjefedepa.setId(Integer.parseInt(vjefedepa.jtableJefe.getValueAt(row, 0).toString()));

     
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
        mjefedepa.setBuscador(vjefedepa.jtxtBuscador.getText());
        vjefedepa.jtableJefe.setModel(mjefedepa.Busquedatabla());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
