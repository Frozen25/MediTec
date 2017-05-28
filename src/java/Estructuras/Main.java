/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;


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

public class Main {
    
    public static void main (String[] args)
    
    {
        //arbol binario
    //abrir x=1  guardar x=2

    int x = 0;
    
    //Abrir el arbol
    if (x==1){
        
        try {

            File fXmlFile = new File(System.getProperty("user.dir") +"\\Casos_Clinicos.xml");
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
            newBST.displayPre();
            //newBST.save("name");
            System.out.println("");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        
    }
    
    
    
    
    //Guardar este arbol
    if (x==2){
        
        
        BinarySearchTree b = new BinarySearchTree();
        CasoClinico coco = new CasoClinico("coco");

        coco.addExamenes("colesterol", "200");

        coco.addMedicamentos("asetaminofen", "150");
        b.insert(coco);
        
        CasoClinico pez = new CasoClinico("pez");
        
        pez.addMedicamentos("asetaminofen", "150");
        pez.addMedicamentos("bruprofenol", "140");
        b.insert(pez);
        
        CasoClinico area = new CasoClinico("area");
        area.addExamenes("estatura", "50");
        area.addExamenes("anchura", "50");
        b.insert(area);
        CasoClinico aereo = new CasoClinico("aereo");
        b.insert(aereo);
        CasoClinico arena = new CasoClinico("arena");
        b.insert(arena);
        CasoClinico pato = new CasoClinico("pato");
        pato.addExamenes("peso", "100");
        System.out.println(pato.getInfo());
        b.insert(pato);
        b.getCaso("pato").removeExamenen("peso");
        //b.display();		
        System.out.println("\n look ");
        System.out.println(b.look(""));
        b.getCaso("arena").removeMedicamento("asetaminofen");
        System.out.println("\n search a");
        System.out.println(b.search("a"));
        
        
        b.save();
        
    }

    
        
        
        
    }

}