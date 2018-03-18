/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static java.awt.Frame.MAXIMIZED_BOTH;

/**
 *
 * @author sergi
 */
public class CMAIN {
    
    public static void main( String [] agrs){
    
        
        Modelo.MMAcceso macceso=new Modelo.MMAcceso();
        Vista.jdlgAcceso vacceso=new Vista.jdlgAcceso(null, true);
        Controlador.CCAcceso cacceso=new Controlador.CCAcceso(macceso, vacceso);
        vacceso.setLocationRelativeTo(null);
        cacceso.form_load();
        
        
    
    }
    
}
