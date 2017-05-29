package Estructuras;

public class NodoAVL{
    public cita cita;
    public int Factbalance;
    public NodoAVL Derecho, Izquierdo;
	
	public NodoAVL (cita cit){
		cita = cit;
		Factbalance = 0;
		Derecho=null;
		Izquierdo = null;
	}
}