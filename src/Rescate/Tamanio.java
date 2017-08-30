package Rescate;

public class Tamanio {
	private int ancho;
	private int alto;
	
	public Tamanio(int ancho, int alto){
		this.ancho = ancho;
		this.alto = alto;
	}
	
	
	/**
	 * Retorna el ancho del Elemento
	 * 
	 * @return Ancho del Elemento
	 */
	public int getAncho() {
		return ancho;
	}
	
	
	/**
	 * Setea el ancho del elemento
	 * 
	 * @param ancho ancho del elemento
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	

	/**
	 * Retorna el alto del Elemento
	 * 
	 * @return alto del Elemento
	 */
	public int getAlto() {
		return alto;
	}
	
	
	
	/**
	 * Setea el alto del elemento
	 * 
	 * @param ancho alto del elemento
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}
}
