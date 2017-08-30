package Rescate;

public class Persona {
	private String nombre;
	private String apellido;
	
	
	
	/**
	 * Constructor de la Clase persona, parametros: nombre
	 * @param nombre
	 */
	public Persona(String nombre){
		this(nombre,"");		
	}
	
	/**
	 * Constructor de la Clase persona parametros: nombre y apellido
	 * @param nombre
	 * @param apellido
	 */
	public Persona(String nombre, String apellido){
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	
	/**
	 * muestra el nombre de la persona
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setea el nombre de la persona
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	/**
	 * muestra el apellido de la persona
	 * @return
	 */
	public String getApellido() {
		return apellido;
	}
	
	
	/**
	 * Setea el apellido de la persona
	 * @param apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
