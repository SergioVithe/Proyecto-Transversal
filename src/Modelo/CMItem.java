/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author sergi
 */
public class CMItem {
    
     private final int id;
    private final String descripcion;

    public CMItem(int id, String descripcion)
    {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId()
    {
        return id;
    }

    public String getDescription()
    {
        return descripcion;
    }
        
    @Override
    public String toString()
    {
        return descripcion;
    }
    
}
