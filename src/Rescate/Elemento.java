package Rescate;



public abstract class  Elemento {
	private Posicion posicion;
	private Tamanio tamanio;
	private boolean estaVivo = true;
	private Escenario escenario;


	/**
	 * Constructor de clase donde setea los valores
	 * @param nombre
	 * @param posicionX
	 * @param posicionY
	 * @param escenario
	 */	
	public Elemento(int X, int Y, Escenario escenario){
		this.tamanio = new Tamanio(20, 20);
		this.posicion = new Posicion(X, Y);
		this.setEscenario(escenario);
	}
	
	
	
	
	/**	
	 * Se le asigna un turno a cada elemento y lo ejecuta de su forma especifica
	 * realizadndo las acciones que se desea.
	 */
	public abstract void jugar();
	
	
	
	
	/**
	 * retorna si el elemento se encuentra vivo.
	 * @return Verdadero cuando el elemento se encuentra vivo
	 */
	public boolean estaVivo() {
		return estaVivo;
	}
	
	
	/**
	 * Indica que el elemento se destruye para luego se depurado
	 */
	public boolean destruir(Elemento elemento){
		return this.estaVivo = false;
	}
	
	
	/**
	 * Asocia el escenario al elemento
	 * 
	 * @param escenario Escneario donde se encuentra el elemento
	 */
	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}
	
	
	/**
	 * reotrna el escenario donde se esta jugando
	 * 
	 * @return Escenario del juego
	 */
	public Escenario getEscenario() {
		return this.escenario;
	}
	
	
	
	/**
	 * retorna la posicion en donde se encuentra el elemento.
	 * 
	 * @return Posicion del elemento
	 */
	public Posicion getPosicion() {
		return posicion;
	}

	
	/**
	 * setea la posicion del elemenento
	 * @param posicion
	 */
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	
	/**
	 * setea el tamaño del elemento.
	 * @param tamanio es el  nuevo tamaño
	 */
	public void setTamanio(Tamanio tamanio) {
		this.tamanio = tamanio;
	}
	
	
	
	/**
	 * retorna el tamaño de elemento.
	 * @return tamaño del elemento
	 */
	public Tamanio getTamanio() {
		return tamanio;
	}
		

	/**
	 * Indica el comportamiento que toma al chocar con otro elemento.
	 * Se sobreescribe en las subclases
	 * 
	 * @param elemento Elemento con el que choco
	 * @return 
	 */
	public abstract void chocarContra(Elemento elemento);

	public void chocarContraSatelite(Satelite satelite){};
	
	public void chocarContraPared() {}
	
	public void chocarContraMunicion(Municion municion){}
	
	public void chocarContraBomba(Bomba bomba){}
	
	public void chocarContraBonus(){}

	public void chocarContraZonaRescate(ZonaRescate zonaRescate){}

	public abstract void chocarContraBonusBateria (BonusBateria bonus);

	public abstract void chocarContraBonusEscudo(BonusEscudo bonus);

	public void chocarContraRobot(Robot robot){}
	
	public void chocarContraRefugio(Refugio refugio){}
		
	

	
}

