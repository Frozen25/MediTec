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
@WebService(serviceName = "AddCasoClinico")
public class AddCasoClinico {

    /**
     * This is a sample web service operation
     */
    @WebMethod
    public String AddCasoClinico(String txt) {
        try {
            System.out.println("Serve.AddCasoClinico.AddCasoClinico()\n------------\n----");
            File fXmlFile = new File("D:\\0-Tec\\Datos 1\\proyecto 2\\MediTec\\Casos_Clinicos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Caso");

            //System.out.println("----------------------------");
            
            BinarySearchTree newBST = new BinarySearchTree();
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                org.w3c.dom.Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());


                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    CasoClinico CasoTemp = new CasoClinico(eElement.getAttribute("id"));

                    String examenes = eElement.getElementsByTagName("Examenes").item(0).getTextContent();

                    if ((!(examenes.equals("")))&&(!(examenes.equals(" "))))
                    {
                        String[] parts = examenes.split(";");
                        for(int i=0;i<parts.length;i++)
                        {
                            String[] dat = parts[i].split("_");
                            CasoTemp.addExamenes(dat[0], dat[1]);
                        }
                    }
 
                    String medics = eElement.getElementsByTagName("Medicamentos").item(0).getTextContent();
                    
                    if ((!medics.equals(""))&&(!(medics.equals(" "))))
                    {
                        String[] parts = medics.split(";");
                        for(int i=0;i<parts.length;i++)
                        {
                            String[] dat = parts[i].split("_");
                            CasoTemp.addMedicamentos(dat[0], dat[1]);
                        }
                    }

                    newBST.insert(CasoTemp);



                    //System.out.println("Staff id : " + eElement.getAttribute("id"));
                    //System.out.println("Nombre : " + eElement.getElementsByTagName("Nombre").item(0).getTextContent());
                    //System.out.println("Examenes : " + eElement.getElementsByTagName("Examenes").item(0).getTextContent());
                    //System.out.println("Medicamentos : " + eElement.getElementsByTagName("Medicamentos").item(0).getTextContent());


                    }
            }
            
            //print tree
            //newBST.displayPre();
            //newBST.save("name");
            CasoClinico temp = new CasoClinico(txt);
            newBST.insert(temp);
            newBST.save();
            return "Success";
        } catch (Exception e) {
            
            e.printStackTrace();
            return "Failed";
        }
        
    }
    
    
    
    
}
