/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Alexis
 */
public class Medicamento extends AbstractBinary{
    public String Price;
    
    
    public Medicamento (String nombre, String precio){
        this.Price = precio;
        this.name = nombre;
    
    }
    @Override
    public String getInfo()
    {
        return Price+";";
    }
    
    
}
