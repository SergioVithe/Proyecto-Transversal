/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MMPrincipal;
import Vista.jfrmPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 *
 * @author sergi
 */
public class CCPrincipal implements ActionListener{

    public CCPrincipal(MMPrincipal mprincipal, jfrmPrincipal vprincipal) {
        this.mprincipal = mprincipal;
        this.vprincipal = vprincipal;
    }

    Modelo.MMPrincipal mprincipal;
    Vista.jfrmPrincipal vprincipal;
    
    public void formLoad()
    {
    
    vprincipal.jmiCategorias.addActionListener(this);
    vprincipal.jmiCategorias.setActionCommand("Categorias");
    vprincipal.jmiEmpleado.setActionCommand("Empleados");
    vprincipal.jmiEmpleado.addActionListener(this);
    vprincipal.jmiProveedor.addActionListener(this);
    vprincipal.jmiProveedor.setActionCommand("Proveedores");
    vprincipal.jmiJefe.addActionListener(this);
    vprincipal.jmiJefe.setActionCommand("Jefe de departamento");
    vprincipal.jmiPago.setActionCommand("Tipo de pago");
    vprincipal.jmiPago.addActionListener(this);
    vprincipal.jmiClientes.addActionListener(this);
    vprincipal.jmiClientes.setActionCommand("Clientes");
    vprincipal.Image.setLocale(Locale.GERMAN);
    vprincipal.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();
        
        switch(str)
        {
        case"Categorias":
            Modelo.MMCategoria mcategoria=new Modelo.MMCategoria();
            Vista.jdlgCategorias vcategoria=new Vista.jdlgCategorias(vprincipal, true);
            Controlador.CCCategoria ccategoria=new Controlador.CCCategoria(mcategoria, vcategoria);
            vcategoria.setLocationRelativeTo(null);
            ccategoria.form_Load();
            break;
            case"Empleados":
                Modelo.MMEmpleado mempleado=new Modelo.MMEmpleado();
                Vista.jdlgEmpleados vempleado=new Vista.jdlgEmpleados(vprincipal, true);
                Controlador.CCEmpleado cempleado=new Controlador.CCEmpleado(mempleado, vempleado);
                vempleado.setLocationRelativeTo(null);
                cempleado.form_load();
            break;
            case"Proveedores":
                Modelo.MMProveedor mproveedor=new Modelo.MMProveedor();
                Vista.jdlgProveedor vproveedor=new Vista.jdlgProveedor(vprincipal, true);
                Controlador.CCProveedor ccproveedor=new Controlador.CCProveedor(mproveedor, vproveedor);
                vproveedor.setLocationRelativeTo(null);
                ccproveedor.form_load();
                break;
            case"Jefe de departamento":
                    Modelo.MMJefeDepa mjefe=new Modelo.MMJefeDepa();
                    Vista.jdlgJefeDep vjefe=new Vista.jdlgJefeDep(vprincipal, true);
                    Controlador.CCJefeDepa cjefe=new Controlador.CCJefeDepa(mjefe, vjefe);
                    vjefe.setLocationRelativeTo(null);
                    cjefe.form_load();
            break;
            case"Tipo de pago":
                Modelo.MMModoPago mmodopago=new Modelo.MMModoPago();
                Vista.jdlgModoPago vmodopago=new Vista.jdlgModoPago(vprincipal, true);
                Controlador.CCModoPago cmodopago=new Controlador.CCModoPago(mmodopago, vmodopago);
                vmodopago.setLocationRelativeTo(null);
                cmodopago.form_load();
                break;
            case"Clientes":
                Modelo.MMClientes mcliente=new Modelo.MMClientes();
                Vista.jdlgClientes vcliente=new Vista.jdlgClientes(vprincipal, true);
                Controlador.CCClientes ccliente=new Controlador.CCClientes(mcliente, vcliente);
                vcliente.setLocationRelativeTo(null);
                ccliente.form_load();
            break;
        }
    }
    
}
