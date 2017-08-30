package Rescate;

public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	
	
	/**
	 * Devuelve la posicion del eje X
	 * 
	 * @return Posicion en el eje X.
	 */
	public int getX() {
		return x;
	}
	
	
	/**
	 * Setea la posicion del eje X
	 * 
	 * @param x Posicion en el eje X.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	
	/**
	 * Devuelve la posicion del eje Y
	 * 
	 * @return Posicion en el eje X.
	 */
	public int getY() {
		return y;
	}
	

	/**
	 * Setea la posicion del eje Y
	 * 
	 * @param x Posicion en el eje Y.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
		
	
}
