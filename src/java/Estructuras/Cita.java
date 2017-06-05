package Estructuras;

public class Cita {
	
    public int numCita;
    public String paciente;
    public float pago =0 ;
    public String nume;
	
	public Cita(int num, String nombre)
	{
		this.numCita = num;
		this.paciente = nombre;

		
		nume = Integer.toString(num);
	}
        public Cita(int num, String nombre, float pago)
	{
		this.numCita = num;
		this.paciente = nombre;
                this.pago = pago;
		
		nume = Integer.toString(num);
	}
	public void agregarMonto(float nuevoMonto)
	{
		pago += nuevoMonto;
		
	}
    public void cambiarPaciente(String nuevoPaciente)
    {
        this.paciente = nuevoPaciente;
    }
    public String getInfo(){
        return Integer.toString(numCita)+">"+paciente+">"+Float.toString(pago);
    
    }
	

}
