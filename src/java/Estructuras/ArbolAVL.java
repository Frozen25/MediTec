/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

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

import java.io.*;
import org.w3c.dom.NodeList;

//Arbol AVl
public class ArbolAVL{
    public String impresor="";
    public NodoAVL A;
    public boolean Hh;
    private static final String direccion = "D:\\0-Tec\\Datos 1\\proyecto 2\\temp1data";
	//Guarda las rotaciones en un archivo
	
	//Inserta un elemento en el arbol
	public void Insercion (Cita cita){
		if ((!Miembro (cita,A))){
			NodoAVL info = new NodoAVL(cita);
			A=InsertarBalanceado(A,info);
		}
		else
			System.out.println("Error autor repetido");
	}
	//Auxiliar de Insercion
	NodoAVL InsertarBalanceado(NodoAVL R, NodoAVL Nodo){
		NodoAVL N1;
		NodoAVL info = Nodo;
		if (ArbolVacio(R)){
			R= info;
			System.out.println("se informa, numero cita: "+ info.cita.numCita+", nombre:"+info.cita.paciente+", pago:"+info.cita.pago);
			Hh=true;
		}
		else
			if (Nodo.cita.numCita < R.cita.numCita){
				R.Izquierdo=InsertarBalanceado(R.Izquierdo,Nodo);
				if (Hh)
					switch(R.Factbalance){
						case 1:{
							R.Factbalance= 0;
							Hh=false;
						}	
						break;
						case 0:
							R.Factbalance= -1; 
						break;
						//se reestructura ya que pasaria a valer-2 y se desequilibra a la izq
						case -1:{
							N1=R.Izquierdo;
							if (N1.Factbalance== -1){   
								R = RotacionIzquierdaIzquierda(R,N1);
							}
							else{
								R = RotacionIzquierdaDerecha(R,N1);
							}
							Hh = false;
						}
						break;
					}		
			}
			else{	
			if (Nodo.cita.numCita > R.cita.numCita){
				R.Derecho=InsertarBalanceado(R.Derecho, Nodo);
				if (Hh)
				switch(R.Factbalance){
					case -1:
						R.Factbalance=0;
						Hh=false;	
					break;
					case 0:
						R.Factbalance=1; 
					break;
					//se reestructura ya que pasaria a valer-2 y se desequilibra a la izq
					case 1:{
						N1=R.Derecho;
						if (N1.Factbalance==1){
							R = RotacionDerechaDerecha(R,N1);
						}
						else{
							R = RotacionDerechaIzquierda(R,N1);
						}
						Hh = false;
					}
					break;
				}	
				
			}
			else{
				System.out.println("Error: No se pueden numeros iguales");
				Hh = false;
			}
		}
	return R;	
	}
	//retorna si esta vacio
	boolean ArbolVacio(NodoAVL R){
		return (R == null);
	}
	//rota a la derecha
	NodoAVL RotacionDerechaDerecha(NodoAVL N, NodoAVL N1){
		N.Derecho = N1.Izquierdo;
		N1.Izquierdo = N;
		if (N1.Factbalance==1) {
			N.Factbalance=0;
			N1.Factbalance=0;
		}
		else{
			N.Factbalance = 1;
			N1.Factbalance = -1;
		}
		N= N1;
		return N;
	}
	
	NodoAVL RotacionDerechaIzquierda(NodoAVL N, NodoAVL N1){
		NodoAVL N2;
		N2 = N1.Izquierdo;
		N.Derecho = N2.Izquierdo;
		N2.Izquierdo=N;
		N1.Izquierdo=N2.Derecho;
		N2.Derecho=N1;
		if (N2.Factbalance==1){
			N.Factbalance=-1;
		}
		else{
			N.Factbalance=0;
		}
		if (N2.Factbalance==-1)
			N1.Factbalance=1;
		else
			N1.Factbalance=0;
		N2.Factbalance=0;
		N=N2;
		return N;
	}
	
	NodoAVL RotacionIzquierdaIzquierda(NodoAVL N, NodoAVL N1){
		N.Izquierdo = N1.Derecho;
		N1.Derecho = N;
		if (N1.Factbalance==-1){
			N.Factbalance=0;
			N1.Factbalance=0;
		}
		else{
			N.Factbalance=-1;
			N1.Factbalance=1;
		}
		N=N1;
		return N;
	}
	
	NodoAVL RotacionIzquierdaDerecha(NodoAVL N, NodoAVL N1){
		NodoAVL N2;
		N2=N1.Derecho;
		N.Izquierdo=N2.Derecho;
		N2.Derecho=N;
		N1.Derecho=N2.Izquierdo;
		N2.Izquierdo=N1;
		if (N2.Factbalance==1)
			N1.Factbalance=-1;
		else
			N1.Factbalance=0;
		if (N2.Factbalance==-1)
			N.Factbalance=1;
		else
			N.Factbalance=0;
		N2.Factbalance=0;
		N=N2;
		return N;
	}
	//Para verificar si esta el autor
	boolean Miembro(Cita cita, NodoAVL R){
		NodoAVL Aux = R;
		boolean miembro = false;
		while (Aux != null){
			if (cita==Aux.cita){
				miembro = true;
				Aux = null;
			}
			else{
				if (cita.numCita>Aux.cita.numCita)
					Aux = Aux.Derecho;
				else{
					Aux = Aux.Izquierdo;
					if (Aux == null)
						miembro = false;
				}
			}
		}
		return miembro;
	}
	//busca la cantidad de nodos de un arbol avl
	int CantidadNodosAVL(NodoAVL A){
		int cont = 0;
		if (A == null) 
			cont = cont;
		else{
			cont = cont + 1 + CantidadNodosAVL(A.Izquierdo) + CantidadNodosAVL(A.Derecho);
		}
		return cont;
	}
	//altura	
	public int Altura(NodoAVL raiz){
		if (raiz == null)
		return 0;
		else
		return	1 + Math.max(Altura(raiz.Izquierdo), Altura(raiz.Derecho));
	}
        
        
        public void PostOrdenAVL()
        {
            PostOrdenAVL (A);
        }
	//Despliega la informacion en Postorden
	private void PostOrdenAVL (NodoAVL Nodo){
		if (Nodo == null){
			return ;
		}
		else{
			PostOrdenAVL (Nodo.Izquierdo);
			PostOrdenAVL (Nodo.Derecho);
			impresor=impresor+"Autor: "+Nodo.cita;
		}
	}
        
        public void InordenAVL()
        {
            InordenAVL (A);
        }
	//Despliega la informacion en Inorden
	private void InordenAVL (NodoAVL Nodo){
		if (Nodo == null)
			return;
		else{
			InordenAVL (Nodo.Izquierdo);
			System.out.print("cita numero: "+Nodo.cita.numCita+"  ");
			InordenAVL (Nodo.Derecho);
		}
	}
	
	//buscar elemento
	public boolean find(Cita cit){
        NodoAVL current = A;
        while(current!=null){
            if(current.cita==cit){
            	System.out.println(true);
                    return true;
            }else 
            	if(current.cita.numCita >0){
                    current = current.Izquierdo;
            }else{
                    current = current.Derecho;
            }
        }
        return false;
    }
	
    public Cita get(int num){
        NodoAVL current = A;
        while(current!=null){
            if(current.cita.numCita==num){
            	
                return current.cita;
            }else 
            	if(current.cita.numCita > num){
                    current = current.Izquierdo;
            }else{
                    current = current.Derecho;
            }
        }
        return null;
    }    
        
    public void save()
    {
        save(A);
    }
 
    // An iterative process to print preorder traversal of Binary tree
    private void save(NodoAVL node) {
        try{
         
            // Base Case
            if (node == null) {
                return;
            }
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Citas");
            doc.appendChild(rootElement);
            
            
            // Create an empty stack and push root to it
            Stack<NodoAVL> nodeStack = new Stack<NodoAVL>();
            nodeStack.push(A);

            /* Pop all items one by one. Do following for every popped item
             a) print it
             b) push its right child
             c) push its left child
             Note that right child is pushed first so that left is processed first */
            while (nodeStack.empty() == false) {

                // Pop the top item from stack and print it
                NodoAVL mynode = nodeStack.peek();
                
                //System.out.print(mynode.getName() + " ");
                
                Element element = doc.createElement("Cita");
                rootElement.appendChild(element);
                
                // set attribute to staff element
		Attr attr = doc.createAttribute("numCita");
		attr.setValue(mynode.cita.nume);
		element.setAttributeNode(attr);
                
                Element at1 = doc.createElement("Numero");
		at1.appendChild(doc.createTextNode(mynode.cita.nume));
		element.appendChild(at1);
                
                Element at2 = doc.createElement("Nombre");
		at2.appendChild(doc.createTextNode(mynode.cita.paciente));
		element.appendChild(at2);
                
                Element at3 = doc.createElement("MontoPagar");
		at3.appendChild(doc.createTextNode(String.valueOf(mynode.cita.pago)));
		element.appendChild(at3);
                
                nodeStack.pop();

                // Push right and left children of the popped node to stack
                if (mynode.Derecho != null) {
                    nodeStack.push(mynode.Derecho);
                }
                if (mynode.Izquierdo != null) {
                    nodeStack.push(mynode.Izquierdo);
                }
            }

        
           
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(direccion + "\\Citas.xml"));

            // Output to console for testing
            //StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);



            System.out.println("\nFile saved! \n"+System.getProperty("user.dir"));

      } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
      } catch (TransformerException tfe) {
            tfe.printStackTrace();
      }
        
    }
    
    public static ArbolAVL loadAVL(){
    
    try {

            File fXmlFile = new File(direccion +"\\Citas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Cita");

            //System.out.println("----------------------------");
            
            ArbolAVL nuevoAVL =new ArbolAVL();
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                org.w3c.dom.Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());


                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String Numero = eElement.getElementsByTagName("Numero").item(0).getTextContent();
                    String monto = eElement.getElementsByTagName("MontoPagar").item(0).getTextContent();

                    Cita tempCita =new Cita( Integer.parseInt(Numero), nombre, Float.valueOf(monto));
                    
                    nuevoAVL.Insercion(tempCita);



                    }
            }
            
            //print tree
            //nuevoAVL.InordenAVL();
            return nuevoAVL;
            //newBST.save("name");
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }
}
	
	
