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

import java.io.File;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class BinarySearchTree {
    public   NodoArbolBinario root;
    private  String lookstack;
    private final static  String direccion = "D:\\0-Tec\\Datos 1\\proyecto 2\\temp1data";
    public BinarySearchTree(){
            this.root = null;
    }

    public boolean isMember(String id){
        NodoArbolBinario current = root;
        while(current!=null){
            if(current.getData().getName().equals(id)){
                    return true;
            }else if(current.getData().getName().compareTo(id)>0){
                    current = current.left;
            }else{
                    current = current.right;
            }
        }
        return false;
    }
    public NodoArbolBinario getMember(String id){
        NodoArbolBinario current = root;
        while(current!=null){
            if(current.getData().getName().equals(id)){
                    return current;
            }else if(current.getData().getName().compareTo(id)>0){
                    current = current.left;
            }else{
                    current = current.right;
            }
        }
        return null;
    }
    
    public CasoClinico getCaso(String id){
        
        NodoArbolBinario current = root;
        
        while(current!=null){
            if(current.getData().getName().equals(id)){
                if (current.getData() instanceof CasoClinico)
                {
                    return (CasoClinico) current.getData();
                }
            }else if(current.getData().getName().compareTo(id)>0){
                    current = current.left;
            }else{
                    current = current.right;
            }
        }
        return null;
    
    
    }
    public boolean delete(String id){
        NodoArbolBinario parent = root;
        NodoArbolBinario current = root;
        boolean isLeftChild = false;
        while(!(current.getData().getName().equals(id))){
            parent = current;
            if(current.getData().getName().compareTo(id)>0){
                isLeftChild = true;
                current = current.left;
            }else{
                isLeftChild = false;
                current = current.right;
            }
            if(current ==null){
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if(current.left==null && current.right==null){
            if(current==root){
                root = null;
            }
            if(isLeftChild ==true){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //Case 2 : if node to be deleted has only one child
        else if(current.right==null){
            if(current==root){
                root = current.left;
            }else if(isLeftChild){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
        }
        else if(current.left==null){
            if(current==root){
                root = current.right;
            }else if(isLeftChild){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
        }else if(current.left!=null && current.right!=null){

            //now we have found the minimum element in the right sub tree
            NodoArbolBinario successor = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }			
            successor.left = current.left;
        }		
        return true;		
    }

    public NodoArbolBinario getSuccessor(NodoArbolBinario deleleNode){
        NodoArbolBinario successsor =null;
        NodoArbolBinario successsorParent =null;
        NodoArbolBinario current = deleleNode.right;
        while(current!=null){
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
            //successsorParent
        if(successsor!=deleleNode.right){
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        System.out.println("deleted!");
        return successsor;
    }
    public void insert(AbstractBinary id){
        NodoArbolBinario newNode = new NodoArbolBinario(id);
        if(root==null){
            root = newNode;
            return;
        }
        NodoArbolBinario current = root;
        NodoArbolBinario parent = null;
        while(true){
            parent = current;
            if(current.getData().getName().compareTo(id.getName())>0){				
                current = current.left;
                if(current==null){
                    parent.left = newNode;
                    return;
                }
            }else{
                current = current.right;
                if(current==null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }
    public void display(){
        display(root);      
    }
    
    private void display(NodoArbolBinario root){
        if(root!=null){
            display(root.left);
            System.out.print(" " + root.getData().getName());
            display(root.right);
        }
    }
    
    public String displayIn(){
        lookstack = "";
        displayIn(root);
        return lookstack;
    }
    
    private void displayIn(NodoArbolBinario root){
        if(root!=null){
            displayIn(root.left);
            lookstack += root.getData().getName() +"_"+ root.getData().getInfo();
            //System.out.print(" " + root.getData().getName());
            displayIn(root.right);
        }
    }
    
    public void displayPre(){
        displayPre(root);      
    }
    
    private void displayPre(NodoArbolBinario root){
        if(root!=null){
            System.out.print(" " + root.getData().getName());
            displayPre(root.left);
            displayPre(root.right);
        }
    }
    
    public String look(String id){
        lookstack = "";
        NodoArbolBinario current = root;
        int depth = 0;
        String path = "";
        
        while(current!=null){
            
            if(current.getData().getName().startsWith(id)){
                    look(current,id, depth, path );
                    current = null;
                    
                    
            }else if(current.getData().getName().compareTo(id)>0){
                path +=( "-" + current.getData().getName() );
                current = current.left;
            }else{
                path +=( "-" + current.getData().getName()  );
                current = current.right;
            }
            depth +=1;
            
        }
        return lookstack;
        
    }
    private void look(NodoArbolBinario root, String id, int depth ,String path){
        if(root!=null){
            look(root.left,  id, depth +1 , path+ "-" + root.getData().getName()   );

            if (root.getData().getName().startsWith(id))
            {

                
                System.out.println("Found: " + root.getData().getName() + " , Depth: " + (depth) + ", Height: " + findHeight(root) + ", Path Size: " + depth );
                System.out.println(" PATH: " + path +" - "+ root.getData().getName() );
                
                
                lookstack += ( root.getData().getName()+ "," + root.getData().getInfo() 
                         + "," + Integer.toString(depth) + "," + Integer.toString( findHeight(root) ) +
                        "," + Integer.toString(depth) +  "," + path +"-"+ root.getData().getName() +">"     );
                
                //System.out.println(lookstack);
                
                //array de nodo, profundidad(int), altura(int), ruta(string)
                //Object a[] = new Object[4];
                //a[0]=root;a[1]=depth;a[2]= findHeight(root);a[3] =  path +" - "+ root.getName();
                //lookstack.push(a);
                
                
                
                
                //array de  profundidad(string), altura(string), ruta(string)
                //String a[] = new String[3];
                //a[0]= Integer.toString(depth);
                //a[1]= Integer.toString( findHeight(root) );
                //a[2] =  path +" - "+ root.getName();                        
                //lookstack.push(root);
                //lookstack.push(a);
                
                
                //lookstack.push(root);lookstack.push(depth);lookstack.push(findHeight(root));lookstack.push((path +" - "+ root.getName()));
                
            }
            

            look(root.right,  id, depth+1, path+ "-" + root.getData().getName()  );
        }
    }
    
    public String search(String id){
        lookstack = "";
        
        int depth = 0;
        String path = "";
        search(root,id, depth, path );
                    
        
        return lookstack;
        
    }
    private void search(NodoArbolBinario root, String id, int depth ,String path){
        if(root!=null){
            search(root.left,  id, depth +1 , path+ "-" + root.getData().getName()   );

            if (root.getData().getName().contains(id))
            {

                
                System.out.println("Found: " + root.getData().getName() + " , Depth: " + (depth) + ", Height: " + findHeight(root) + ", Path Size: " + depth );
                System.out.println(" PATH: " + path +" - "+ root.getData().getName() );
                
                
                lookstack += ( root.getData().getName()+ "," + root.getData().getInfo() 
                         + "," + Integer.toString(depth) + "," + Integer.toString( findHeight(root) ) +
                        "," + Integer.toString(depth) +  "," + path +"-"+ root.getData().getName() +">"     );
                
                //System.out.println(lookstack);
                
                //array de nodo, profundidad(int), altura(int), ruta(string)
                //Object a[] = new Object[4];
                //a[0]=root;a[1]=depth;a[2]= findHeight(root);a[3] =  path +" - "+ root.getName();
                //lookstack.push(a);
                
                
                
                
                //array de  profundidad(string), altura(string), ruta(string)
                //String a[] = new String[3];
                //a[0]= Integer.toString(depth);
                //a[1]= Integer.toString( findHeight(root) );
                //a[2] =  path +" - "+ root.getName();                        
                //lookstack.push(root);
                //lookstack.push(a);
                
                
                //lookstack.push(root);lookstack.push(depth);lookstack.push(findHeight(root));lookstack.push((path +" - "+ root.getName()));
                
            }
            

            search(root.right,  id, depth+1, path+ "-" + root.getData().getName()  );
        }
    }
    
    private int findHeight(NodoArbolBinario current) {
    if (current == null) {
        return -1;
    }

        int lefth = findHeight(current.left);
        int righth = findHeight(current.right);

    if (lefth > righth) {
        return lefth + 1;
    } else {
        return righth + 1;
    }
    }

    
    
    
    public void save()
    {
        save(root, "Casos_Clinicos");
    }
    public void save(String name)
    {
        name = name.replaceAll(" ", "");
        save(root, name);
    }
 
    // An iterative process to print preorder traversal of Binary tree
    private void save(NodoArbolBinario node, String fileName) {
        try{
         
            // Base Case
            if (node == null) {
                return;
            }
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Casos_Medicos");
            doc.appendChild(rootElement);
            
            
            // Create an empty stack and push root to it
            Stack<NodoArbolBinario> nodeStack = new Stack<NodoArbolBinario>();
            nodeStack.push(root);

            /* Pop all items one by one. Do following for every popped item
             a) print it
             b) push its right child
             c) push its left child
             Note that right child is pushed first so that left is processed first */
            while (nodeStack.empty() == false) {

                // Pop the top item from stack and print it
                NodoArbolBinario mynode = nodeStack.peek();
                
                //System.out.print(mynode.getName() + " ");
                
                Element element = doc.createElement("Caso");
                rootElement.appendChild(element);
                
                // set attribute to staff element
		Attr attr = doc.createAttribute("id");
		attr.setValue(mynode.getData().getName());
		element.setAttributeNode(attr);
                
                Element at1 = doc.createElement("Nombre");
		at1.appendChild(doc.createTextNode(mynode.getData().getName()));
		element.appendChild(at1);
                
                
                
                
                
                /*
                String[] examenes = parts[0].split(";");   
                String[] medicamentos = parts[1].split(";");
                //arrays de strings de forma nombre_precio
                
                for(int i=0;i<examenes.length;i++)
                {
                    System.out.println(examenes[i]);
                }
                
                
                
                for(int i=0;i<medicamentos.length;i++)
                {
                    System.out.println(medicamentos[i]);
                }
                */
                System.out.println("__");
                String string = mynode.getData().getInfo();
                if (mynode.getData().getInfo().equals(",")||mynode.getData().getInfo().equals(" ,")||
                        mynode.getData().getInfo().equals(", ")||mynode.getData().getInfo().equals(" , "))
                {
                    string = " , ";
                }
                
                String[] parts = string.split(",");
                //System.out.println("__"+ parts[0] + "__"+ parts[1] + "__");
                
                Element at2 = doc.createElement("Examenes");
                if ((parts[0].equals(""))||(parts[0].equals(" ")))
                {
                    parts[0] = " ";
                }
		at2.appendChild(doc.createTextNode(parts[0]));
		element.appendChild(at2);
                
                Element at3 = doc.createElement("Medicamentos");
                if ((parts[1].equals("")) ||(parts[1].equals(" ")))
                {
                    parts[1] = " ";
                }
		at3.appendChild(doc.createTextNode(parts[1]));
		element.appendChild(at3);
                
                nodeStack.pop();

                // Push right and left children of the popped node to stack
                if (mynode.right != null) {
                    nodeStack.push(mynode.right);
                }
                if (mynode.left != null) {
                    nodeStack.push(mynode.left);
                }
            }

        
           
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(direccion + "\\Casos_Clinicos.xml"));

            // Output to console for testing
            //StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);



            System.out.println("\nFile saved! \n"+"D:\\0-Tec\\Datos 1\\proyecto 2\\MediTec\\Casos_Clinicos.xml");

      } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
      } catch (TransformerException tfe) {
            tfe.printStackTrace();
      }
        
    }
    
    public BinarySearchTree loadBST(){
        try {    
            File fXmlFile = new File(direccion + "\\Casos_Clinicos.xml");
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
                            if ((!(parts[i].equals("")))&&(!(parts[i].equals(" ")))){
                            String[] dat = parts[i].split("_");
                            CasoTemp.addExamenes(dat[0], dat[1]);
                        }
                        }
                    }
 
                    String medics = eElement.getElementsByTagName("Medicamentos").item(0).getTextContent();
                    
                    if ((!medics.equals(""))&&(!(medics.equals(" "))))
                    {
                        String[] parts = medics.split(";");
                        for(int i=0;i<parts.length;i++)
                        {
                            if ((!(parts[i].equals("")))&&(!(parts[i].equals(" ")))){
                            String[] dat = parts[i].split("_");
                            CasoTemp.addMedicamentos(dat[0], dat[1]);
                        }
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
            
            return newBST;
            
        } catch (Exception e) {
            
            e.printStackTrace();
            return null;
        }
        
        
    }
    
        
        
 

}

