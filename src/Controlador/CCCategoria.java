/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MMCategoria;
import Vista.jdlgCategorias;
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
public class CCCategoria implements ActionListener, MouseListener, KeyListener {//Para utilizar las implementaciones de los objetos dentro de la ventana, se deberan trabajar con ActionListener, MouseListener o cualquier otra más

    public CCCategoria(MMCategoria mcategoria, jdlgCategorias vcategoria) {
        this.mcategoria = mcategoria;
        this.vcategoria = vcategoria;
    }
    Modelo.MMCategoria mcategoria;
    Vista.jdlgCategorias vcategoria;
    
    
    
    public void form_Load()
    {
    
    tabla();//Antes que carge la ventana, se debe hacer una consulta a la bd para llenar la tabla
    vcategoria.jbtnGuardar.addActionListener(this);//se debe inicializar el ó los botones a utilizar dentro de la ventana
    vcategoria.jbtnGuardar.setActionCommand("Guardar");
    vcategoria.jbtnActualizar.setActionCommand("Actualizar");
    vcategoria.jbtnActualizar.addActionListener(this);
    vcategoria.jbtnEliminar.addActionListener(this);
    vcategoria.jbtnEliminar.setActionCommand("Eliminar");
    vcategoria.jbtnSalir.addActionListener(this);//en caso de que es un botón y solo es presionado, por ese motivo se agrega el SetActionListener
    vcategoria.jbtnSalir.setActionCommand("Salir");
    vcategoria.jtableCategoria.addMouseListener(this);//se agregará el tipo de accion, dependiendo de quien lo utilice, en caso contrario no podra ejecutarse correctamente
    vcategoria.jbtnCancelar.addActionListener(this);
    vcategoria.jbtnCancelar.setActionCommand("Cancelar");
     vcategoria.jbtnActualizar.setEnabled(false);
     vcategoria.jbtnEliminar.setEnabled(false);
     vcategoria.jbtnCancelar.setEnabled(false);
     vcategoria.jtxtBuscador.addKeyListener(this);
     vcategoria.jtxtNombre.requestFocus();
     
    vcategoria.setVisible(true);//Por último, se debe de mostrar la ventana.. ADVERTENCIA:NO DEBERÁ COLOCARSE PRIMERO, SINO HASTA EL ULTIMO :)
//vcategoria.jtxtBuscador.setActionCommand(""); 
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String opc=e.getActionCommand();//una variable dondre guardo la acción del botón presionado
        
        switch(opc)
        {
        case"Guardar":
            
            if (JOptionPane.showConfirmDialog(null,"Advertencia", "Desea continuar?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                
            variables();
            if (mcategoria.getNombre().trim().equals("")||mcategoria.getDescripcion().trim().equals("")) {
            JOptionPane.showMessageDialog(null,"Falto rellenar datos");    
            }else{
            //dispose();
            //Jfram1.llenarCombo();
            mcategoria.guardar();
            vaciarvariables();
            tabla();
            JOptionPane.showMessageDialog(null,"successful");}
        }
        break;
        case"Cancelar":
            vcategoria.jbtnGuardar.setEnabled(true);
            vcategoria.jbtnActualizar.setEnabled(false);
            vcategoria.jbtnEliminar.setEnabled(false);
            vaciarvariables();
            vcategoria.jbtnCancelar.setEnabled(false);
            mcategoria.setId(0);
            tabla();//Lo mando a llamar, por si se realizo una busqueda, para que vuelva a la normalidad
            
            
            break;
            case"Eliminar":
                mcategoria.Eliminar();//UTILIZO LA VARIABLE DEL ID ANTERIORMENTE GUARDADO PARA PODER ELIMINAR UN REGISTRO SIN TENER QUE TOMARLO DE UN LABEL
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"successful");
                vcategoria.jbtnGuardar.setEnabled(true);
                vcategoria.jbtnActualizar.setEnabled(false);
                vcategoria.jbtnEliminar.setEnabled(false);
                vcategoria.jbtnCancelar.setEnabled(false);
                break;
            case "Actualizar":
                variables();
                mcategoria.Actualizar();
                vaciarvariables();
                tabla();
                JOptionPane.showMessageDialog(null,"successful");
                vcategoria.jbtnGuardar.setEnabled(true);
                vcategoria.jbtnActualizar.setEnabled(false);
                vcategoria.jbtnEliminar.setEnabled(false);
                vcategoria.jbtnCancelar.setEnabled(false);
                break;
        }
    }
    public void tabla()
    {
        vcategoria.jtableCategoria.setModel(mcategoria.llenartabla());
    }
    
    public void variables()
    {
    mcategoria.setNombre(vcategoria.jtxtNombre.getText());
    mcategoria.setDescripcion(vcategoria.jtxtDescripcion.getText());
    
    }
    
    public void vaciarvariables()
    {
    vcategoria.jtxtNombre.setText("");
    vcategoria.jtxtDescripcion.setText("");
    vcategoria.jtxtBuscador.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        vcategoria.jbtnEliminar.setEnabled(true);//Habilito los botones
        vcategoria.jbtnActualizar.setEnabled(true);
        vcategoria.jbtnCancelar.setEnabled(true);
        vcategoria.jbtnGuardar.setEnabled(false);
       vaciarvariables();
        int row=vcategoria.jtableCategoria.rowAtPoint(e.getPoint());
        vcategoria.jtxtNombre.setText(vcategoria.jtableCategoria.getValueAt(row, 1).toString());
        vcategoria.jtxtDescripcion.setText(vcategoria.jtableCategoria.getValueAt(row, 2).toString());
        
        mcategoria.setId(Integer.parseInt(vcategoria.jtableCategoria.getValueAt(row, 0).toString()));//Guardo la variable del id en el Modelo, para despues utilizarla.. ATENCION:LO UTILIZO PARA PODER ELIMINAR
        //UN REGISTRO, SIN TENER QUE PASAR EL ID A UN LABEL.. :)
        
        
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
        mcategoria.setBusqueda(vcategoria.jtxtBuscador.getText().trim()); 
        vcategoria.jtableCategoria.setModel(mcategoria.Busquedatabla());
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        
    }
}
