package Estructuras;

public class cita {
	
    public int numCita;
    public String paciente;
    public float pago =0 ;
    public String nume;
	
	public cita(int num, String nombre)
	{
		this.numCita = num;
		this.paciente = nombre;

		
		nume = Integer.toString(num);
	}
        public cita(int num, String nombre, float pago)
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
	

}
