package Controlador;

import Modelo.MMAcceso;
import Vista.jdlgAcceso;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author sergi
 */
public class CCAcceso implements ActionListener{

    public CCAcceso(MMAcceso macceso, jdlgAcceso vacceso) {
        this.macceso = macceso;
        this.vacceso = vacceso;
    }
Modelo.MMAcceso macceso;
Vista.jdlgAcceso vacceso;

public void form_load()
{
    vacceso.jcmbNivel.removeAllItems();
    combo();
    vacceso.jbtnConectar.addActionListener(this);
    vacceso.jbtnConectar.setActionCommand("Conectar");
    vacceso.setVisible(true);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        String opc=e.getActionCommand();
        variables();
        switch(opc){
        case"Conectar":
            if (macceso.verificausuario()) {
                //JOptionPane.showMessageDialog(vacceso, "Usuario y/o Contraseña incorrecta"+macceso.getNivel()+"/ "+macceso.getPassword()+"/"+macceso.getUsuario());
                vacceso.dispose();
                Modelo.MMPrincipal mprincipal=new Modelo.MMPrincipal();
                Vista.jfrmPrincipal vprincipal=new Vista.jfrmPrincipal();
                Controlador.CCPrincipal cprincipal=new Controlador.CCPrincipal(mprincipal, vprincipal);
                cprincipal.formLoad();
                vprincipal.setExtendedState(MAXIMIZED_BOTH);
                
            }else{
                //JOptionPane.showMessageDialog(vacceso, "Usuario y/o Contraseña incorrecta"+macceso.getNivel()+"/ "+macceso.getPassword()+"/"+macceso.getUsuario());
            JOptionPane.showMessageDialog(vacceso, "Usuario y/o Contraseña incorrecta");
            }
            break;
        }
    }
    public void variables()
    {
        macceso.setNivel(vacceso.jcmbNivel.getSelectedIndex()+1);
        macceso.setUsuario(vacceso.jtxtUsuario.getText());
        macceso.setPassword(vacceso.jtxtpass.getText());
    }
    public void combo() {
        vacceso.jcmbNivel.setModel(macceso.cargarCombo("select * from tblnivel", "vchNombre","intIdNivel" ));
        
    }
    
}
