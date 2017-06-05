package Estructuras;
//Nodo splay
public class NodoSplay{
    public Doctor datos;
    public NodoSplay Hiz;
    public NodoSplay Hde;
  
  public NodoSplay (Doctor data){
	    datos = data;
	    Hiz = null;
	    Hde = null;
  }
  public Doctor getData(){
      return datos;
  }
  
  
}
	