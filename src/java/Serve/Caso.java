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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Alexis
 */
@WebService(serviceName = "Caso")
public class Caso {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "addCC")
    public String addCC(String txt) {
        try{
            CasoClinico temp = new CasoClinico(txt);
            BinarySearchTree newBST= new BinarySearchTree().loadBST();
            newBST.insert(temp);    
            newBST.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    @WebMethod(operationName = "removeCC")
    public String removeCC(String txt) {
        try{
            BinarySearchTree newBST= new BinarySearchTree().loadBST();
            newBST.displayPre();
            newBST.delete(txt);
            newBST.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    
    @WebMethod(operationName = "getCC")
    public String getCC(String txt) {
        try{
            BinarySearchTree newBST= new BinarySearchTree().loadBST();
            //String result = newBST.getCaso(txt).getInfo();
            String result = newBST.getCaso(txt).getInfo();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed getting data";
        }
        
    }
    
    
    
}