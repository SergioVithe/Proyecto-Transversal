/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MMProveedor;
import Vista.jdlgProveedor;
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
public class CCProveedor implements ActionListener, MouseListener, KeyListener{

    public CCProveedor(MMProveedor mproveedor, jdlgProveedor vproveedor) {
        this.mproveedor = mproveedor;
        this.vproveedor = vproveedor;
    }
Modelo.MMProveedor mproveedor;
Vista.jdlgProveedor vproveedor;

public void form_load()
{
    tabla();
    vproveedor.jbtnActualizar.addActionListener(this);
    vproveedor.jbtnActualizar.setActionCommand("Actualizar");
    vproveedor.jbtnCancelar.addActionListener(this);
    vproveedor.jbtnCancelar.setActionCommand("Cancelar");
    vproveedor.jbtnEliminar.addActionListener(this);
    vproveedor.jbtnEliminar.setActionCommand("Eliminar");
    vproveedor.jbtnGuardar.addActionListener(this);
    vproveedor.jbtnGuardar.setActionCommand("Guardar");
    vproveedor.jbtnSalir.addActionListener(this);
    vproveedor.jbtnSalir.setActionCommand("Salir");
    vproveedor.jtxtBuscador.addKeyListener(this);
    vproveedor.jtableProveedor.addMouseListener(this);
    vproveedor.jtxtNombre.requestFocus();
    vproveedor.jbtnActualizar.setEnabled(false);
    vproveedor.jbtnEliminar.setEnabled(false);
    vproveedor.jbtnCancelar.setEnabled(false);
    vproveedor.setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        String opc=e.getActionCommand();
        
        switch(opc)
        {
        case"Guardar":
            variables();
            if (mproveedor.getNombre().equals("")||mproveedor.getRfc().equals("")||mproveedor.getUbicacion().equals("")||mproveedor.getCp().equals("")) {
                JOptionPane.showMessageDialog(vproveedor, "Campos requeridos");
            }else
                
            {
            if (JOptionPane.showConfirmDialog(null,"Deseas agregar un nuevo proveedor", "Proveedor",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                variables();
                mproveedor.guardar();
                JOptionPane.showMessageDialog(vproveedor, "Guardado con exito!!");
                vaciarvariables();
                tabla();
             
             }
            
            }
             
            break;
        case"Actualizar":
            if (JOptionPane.showConfirmDialog(null,"Desea guardar los cambios", "Proveedor",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                variables();
                mproveedor.Actualizar();
                JOptionPane.showMessageDialog(vproveedor, "Actualizado con exito!!");
                vaciarvariables();
                tabla();
                
                vproveedor.jbtnGuardar.setEnabled(true);
                vproveedor.jbtnActualizar.setEnabled(false);
                vproveedor.jbtnEliminar.setEnabled(false);
                vproveedor.jbtnCancelar.setEnabled(false);
            }
            break;
        case"Eliminar":
            if (JOptionPane.showConfirmDialog(null,"Desea eliminar un proveedor", "Proveedor",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                variables();
                mproveedor.Eliminar();
                JOptionPane.showMessageDialog(vproveedor, "Eliminado con exito!!");
                vaciarvariables();
                tabla();
                vproveedor.jbtnGuardar.setEnabled(true);
                vproveedor.jbtnActualizar.setEnabled(false);
                vproveedor.jbtnEliminar.setEnabled(false);
                vproveedor.jbtnCancelar.setEnabled(false);
            }
            break;
        case"Cancelar":
            vproveedor.jbtnGuardar.setEnabled(true);
            vproveedor.jbtnActualizar.setEnabled(false);
            vproveedor.jbtnEliminar.setEnabled(false);
            vaciarvariables();
            vproveedor.jbtnCancelar.setEnabled(false);
            mproveedor.setId(0);
            tabla();//Lo mando a llamar, por si se realizo una busqueda, para que vuelva a la normalidad
            JOptionPane.showMessageDialog(vproveedor, "Cancelado por el usuario");
            break;
        case"Salir":
            vproveedor.dispose();
            break;
            
        }
    }
    public void vaciarvariables()
    {
    vproveedor.jtxtNombre.setText("");
    vproveedor.jtxtBuscador.setText("");
    vproveedor.jtxtCP.setText("");
    vproveedor.jtxtRFC.setText("");
    vproveedor.jtxtUbicacion.setText("");
    }
    
    public void variables()
    {
    mproveedor.setNombre(vproveedor.jtxtNombre.getText());
    mproveedor.setUbicacion(vproveedor.jtxtUbicacion.getText());
    mproveedor.setRfc(vproveedor.jtxtRFC.getText());
    mproveedor.setCp(vproveedor.jtxtCP.getText());
    }
    
    public void tabla()
    {
    vproveedor.jtableProveedor.setModel(mproveedor.llenartabla());
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        vproveedor.jbtnEliminar.setEnabled(true);//Habilito los botones
        vproveedor.jbtnActualizar.setEnabled(true);
        vproveedor.jbtnCancelar.setEnabled(true);
        vproveedor.jbtnGuardar.setEnabled(false);
         int row=vproveedor.jtableProveedor.rowAtPoint(e.getPoint());
        vproveedor.jtxtNombre.setText(vproveedor.jtableProveedor.getValueAt(row, 1).toString());
        vproveedor.jtxtCP.setText(vproveedor.jtableProveedor.getValueAt(row, 4).toString());
        vproveedor.jtxtRFC.setText(vproveedor.jtableProveedor.getValueAt(row, 3).toString());
        vproveedor.jtxtUbicacion.setText(vproveedor.jtableProveedor.getValueAt(row, 2).toString());
        mproveedor.setId(Integer.parseInt(vproveedor.jtableProveedor.getValueAt(row, 0).toString()));
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
        mproveedor.setBuscador(vproveedor.jtxtBuscador.getText());
        vproveedor.jtableProveedor.setModel(mproveedor.Busquedatabla());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
