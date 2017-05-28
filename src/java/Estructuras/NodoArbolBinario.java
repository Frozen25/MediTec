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
public class NodoArbolBinario {

    private AbstractBinary data;
    NodoArbolBinario left;
    NodoArbolBinario right;	
    
    public NodoArbolBinario(AbstractBinary data){
        this.data = data;
        left = null;
        right = null;
    }

    
    public AbstractBinary getData()
    {
        return data;
    }
}