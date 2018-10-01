/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serve;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import Estructuras.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 *
 * @author Alexis
 */
@WebService(serviceName = "Doctores")
public class Doctores {

    /**
     * This is a sample web service operation
     */
    
    
    
    
    
    @WebMethod(operationName = "addDoctor")
    public String addDoctor(String codigo, String nombre) {
        try{
            
            Splay newSplay = Splay.loadSplay();
            
            
            Doctor d8 = new Doctor(codigo, nombre);
            newSplay.Insertar(d8);	
            newSplay.save();
            //Doctor tempDoc = new Doctor(codigo, nombre);
            //newSplay.Insertar(tempDoc);    
            //newSplay.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
    @WebMethod(operationName = "getDoctor")
    public String getDoctor(String codigo)
    {
        try{
            
            Splay newSplay = Splay.loadSplay();
            String result = newSplay.Buscar(codigo).getData().getNombre();
            //newBTree.save();
            return result;
 
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
    @WebMethod(operationName = "searchDoctor")
    public String searchDoctor(String codigo){
    try{
            
            Splay newSplay = Splay.loadSplay();
            
            //String x = newSplay.look(codigo);
            //newBTree.save();
            return newSplay.look(codigo);
 
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FAILED");
            return null;
        }
    }
    @WebMethod(operationName = "removeDoctor")
    public String removeDoctor(String codigo){
    try{
            
            Splay newSplay = Splay.loadSplay();
            newSplay.Eliminar(codigo);
            //newBTree.save();
            return "Sucess";
 
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
    
}
