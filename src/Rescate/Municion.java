package Rescate;

public class Municion extends Movible{

	private int daño;
	private Robot robot;

	private int anguloDisparo;
	private Config config;
	
	public Municion(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
		this.config = escenario.getConfig();
		this.setTamanio(new Tamanio(config.getAnchoMunicion(), config.getAltoMunicion()));
	}

	/**
	 * avanza en su turno
	 */
	@Override
	public void jugar() {
		avanzar();
	}
	
	/**
	 * Se ejecuta si la municion choca contra la pared, si es asi, muere
	 */
	@Override
	public void chocarContraPared() {
		destruir(this);
	}
	
	/**
	 * Se ejecuta si la municion choca contra algun tanque, si es asi, muere
	*/
	@Override
	public void chocarContraRobot(Robot robot) {
		if (this.getRobot()!= robot){        	//controla que le robot con el que coliciona, es distinto a si mismo			
			destruir(this);
		}
	}
	
	/**
	 * Se ejecuta si la municion choca contra algun bonus, le devuelve la llamada
	*/
	@Override
	public void chocoContraBonus(Bonus bonus) {}
	
	/**
	 * Se ejecuta si la municion choca contra algun elemento, le devuelve la llamada
	*/
	@Override
	public void chocarContra(Elemento elemento) {
		elemento.chocarContraMunicion(this);
			
	}
	
	
	@Override
	public void chocarContraZonaRescate(ZonaRescate zona) {
		destruir(this);
	}
	
	
	/**
	 * 
	 * Metodo que se encarga de lanzar una {@link Municion municion}
	 * a los diferentes elementos del escenario.
	 * 
	 */
	public void disparar(Elemento elemento, Municion municion) {			
			municion.getTamanio().setAlto(config.getAltoMunicion());
			municion.getTamanio().setAncho(config.getAnchoMunicion());
			municion.getPosicion().setX(elemento.getPosicion().getX());
			municion.getPosicion().setY(elemento.getPosicion().getY());
			municion.setDireccion(this.anguloDisparo);

			this.setVelocidad(config.getVelocidadMunicion());
			
			elemento.getEscenario().addElemento(municion);
		
		
	}

	/**
	 * 
	 * crea la municion con la direccon del robot y la posicion en donde se encuentra el robot
	 */
/*	public Municion(Elemento elemento){
		
		if(elemento instanceof Robot){	
			Robot robot = (Robot) elemento;
			this.setDireccin(robot.getDireccin());
			this.setVelocidad(robot.getVelocidad()+10);
			this.getPosicion().setX(robot.getPosicion().getX());
			this.getPosicion().setY(robot.getPosicion().getY());
		}
		if (elemento instanceof Satelite){
			//Satelite satelite = (Satelite) elemento;
			// direccion del radar-APLICAR
			this.setVelocidad(10);
			this.getPosicion().setX(elemento.getPosicion().getX());
			this.getPosicion().setY(elemento.getPosicion().getY());
		}
	}

	*/

	/**
	 * retorna el daño de la munision	
	 * @return dano de la munision
	 */
	public int getDaño() {
		return daño;
	}
	
	
	/**
	 * setea el daño de la municion
	 * @param daño
	 */
	public void setDaño(int daño) {
		this.daño = daño;
	}

	/**
	 * retorna el robot que lanzo la municion
	 * @return robot que lanza la municion
	 */
	public Robot getRobot(){
		return this.robot;
	}
	
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	/**
	 * Devuelve el angulo en que se encuentra apuntando el canion.
	 * 
	 * @return Angulo con respecto al tanque
	 */
	public int getAnguloDisparo() {
		
		return this.anguloDisparo;
	}

	/**
	 * Setea el angulo en que se encuentra apuntando el cañon.
	 * 
	 * @param angulo Angulo con respecto al tanque
	 */	
	public void setAnguloDisparo(int angulo) {
	
		this.anguloDisparo = angulo;
	}

	/**
	 * Gira la dirección del canion en un determinado angulo
	 * 
	 * @param angulo Ángulo que se desea girar. Si el canion tiene un angulo actual de 100º y se gira 30º, el resultado 
	 * debera ser un angulo final de 130º
	 */
	public void girar(int angulo) {
		
		this.setAnguloDisparo(getAnguloDisparo() + angulo);
	}
}
