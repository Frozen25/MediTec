/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serve;

import Estructuras.ArbolAVL;
import Estructuras.cita;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alexis
 */
@WebService(serviceName = "Citas")
public class Citas {

    /**
     * This is a sample web service operation
     */
    
    @WebMethod(operationName = "addCita")
    public String addCliente(String numero, String nombre) {
        try{
            
            ArbolAVL newAVL = ArbolAVL.loadAVL();
            cita citatemp = new cita(Integer.parseInt(numero), nombre);
            newAVL.Insercion(citatemp);
            newAVL.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    
    @WebMethod(operationName = "getCita")
    public String getData(String numero)
    {
        try{
            
            ArbolAVL newAVL = ArbolAVL.loadAVL();
            String result  = newAVL.get(Integer.parseInt(numero)).getInfo();
            //newBTree.save();
            return result;
 
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
    
    
    @WebMethod(operationName = "addMonto")
    public String addMonto(String numero, String monto) {
        try{
            
            ArbolAVL newAVL = ArbolAVL.loadAVL();
            newAVL.get(Integer.parseInt(numero)).agregarMonto(Float.valueOf(monto));
            newAVL.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    /*
    @WebMethod(operationName = "removeCliente")
    public String removeCliente(String txt) {
        try{
            
            //ArbolAVL newAVL = ArbolAVL.loadAVL();
            //newAVL.delete(txt);    
            //newAVL.save();    
            return "Failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }  
    }
    */
    
}
