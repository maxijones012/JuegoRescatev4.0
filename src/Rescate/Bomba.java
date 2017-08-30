package Rescate;


/**
 *  
 * 
 */
public class Bomba extends Movible{
	private int danio;
	private int velocidad;
	private Config config;
	
	/**
	 * Constructor de la clase Bomba
	 * @param posicionX
	 * @param posicionY
	 * @param escenario
	 * @return {@link #danio}
	 */
	public Bomba(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
		this.config = escenario.getConfig();
		this.setTamanio(new Tamanio(config.getAnchoBomba(),config.getAltoBomba())); //tamaño de la bomba
		this.danio = config.getDañoBomba();
		this.velocidad = config.getVelocidadBomba();
	}

	/**
	 * al avanzar se le reduce la velocidad que lleva si llega a 0, se destruye
	 */
	@Override
	public void jugar() {
		if(this.velocidad >= 0){
			avanzar();
			this.setVelocidad(getVelocidad()-2);
		}
		else
			explotar(this);
			destruir(this);
	}
	
	
	@Override
	public boolean destruir(Elemento elemento) {
		this.getEscenario().getElementos();
		
		return super.destruir(elemento);
	}
	
	
	public void lanzarBomba(Elemento elemento, Bomba bomba){
		bomba.getTamanio().setAlto(config.getAltoBomba());
		bomba.getTamanio().setAncho(config.getAnchoBomba());
		bomba.getPosicion().setX(elemento.getPosicion().getX());
		bomba.getPosicion().setY(elemento.getPosicion().getY());
//		bomba.getDireccion(this.anguloDisparo);
		
		this.setVelocidad(config.getVelocidadBomba());
		
		elemento.getEscenario().addElemento(bomba);
	}
	

	/**
	 * Se ejecuta si la bomba choca contra la pared, si es asi, explota y muere
	 */
	@Override
	public void chocarContraPared() {
		explotar(this);
		destruir(this);
	}
	  
	/**
	 * Se ejecuta si la bomba choca contra algun tanque, si es asi, muere
	*/
	@Override
	public void chocarContraRobot(Robot robot) {
		explotar(this);
		destruir(this);

	}
	
	
	/**
	 * Se ejecuta si la bomba choca contra algun bonus
	*/
	@Override
	public void chocoContraBonus(Bonus bonus) {
	//	bonus.chocarContraBomba(this);
		explotar(this);
		destruir(this);
	}
	
	
	/**
	 * comportamiento que toma la bomba al chochar contra un elemento
	 */
	@Override
	public void chocarContra(Elemento elemento) {
		elemento.chocarContraBomba(this);
	}
	
	/**
	 * comportamiento que toma la bomba al destruirse
	 *  @return devuleve un area de combertura mas grande
	 */
	public void explotar(Bomba bomba){
		bomba.getTamanio().setAlto(bomba.getTamanio().getAlto()+config.getExpansion());
		bomba.getTamanio().setAncho(bomba.getTamanio().getAncho()+config.getExpansion());
		
	}

	
	

	/**
	 * Devuelve el daño que provoca la bomba
	 * @return daño 
	 */
	public int getDanio() {
		return danio;
	}

	/**
	 * Setea la variable daño de la bomba
	 * @param daño
	 */
	public void setDanio(int danio) {
		this.danio = danio;
	}
	

}
