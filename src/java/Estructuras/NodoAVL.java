package Estructuras;

public class NodoAVL{
    public Cita cita;
    public int Factbalance;
    public NodoAVL Derecho, Izquierdo;
	
	public NodoAVL (Cita cit){
		cita = cit;
		Factbalance = 0;
		Derecho=null;
		Izquierdo = null;
	}
        
        public Cita getData(){
        return cita;
            }
}