/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

//package com.meditec.estructuras;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.io.File;
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

/**
 * jBixbe debuggee: test insert and delete operation of a balanced tree data
 * structure. Using integer values read from keyboard as tree elements.
 * 
 * @author ds-emedia
 */
public class BTree<T extends Comparable<T>> {
    
    private static final String direccion = "D:\\0-Tec\\Datos 1\\proyecto 2\\temp1data";

    private static BTree<Integer> tree = new BTree<Integer>();

    private static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {

        System.out.println("test balanced tree operations");
        System.out.println("*****************************");

        String input;
        Integer value;

        do {
            input = stringInput("please select: [i]nsert, [d]elete, [e]xit");
            switch (input.charAt(0)) {
            case 'i':
                value = Integer.parseInt(stringInput("insert: "), 10);
                if (tree.isMember(value)) {
                    System.out.println("value " + value + " already in tree");
                } else {
                    tree.insert(value);
                }
                break;
            case 'd':
                value = Integer.parseInt(stringInput("delete: "), 10);
                if (tree.isMember(value)) {
                    tree.delete(value);
                } else {
                    System.out.println(value + " not found in tree");
                }
                break;
            }
        } while ((input.charAt(0) != 'e'));
    }

    private static String stringInput(String inputRequest) throws IOException {
        System.out.println(inputRequest);
        return reader.readLine();
    }

    /* +++++++++++ instance declarations +++++++++++ */

    private Node root;

    /**
     * Creates an empty balanced tree.
     */
    public BTree() {
        root = null;
    }

    /**
     * Creates a balances tree using the given node as tree root.
     */
    public BTree(Node root) {
        this.root = root;
    }

    /**
     * Inserts an element into the tree.
     */
    public void insert(T info) {
        insert(info, root, null, false);
    }

    /**
     * Checks whether the given element is already in the tree.
     */
    public boolean isMember(T info) {
        return isMember(info, root);
    }
    public Node getMember(T info) {
        return getMember(info, root);
    }
    /**
     * Removes an elememt from the tree.
     */
    public void delete(T info) {
        delete(info, root);
    }

    /**
     * Returns a text representation of the tree.
     */
    public String toString() {
        return inOrder();
    }

    /**
     * Returns all elements of the tree in in-order traversing.
     */
    public String inOrder() {
        return inOrder(root);
    }

    /**
     * Returns all elements of the tree in pre-order traversing.
     */
    public String preOrder() {
        return preOrder(root);
    }

    /**
     * Returns all elements of the tree in post-order traversing.
     */
    public String postOrder() {
        return postOrder(root);
    }

    /**
     * Returns the height of the tree.
     */
    public int getHeight() {
        return getHeight(root);
    }

    private void insert(T info, Node node, Node parent, boolean right) {

        if (node == null) {
            if (parent == null) {
                root = node = new Node(info, parent);
            } else if (right) {
                parent.right = node = new Node(info, parent);
            } else {
                parent.left = node = new Node(info, parent);
            }
            restructInsert(node, false);
        } else if (info.compareTo(node.information) == 0) {
            node.information = info;
        } else if (info.compareTo(node.information) > 0) {
            insert(info, node.right, node, true);
        } else {
            insert(info, node.left, node, false);
        }
    }

    private boolean isMember(T info, Node node) {

        boolean member = false;

        if (node == null) {
            member = false;
        } else if (info.compareTo(node.information) == 0) {
            member = true;
        } else if (info.compareTo(node.information) > 0) {
            member = isMember(info, node.right);
        } else {
            member = isMember(info, node.left);
        }

        return member;
    }
    private Node getMember(T info, Node node) {

        Node member = null;

        if (node == null) {
            member = null;
        } else if (info.compareTo(node.information) == 0) {
            member = node;
        } else if (info.compareTo(node.information) > 0) {
            member = getMember(info, node.right);
        } else {
            member = getMember(info, node.left);
        }

        return member;
    }

    private void delete(T info, Node node) throws NoSuchElementException {

        if (node == null) {
            throw new NoSuchElementException();
        } else if (info.compareTo(node.information) == 0) {
            deleteNode(node);
        } else if (info.compareTo(node.information) > 0) {
            delete(info, node.right);
        } else {
            delete(info, node.left);
        }
    }

    private void deleteNode(Node node) {

        Node eNode, minMaxNode, delNode = null;
        boolean rightNode = false;

        if (node.isLeaf()) {
            if (node.parent == null) {
                root = null;
            } else if (node.isRightNode()) {
                node.parent.right = null;
                rightNode = true;
            } else if (node.isLeftNode()) {
                node.parent.left = null;
            }
            delNode = node;
        } else if (node.hasLeftNode()) {
            minMaxNode = node.left;
            for (eNode = node.left; eNode != null; eNode = eNode.right) {
                minMaxNode = eNode;
            }
            delNode = minMaxNode;
            node.information = minMaxNode.information;

            if (node.left.right != null) {
                minMaxNode.parent.right = minMaxNode.left;
                rightNode = true;
            } else {
                minMaxNode.parent.left = minMaxNode.left;
            }

            if (minMaxNode.left != null) {
                minMaxNode.left.parent = minMaxNode.parent;
            }
        } else if (node.hasRightNode()) {
            minMaxNode = node.right;
            delNode = minMaxNode;
            rightNode = true;

            node.information = minMaxNode.information;

            node.right = minMaxNode.right;
            if (node.right != null) {
                node.right.parent = node;
            }
            node.left = minMaxNode.left;
            if (node.left != null) {
                node.left.parent = node;
            }
        }
        restructDelete(delNode.parent, rightNode);
    }

    private int getHeight(Node node) {
        int height = 0;

        if (node == null) {
            height = -1;
        } else {
            height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
        return height;
    }

    private String inOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + inOrder(node.left) + " ";
            result = result + node.information.toString();
            result = result + inOrder(node.right);
        }
        return result;
    }

    private String preOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + node.information.toString() + " ";
            result = result + preOrder(node.left);
            result = result + preOrder(node.right);
        }
        return result;
    }

    private String postOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + postOrder(node.left);
            result = result + postOrder(node.right);
            result = result + node.information.toString() + " ";
        }
        return result;
    }

    private void restructInsert(Node node, boolean wasRight) {

        if (node != root) {
            if (node.parent.balance == '_') {
                if (node.isLeftNode()) {
                    node.parent.balance = '/';
                    restructInsert(node.parent, false);
                } else {
                    node.parent.balance = '\\';
                    restructInsert(node.parent, true);
                }
            } else if (node.parent.balance == '/') {
                if (node.isRightNode()) {
                    node.parent.balance = '_';
                } else {
                    if (!wasRight) {
                        rotateRight(node.parent);
                    } else {
                        doubleRotateRight(node.parent);
                    }
                }
            } else if (node.parent.balance == '\\') {
                if (node.isLeftNode()) {
                    node.parent.balance = '_';
                } else {
                    if (wasRight) {
                        rotateLeft(node.parent);
                    } else {
                        doubleRotateLeft(node.parent);
                    }
                }
            }
        }
    }

    private void restructDelete(Node z, boolean wasRight) {

        Node parent;
        boolean isRight = false;
        boolean climb = false;
        boolean canClimb;

        if (z == null) {
            return;
        }

        parent = z.parent;
        canClimb = (parent != null);

        if (canClimb) {
            isRight = z.isRightNode();
        }

        if (z.balance == '_') {
            if (wasRight) {
                z.balance = '/';
            } else {
                z.balance = '\\';
            }
        } else if (z.balance == '/') {
            if (wasRight) {
                if (z.left.balance == '\\') {
                    doubleRotateRight(z);
                    climb = true;
                } else {
                    rotateRight(z);
                    if (z.balance == '_') {
                        climb = true;
                    }
                }
            } else {
                z.balance = '_';
                climb = true;
            }
        } else {
            if (wasRight) {
                z.balance = '_';
                climb = true;
            } else {
                if (z.right.balance == '/') {
                    doubleRotateLeft(z);
                    climb = true;
                } else {
                    rotateLeft(z);
                    if (z.balance == '_') {
                        climb = true;
                    }
                }
            }
        }

        if (canClimb && climb) {
            restructDelete(parent, isRight);
        }
    }

    private void rotateLeft(Node a) {

        Node b = a.right;

        if (a.parent == null) {
            root = b;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }

        a.right = b.left;
        if (a.right != null) {
            a.right.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.left = a;

        if (b.balance == '_') {
            a.balance = '\\';
            b.balance = '/';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void rotateRight(Node a) {

        Node b = a.left;

        if (a.parent == null) {
            root = b;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }

        a.left = b.right;
        if (a.left != null) {
            a.left.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.right = a;

        if (b.balance == '_') {
            a.balance = '/';
            b.balance = '\\';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void doubleRotateLeft(Node a) {

        Node b = a.right;
        Node c = b.left;

        if (a.parent == null) {
            root = c;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        }

        c.parent = a.parent;

        a.right = c.left;
        if (a.right != null) {
            a.right.parent = a;
        }
        b.left = c.right;
        if (b.left != null) {
            b.left.parent = b;
        }

        c.left = a;
        c.right = b;

        a.parent = c;
        b.parent = c;

        if (c.balance == '/') {
            a.balance = '_';
            b.balance = '\\';
        } else if (c.balance == '\\') {
            a.balance = '/';
            b.balance = '_';
        } else {
            a.balance = '_';
            b.balance = '_';
        }

        c.balance = '_';
    }

    private void doubleRotateRight(Node a) {

        Node b = a.left;
        Node c = b.right;

        if (a.parent == null) {
            root = c;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        }

        c.parent = a.parent;

        a.left = c.right;
        if (a.left != null) {
            a.left.parent = a;
        }
        b.right = c.left;
        if (b.right != null) {
            b.right.parent = b;
        }

        c.right = a;
        c.left = b;

        a.parent = c;
        b.parent = c;

        if (c.balance == '/') {
            b.balance = '_';
            a.balance = '\\';
        } else if (c.balance == '\\') {
            b.balance = '/';
            a.balance = '_';
        } else {
            b.balance = '_';
            a.balance = '_';
        }
        c.balance = '_';
    }
    
    public String trasOrder() {
        return trasOrder(root);
    }
    
    private String trasOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + trasOrder(node.right);
            result = result + node.information.toString() + " ";
            result = result + trasOrder(node.left);
            
        }
        return result;
    }
    
    //preorder
    public void save(){
        
        save("Clientes");
    }
    
    private String savePre(Node node) {

        String result = "";
        if (node != null) {
            result = result + node.getAll() + ",";
            result = result + savePre(node.left);
            result = result + savePre(node.right);
        }
        return result;
    }
    
    
    public void save(String Filename){
        String savestack = savePre (root);
       
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Clientes");
            doc.appendChild(rootElement);

            // staff elements



            String[] parts = savestack.split(",");

            for(int i=0;i<parts.length;i++)
            {
                String[] partes = parts[i].split(">");

                Element client = doc.createElement("Cliente");
                rootElement.appendChild(client);
                // set attribute to staff element
                Attr attr = doc.createAttribute("id");
                attr.setValue(partes[0]);
                client.setAttributeNode(attr);
                // shorten way
                // staff.setAttribute("id", "1");


                // firstname elements
                Element firstname = doc.createElement("Nombre");
                firstname.appendChild(doc.createTextNode(partes[0]));
                client.appendChild(firstname);

                // lastname elements
                Element lastname = doc.createElement("Correo");
                lastname.appendChild(doc.createTextNode(partes[1]));
                client.appendChild(lastname);

                // nickname elements
                Element nickname = doc.createElement("Activo");
                nickname.appendChild(doc.createTextNode(partes[2]));
                client.appendChild(nickname);

                Element din = doc.createElement("Dinero");
                din.appendChild(doc.createTextNode(partes[3]));
                client.appendChild(din);
                    

                }   
                
                
		

		
                
		

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(direccion + "\\Clientes.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }

        
        
             
        
    }
    
    public BTree loadBTree() {

        try {

            File fXmlFile = new File(direccion + "\\Clientes.xml");
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
        
        
        
    
    
    
    
    
    
    

    public class Node {

        private T information;
        
        private boolean Active;
        
        private String email;
        
        private String nombre;
        
        private float money;

        private Node parent;

        private Node left;

        private Node right;

        char balance;

        public Node(T information, Node parent) {
            String temp = (String) information;
                if (temp.contains(">"))
                {
                    String[] parts = temp.split(">");
                    if (parts.length>=1)
                    {
                        this.information = (T) parts[0];
                        this.nombre = parts[0];     
                        this.email= " ";
                        this.Active = false;
                        this.money = 0;
                    }
                    if (parts.length>=2)
                    {
                        this.email = parts[1];
                        this.Active = false;
                    }
                    if (parts.length>=3)
                    {
                        this.Active= Boolean.valueOf(parts[2]);
                        this.money = 0;
                    }
                    if (parts.length>=4)
                    {
                        this.money = Float.valueOf(parts[3]);
                    }
                }
                else
                {
                    this.information = information;
                    this.nombre = (String) information;
                    this.email= " ";
                    this.money = 0;
                }
            

            
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.balance = '_';
        }
        

        boolean isLeaf() {
            return ((left == null) && (right == null));
        }

        boolean isNode() {
            return !isLeaf();
        }

        boolean hasLeftNode() {
            return (null != left);
        }

        boolean hasRightNode() {
            return (right != null);
        }

        boolean isLeftNode() {
            return (parent.left == this);
        }

        boolean isRightNode() {
            return (parent.right == this);
        }
        public Object getData()
        {
            return this.information;
        }
        public String getEmail()
        {
            return email;
        }
        public Boolean isActive()
        {
            return Active;
        }
        public void setActive(boolean newActive)
        {
            Active = newActive;
        }
        public void changeEmail(String newEmail)
        {
            email = newEmail;
        }
        public String getAll()
        {
            //System.out.println(nombre);
            return nombre + ">" + email + ">" + Active+ ">" + Float.toString(money);
        }
        public void addMoney(float monto)
        {
            this.money += monto;
        }
        
    }
}