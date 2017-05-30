package Estructuras;
import java.io.*;
import java.lang.*;
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


//Definicion del arbol splay	
class Splay{
  public int cont;
  public String codigot;
  public NodoSplay raiz;
  public NodoSplay auxp;
  public NodoSplay auxh;
  public boolean bandera = true;
  public String searchresult = "";
  
  //Inserta un elemento en un arbol splay
  public NodoSplay Insertar (Doctor data){
	    try{
	      FileWriter fw = new FileWriter (codigot+".txt", true);
	      BufferedWriter bw = new BufferedWriter (fw);
	      PrintWriter salida = new PrintWriter (bw);
	      salida.println("Elemento: " + data);
	      salida.close();
	      if (raiz == null)
	        raiz = new NodoSplay (data);
	      else{
	        auxp = null;
	        auxh = raiz;
	        while (auxh != null){
	          if (data.getCodigo().compareTo(auxh.datos.getCodigo()) <= 0 ){
	            auxp = auxh;
	            auxh = auxh.Hiz;
	          }
	          else{
	            auxp = auxh;
	            auxh = auxh.Hde;
	          }
	        }
	        NodoSplay nuevo = new NodoSplay (data);
	        if (auxp.datos.getCodigo().compareTo(data.getCodigo()) < 0){
	          auxp.Hde = nuevo;
	          Subir (auxp, nuevo);
	        }
	        else{
	          auxp.Hiz = nuevo;
	          Subir (auxp, nuevo);
	        }
	      }
	    }
	    catch (IOException ioex){
	    }
	return raiz;
  }
  
  //Contructor
  public Splay(){
    raiz = null;
  }
  
  //rotacion zag zag
  public void zagzag(NodoSplay abuelo){
  	try{
  		FileWriter fw = new FileWriter (codigot+".txt", true);
  		BufferedWriter bw = new BufferedWriter (fw);
  		PrintWriter salida = new PrintWriter (bw);
  		if(cont<2){
	  		salida.print("zag ");
	  		cont++;
	        NodoSplay nuevo = new NodoSplay (abuelo.datos);
	        nuevo.Hiz = abuelo.Hiz;
	        nuevo.Hde = abuelo.Hde;
	        nuevo.Hde = auxp.Hiz;
	        abuelo.Hiz = nuevo;
	        abuelo.Hde = auxp.Hde;
	        abuelo.datos = auxp.datos;
	        if (abuelo == raiz)
	        	bandera = false;
	        auxp = abuelo;
	    }
  		else{
  			salida.println("");
  			cont=0;
  		}	
       	salida.close();
  	}
  	catch(Exception e){
  	}
  }
  
  //rotacion zag zig
  public void zagzig(NodoSplay abuelo){
  	try{
  		FileWriter fw = new FileWriter (codigot+".txt", true);
  		BufferedWriter bw = new BufferedWriter (fw);
  		PrintWriter salida = new PrintWriter (bw);
  		if(cont==1 || cont ==2)
  			salida.println("");
  		salida.println("zag zig");
       	salida.close();
       	cont=0;
       	NodoSplay nuevo = new NodoSplay (abuelo.datos);
        nuevo.Hiz = abuelo.Hiz;
        nuevo.Hde = abuelo.Hde;
        abuelo.datos = auxh.datos;
        nuevo.Hde = auxh.Hiz;
        abuelo.Hiz = nuevo;
        auxp.Hiz = auxh.Hde;
        abuelo.Hde = auxp;
        if (abuelo == raiz){
        	raiz = abuelo;
        	bandera = false;
        }
        auxh = abuelo;
        auxp = TieneAbuelo (auxh);
  	}
  	catch(Exception e){
  	}
  }
  
  //rotacion zig zig
  public void zigzig(NodoSplay abuelo){
  	try{
  		FileWriter fw = new FileWriter (codigot+".txt", true);
  		BufferedWriter bw = new BufferedWriter (fw);
  		PrintWriter salida = new PrintWriter (bw);
  		if(cont<2){
  			salida.print("zig ");
  			cont++;
  			NodoSplay nuevo = new NodoSplay (abuelo.datos);
  			nuevo.Hiz = abuelo.Hiz;
        	nuevo.Hde = abuelo.Hde;
        	nuevo.Hiz = auxp.Hde;
        	abuelo.Hde = nuevo;
        	abuelo.Hiz = auxp.Hiz;
        	abuelo.datos = auxp.datos;
        	if (abuelo == raiz)
          		bandera = false;
        	auxp = abuelo;
  		}
  		else{
  			salida.println("");
  			cont=0;
  		}	
       	salida.close();
  	}
  	catch(Exception e){
  	}
  }
  
  //rotacion zig zag
  public void zigzag(NodoSplay abuelo){
  	try{
  		FileWriter fw = new FileWriter (codigot+".txt", true);
  		BufferedWriter bw = new BufferedWriter (fw);
  		PrintWriter salida = new PrintWriter (bw);
  		if(cont==1 || cont ==2)
  			salida.println("");
  		salida.println("zig zag");
       	salida.close();
       	cont=0;
       	NodoSplay nuevo = new NodoSplay (abuelo.datos);
	  	nuevo.Hiz = abuelo.Hiz;
      	nuevo.Hde = abuelo.Hde;
      	abuelo.datos = auxh.datos;
      	nuevo.Hiz = auxh.Hde;
      	abuelo.Hde = nuevo;
      	auxp.Hde = auxh.Hiz;
      	abuelo.Hiz = auxp;
      	if (abuelo == raiz){
        	raiz = abuelo;
        	bandera = false;
      	}
      	auxh = abuelo;
      	auxp = TieneAbuelo (auxh);
  	}
  	catch(Exception e){

  	}
  }
  
  //rotacion zig
  public void zig(){
  	try{
  		FileWriter fw = new FileWriter (codigot+".txt", true);
  		BufferedWriter bw = new BufferedWriter (fw);
  		PrintWriter salida = new PrintWriter (bw);
  		if(cont==2)
        	salida.println("");
  		salida.println("zig ");
       	salida.close();
       	raiz.Hiz = auxh.Hde;
        auxh.Hde = raiz;
        raiz = auxh;
        cont=0;
  	}
  	catch(Exception e){
  		
  	}
  }
  
  //rotacion zag
  public void zag(){
  	try{
  		FileWriter fw = new FileWriter (codigot+".txt", true);
  		BufferedWriter bw = new BufferedWriter (fw);
  		PrintWriter salida = new PrintWriter (bw);
  		if(cont==2)
       		salida.println("");
  		salida.println("zag");
       	salida.close();
       	raiz.Hde = auxh.Hiz;
        auxh.Hiz = raiz;
        raiz = auxh;
        cont=0;
  	}
  	catch(Exception e){
  	}
  }
  
  //sube el recien insertado a la raiz
  public void Subir (NodoSplay padre, NodoSplay hijo){
  	  bandera=true;
  	  auxp=padre;
  	  auxh=hijo;
      while ((bandera == true) && (TieneAbuelo (auxp) != null)){
        NodoSplay abuelo = TieneAbuelo (auxp);
        //zag zag
        if ((abuelo.Hde == auxp) && (auxp.Hde == auxh)){
          zagzag(abuelo);
        }
        else{
          //zag zig
          if ((abuelo.Hde == auxp) && (auxp.Hiz == auxh)){
         	zagzig(abuelo);
          }
          else{
          	//zig zig
            if ((abuelo.Hiz == auxp) && (auxp.Hiz == auxh)){
              zigzig(abuelo);
            }
            //zig zag
            else{
              zigzag(abuelo);
            }
          }
        }
      }
      if (auxh != raiz){
      	//zag
        if (raiz.Hde == auxh){
          zag();
        }
        //zig
        else{
          zig();
        }
      }
  }
  
  //retorna el abuelo de un nieto  
  public NodoSplay TieneAbuelo (NodoSplay nodo){
    if (nodo == raiz)
      return null;
    else{
      NodoSplay padre = null;
      NodoSplay hijo = raiz;
      while (hijo != nodo){
        if (nodo.datos.codigo.compareTo(hijo.datos.codigo) <= 0){
          padre = hijo;
          hijo = hijo.Hiz;
        }
        else{
          padre = hijo;
          hijo = hijo.Hde;
        }
      }
      return padre;
    }
  }
  
  //imprime en inorden el arbol splay
  public void Postorden (NodoSplay root){
  	if(root==null)
  		return;
  	else{
  		Postorden (root.Hiz);
  		Postorden (root.Hde);
    	System.out.print (root.datos.codigo + " ");
    	
  	}
  }
  
  //elimina un elemento de un arbol splay y coloca su antecesor
  //en la raiz	
  public NodoSplay Eliminar (String codl){
    if (codl == raiz.datos.codigo){
      NodoSplay borrado = raiz;
      if ((raiz.Hiz == null) && (raiz.Hde == null)){
        raiz = null;
        return borrado;
      }
      else{
        if ((raiz.Hiz != null) && (raiz.Hde != null)){
          NodoSplay aux = raiz;
          raiz = MayordeMenores (raiz);
          raiz.Hiz = aux.Hiz;
          raiz.Hde = aux.Hde;
          return borrado;
        }
        else{
          if (raiz.Hde != null){
            raiz = raiz.Hde;
            return borrado;
          }
          else{
            raiz = raiz.Hiz;
            return borrado;
          }
        }
      }
    }
    else{
      NodoSplay padre = null;
      NodoSplay hijo = raiz;
      while (hijo.datos.codigo != codl){
        if (codl.compareTo(hijo.datos.codigo) <= 0){
          padre = hijo;
          hijo = hijo.Hiz;
        }
        else{
          padre = hijo;
          hijo = hijo.Hde;
        }
      }
      Subir (padre, hijo);
      NodoSplay rai = raiz;
      Eliminar (raiz.datos.codigo);
      return rai;
    }
  }
  
  //buscar el mayor de los menores
  public NodoSplay MayordeMenores (NodoSplay nodo){
    NodoSplay padre = nodo;
    NodoSplay aux = nodo.Hiz;
    while (aux.Hde != null){
      padre = aux;
      aux = aux.Hde;
    }
    padre.Hde = aux.Hiz;
    return aux;
  }
  
  //buscar un elemento y lo sube a la raiz
  public NodoSplay Buscar (String codl){
  
  	try{
  		FileWriter fw = new FileWriter (codigot+".txt", true);
      	BufferedWriter bw = new BufferedWriter (fw);
      	PrintWriter salida = new PrintWriter (bw);
	    if (codl == raiz.datos.codigo){
	    	salida.println("Rotacion para busqueda: ");
	    	salida.println("Sin rotacion");
	      	salida.println("Elemento encontrado. Raiz: " + raiz.datos.codigo);
          	salida.close();
	 	}
	    else{
	      NodoSplay padre = null;
	      NodoSplay hijo = raiz;
	      salida.println("Rotacion para busqueda: ");
	      salida.close();
	      while ((hijo != null) && (hijo.datos.codigo != codl)){
	        if (codl.compareTo(hijo.datos.codigo) <= 0){
	          padre = hijo;
	          hijo = hijo.Hiz;
	        }
	        else{
	          padre = hijo;
	          hijo = hijo.Hde;
	        }
	      }
	      if (hijo == null){
	        NodoSplay aux = TieneAbuelo (padre);
	        if (padre != raiz)
	          Subir (aux, padre);
	        FileWriter fw1 = new FileWriter (codigot+".txt", true);
      		BufferedWriter bw1 = new BufferedWriter (fw1);
	        PrintWriter salida1 = new PrintWriter (bw1);
			salida1.println("El elemento no se encuentra. Raiz: " + raiz.datos.codigo);
          	salida1.close();
	      }
	      else{
	        Subir (padre, hijo);
	        FileWriter fw2 = new FileWriter (codigot+".txt", true);
      		BufferedWriter bw2 = new BufferedWriter (fw2);
	        PrintWriter salida2 = new PrintWriter (bw2);
	        salida2.println("Elemento encontrado. Raiz: " + raiz.datos.codigo);
          	salida2.close();
	      }
	    }
	 }
	 catch(Exception e){
	 	
	 }
	 return raiz;
  }
  
  //retorna si es miembro un elemento
  public boolean Miembro (String dat,NodoSplay rai){
  	raiz=rai;
    NodoSplay hijo = raiz;
    while ((hijo != null) && (hijo.datos.codigo != dat)){
      if (dat.compareTo(hijo.datos.codigo) <= 0){
        hijo = hijo.Hiz;
      }
      else{
        hijo = hijo.Hde;
      }
    }
    if (hijo == null)
      return false;
    else
      return true;
  }
  public String look(String id)
  {
    searchresult = "";
    InorderSearch(raiz, id);
    if (!searchresult.equals("")){
        String doctores [] = searchresult.split(">");

        for (int top = 1 ; top < doctores.length ; top++)
        {
            String item = doctores [top];
            int i = top;
            while (i > 0 && item.compareTo(doctores [i - 1]) < 0)
            {
                doctores [i] = doctores [i - 1];
                i--;
            }

        }

        String resultado = "";

        for (int i = 0; i < doctores.length; i++) {
            
               resultado += doctores[i] + ">";
            
        }
        resultado = resultado.substring(0, resultado.length()-1);

        return resultado;
        

    }
    
    return "";
}

  

private void InorderSearch(NodoSplay node, String id) {

        
    if (node != null) {
        InorderSearch(node.Hiz, id);
        if (node.datos.getCodigo().contains(id))
        {
            searchresult += node.datos.getCodigo() + ","+ node.datos.getNombre() + ">";
        }
        InorderSearch(node.Hde, id);
    }
        
}

public void save()
{
    save(raiz);
}

  // An iterative process to print preorder traversal of Binary tree
  private void save(NodoSplay node) {
      try{
       
          // Base Case
          if (node == null) {
              return;
          }
          
          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

          // root elements
          Document doc = docBuilder.newDocument();
          Element rootElement = doc.createElement("Doctores");
          doc.appendChild(rootElement);
          
          
          // Create an empty stack and push root to it
          Stack<NodoSplay> nodeStack = new Stack<NodoSplay>();
          nodeStack.push(raiz);

          /* Pop all items one by one. Do following for every popped item
           a) print it
           b) push its right child
           c) push its left child
           Note that right child is pushed first so that left is processed first */
          while (nodeStack.empty() == false) {

              // Pop the top item from stack and print it
              NodoSplay mynode = nodeStack.peek();
              
             
              
              Element element = doc.createElement("Doctor");
              rootElement.appendChild(element);
              
              // set attribute to staff element
		Attr attr = doc.createAttribute("Codigo");
		attr.setValue(mynode.datos.codigo);
		element.setAttributeNode(attr);
              
              Element at1 = doc.createElement("Nombre");
		at1.appendChild(doc.createTextNode(mynode.datos.nombre));
		element.appendChild(at1);
              
              Element at2 = doc.createElement("Codigo");
		at2.appendChild(doc.createTextNode(mynode.datos.codigo));
		element.appendChild(at2);
              
           
              
              nodeStack.pop();

              // Push right and left children of the popped node to stack
              if (mynode.Hde != null) {
                  nodeStack.push(mynode.Hde);
              }
              if (mynode.Hiz != null) {
                  nodeStack.push(mynode.Hiz);
              }
          }

      
         
          // write the content into xml file
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          DOMSource source = new DOMSource(doc);
          StreamResult result = new StreamResult(new File("D:\\0-Tec\\Datos 1\\proyecto 2\\temp1data" + "\\Doctores.xml"));

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
  
  
  
}
