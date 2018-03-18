/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MMModoPago;
import Vista.jdlgModoPago;
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
public class CCModoPago implements ActionListener, MouseListener, KeyListener{

    public CCModoPago(MMModoPago mmodopago, jdlgModoPago vmodopago) {
        this.mmodopago = mmodopago;
        this.vmodopago = vmodopago;
    }
        Modelo.MMModoPago mmodopago;
        Vista.jdlgModoPago vmodopago;

        public void form_load()
        {
            tabla();
        vmodopago.jbtnSalir.addActionListener(this);
        vmodopago.jbtnSalir.setActionCommand("Salir");
        vmodopago.jbtnActualizar.setActionCommand("Actualizar");
        vmodopago.jbtnActualizar.addActionListener(this);
        vmodopago.jbtnCancelar.setActionCommand("Cancelar");
        vmodopago.jbtnEliminar.setActionCommand("Eliminar");
        vmodopago.jbtnEliminar.addActionListener(this);
        vmodopago.jbtnGuardar.addActionListener(this);
        vmodopago.jbtnGuardar.setActionCommand("Guardar");
        vmodopago.jbtnActualizar.setEnabled(false);
     vmodopago.jbtnEliminar.setEnabled(false);
     vmodopago.jbtnCancelar.setEnabled(false);
     vmodopago.jtxtBuscador.addKeyListener(this);
     vmodopago.jtableModopago.addMouseListener(this);
     vmodopago.jtxtBuscador.addKeyListener(this);
     vmodopago.jtxtNombre.requestFocus();
        vmodopago.setVisible(true);

        }


    @Override
    public void actionPerformed(ActionEvent e) {
            String opc=e.getActionCommand();
            
            switch(opc)
            {
            case"Guardar":
                if (JOptionPane.showConfirmDialog(null,"Guardar registro", "Desea continuar?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                variables();
                if (mmodopago.getNombre().trim().equals("")||mmodopago.getDescripcion().trim().equals("")) {
                JOptionPane.showMessageDialog(null,"Falto rellenar datos");    
                }
                else{
                 mmodopago.guardar();
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"Registro guardado con exito!!");
                }
                }
            break;
            case"Eliminar":
                 mmodopago.Eliminar();//UTILIZO LA VARIABLE DEL ID ANTERIORMENTE GUARDADO PARA PODER ELIMINAR UN REGISTRO SIN TENER QUE TOMARLO DE UN LABEL
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"Registro eliminado con exito!!");
                vmodopago.jbtnGuardar.setEnabled(true);
                vmodopago.jbtnActualizar.setEnabled(false);
                vmodopago.jbtnEliminar.setEnabled(false);
                vmodopago.jbtnCancelar.setEnabled(false);
            break;
            case"Actualizar":
                variables();
                mmodopago.Actualizar();
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"Registro actualizado con exito!!");
                vmodopago.jbtnGuardar.setEnabled(true);
                vmodopago.jbtnActualizar.setEnabled(false);
                vmodopago.jbtnEliminar.setEnabled(false);
                vmodopago.jbtnCancelar.setEnabled(false);
            break;
            case"Cancelar":
            vmodopago.jbtnGuardar.setEnabled(true);
            vmodopago.jbtnActualizar.setEnabled(false);
            vmodopago.jbtnEliminar.setEnabled(false);
            vaciarvariables();
            vmodopago.jbtnCancelar.setEnabled(false);
            mmodopago.setId(0);
            tabla();//Lo mando a llamar, por si se realizo una busqueda, para que vuelva a la normalidad
            break;
            case"Salir":
                vmodopago.dispose();
            break;          
            }
    }
    
    public void tabla()
    {
    vmodopago.jtableModopago.setModel(mmodopago.llenartabla());
    }
    
    public void variables()
    {
    mmodopago.setNombre(vmodopago.jtxtNombre.getText());
    mmodopago.setDescripcion(vmodopago.jtxtDescripcion.getText());
    }
    public void vaciarvariables()
    {
    vmodopago.jtxtNombre.setText("");
    vmodopago.jtxtDescripcion.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        vmodopago.jbtnEliminar.setEnabled(true);//Habilito los botones
        vmodopago.jbtnActualizar.setEnabled(true);
        vmodopago.jbtnCancelar.setEnabled(true);
        vmodopago.jbtnGuardar.setEnabled(false);
        vaciarvariables();
        int row=vmodopago.jtableModopago.rowAtPoint(e.getPoint());
        vmodopago.jtxtNombre.setText(vmodopago.jtableModopago.getValueAt(row, 1).toString());
        vmodopago.jtxtDescripcion.setText(vmodopago.jtableModopago.getValueAt(row, 2).toString());
        mmodopago.setId(Integer.parseInt(vmodopago.jtableModopago.getValueAt(row, 0).toString()));
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
      mmodopago.setBuscador(vmodopago.jtxtBuscador.getText());
      vmodopago.jtableModopago.setModel(mmodopago.Busquedatabla());
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }
    
}
