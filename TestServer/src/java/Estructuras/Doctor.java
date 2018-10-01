package Estructuras;
public class Doctor {
	
	public String codigo;
	public String nombre;

	public Doctor(){}
	public Doctor(String codigo, String nombre)
	{
		
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	
	
	public String getCodigo()
	{
		return this.codigo;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
        {
            this.nombre = nombre;
        }

}
