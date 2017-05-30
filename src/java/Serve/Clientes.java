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
    
    private BTree loadBTree() {

        try {

            File fXmlFile = new File("D:\\0-Tec\\Datos 1\\proyecto 2\\temp1data" + "\\Clientes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Cliente");

            //System.out.println("----------------------------");
            
            BTree newBTree = new BTree();
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                org.w3c.dom.Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());


                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    
                    String correo = eElement.getElementsByTagName("Correo").item(0).getTextContent();
                    if (correo.equals(""))
                    {
                        correo = " ";
                    }
                    String Active = eElement.getElementsByTagName("Activo").item(0).getTextContent();
                    String dinero = eElement.getElementsByTagName("Dinero").item(0).getTextContent();
                    

                    newBTree.insert(nombre +">"+ correo +">"+ Active+">"+dinero);



                    }
            }
            
            //print tree
            //System.out.println(newBTree.preOrder());
            return newBTree;
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }

        
    } 
    @WebMethod
    public String addCliente(String txt) {
        try{
            
            BTree newBTree = loadBTree();
            newBTree.insert(txt);    
            newBTree.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    
    
    public String getData(String cliente)
    {
        try{
            
            BTree newBTree = loadBTree();
            String result = newBTree.getMember(cliente).getAll();
            //newBTree.save();
            return result;
 
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
    
    
    public String removeCliente(String txt) {
        try{
            
            BTree newBTree = loadBTree();
            newBTree.delete(txt);    
            newBTree.save();    
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        
    }
    
}
