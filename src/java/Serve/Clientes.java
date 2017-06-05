/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serve;

import Estructuras.BTree;
import java.io.File;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import Estructuras.*;
/**
 *
 * @author Alexis
 */
@WebService(serviceName = "Clientes")
public class Clientes {

    /**
     * This is a sample web service operation
     */
     
    @WebMethod(operationName = "addCliente")
    public String addCliente(String txt) {
        try{
            
            BTree newBTree = new BTree().loadBTree();
            newBTree.insert(txt);    
            newBTree.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    
    @WebMethod(operationName = "getData")
    public String getData(String cliente)
    {
        try{
            
            BTree newBTree = new BTree().loadBTree();
            String result = newBTree.getMember(cliente).getAll();
            //newBTree.save();
            return result;
 
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
    
    @WebMethod(operationName = "removeCliente")
    public String removeCliente(String txt) {
        try{
            
            BTree newBTree = new BTree().loadBTree();
            newBTree.delete(txt);    
            newBTree.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    
}
