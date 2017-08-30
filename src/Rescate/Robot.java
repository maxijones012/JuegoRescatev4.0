package Rescate;

import java.util.ArrayList;

public abstract class Robot extends Movible implements RadarListener{
	private int nivelEscudo;
	private int nivelEnergia;
	private boolean llevaPersona = false;
	private int cantidadMunicion;
	private int cantidadBomba;
	private Radar radar; 
	private Config config;
	private Municion municion;
	private Persona personaCargada;
	private Escenario escenario;	
	

	/**
	 * Constructor de clase
	 * @param posicionX
	 * @param posicionY
	 * @param escenario
	 */
	public Robot(int posicionX, int posicionY, Escenario escenario){
		super(posicionX, posicionY, escenario);
		this.escenario = escenario;
		this.config = escenario.getConfig();
		this.radar = new Radar(this);
		this.radar.addRadarListener(this);
		this.cantidadBomba = escenario.getConfig().getCantidadBomba();
		this.cantidadMunicion = escenario.getConfig().getCantidadMunicion();
		this.municion = new Municion(posicionX, posicionY, escenario);
		Tamanio tamanio = new Tamanio(config.getAnchoRobot(), config.getAltoRobot());
		this.setTamanio(tamanio); 
		this.nivelEscudo= this.config.getEscudo();
	}
	
	
	/**
	 * accion que toma el robot a jugar en su turno, se encarga de avanzar y de escanar mediante el radar que 
	 * le pertenece
	 */
	@Override
	public void jugar() {
		
		if (this.getNivelEnergia()>=0){	
			avanzar();
			this.getRadar().escanear();				
		}
		else{
			destruir(this);
		}
			
	}
	
	/**
	 * Deja las personas que tiene el robot en el refugio
	 * @param refugio
	 */
	public void dejarPersona(Refugio refugio){
		refugio.personasEnRefugio.add(getPersonaCargada());
		this.setPersonaCargada(null);
		this.setLlevaPersona(false);
	}
	
	
	/**
	 * realiza un desplazamiento hacia la direccion ya establecida
	 */
	@Override
	public void avanzar() {
		
		if (this.getNivelEnergia()>=0){
			super.avanzar();			
			this.setNivelEnergia(getNivelEnergia()-1);			
		}
		
	}
	
	/**
	 * Se ejecuta si el robot choca contra la pared
	 * 
	*/
	@Override
	public void chocarContraPared() {
		super.girar(90);
	}

	

	/**
	 * decrementa el escudo de un robot si choca contra una municion
	*/
	@Override
	public void chocoContraMunicion(Municion municion) {
		this.setNivelEscudo(this.getNivelEscudo()- municion.getDaño());
		System.out.println("DAÑO RECIBIDO: , "+this.getNivelEscudo());
	}
	
	@Override
	public void chocarContraBonusBateria(BonusBateria bonus) {
		bonus.chocarContraRobot(this);
		this.recargarEnergia(bonus.getEnergia());		
	}
	
	/**
	 * recarga la energia del robot con una cantidad dada
	 * @param energia
	 */
	public void recargarEnergia(int energia){
		this.setNivelEnergia(this.getNivelEnergia()+energia);
	}

	
	@Override
	public void chocarContraBonusEscudo(BonusEscudo bonus) {
		this.recargarEscudo(bonus.getEscudo());
//		System.out.println("choque bonus");
		bonus.chocarContraRobot(this);
	}
	
	
	@Override
	public void chocarContraSatelite(Satelite satelite) {
		satelite.chocarContraRobot(this);
		girar(180);
		this.setNivelEscudo(this.getNivelEscudo()-10);
	}
	
	/**
	 * incrementa el escudo del robot con una cantidad dada
	 * @param escudo
	 */
	public void recargarEscudo(int escudo){
		this.setNivelEscudo(this.getNivelEscudo()+escudo);
	}
	
	
	public void chocarContraRefugio(Refugio refugio) {
		refugio.chocarContraRobot(this);
		this.girar(180);				
	
	}
	
	public void chocarContraZonaRescate(ZonaRescate zonaRescate){
		zonaRescate.chocarContraRobot(this);		
		girar(180);
		this.setNivelEnergia(this.nivelEnergia-1);		
	}
	
	public void chocarContraBomba(Bomba bomba){
		this.setNivelEscudo(this.getNivelEscudo()- config.getDañoBomba());
		this.setNivelEnergia(this.nivelEnergia-1);
		if (this.getNivelEnergia()<=0){
			destruir(this);			
		}
	}
	
	/**
	 * devuelve el radar que pertence a el robot
	 * @return radar del robot
	 */
	public Radar getRadar() {
		return radar;
	}


 
	
	/**
	 *  Procedimiento que responde a disparar municion
	 */
	public void disparar(){
		if (this.cantidadMunicion > 0){
			Municion municion = new Municion(this.getPosicion().getX(), this.getPosicion().getY(), escenario);
			municion.setDireccion(this.getDireccion());
			municion.disparar(this, municion);
			this.cantidadMunicion--;
			
		}
		
	}
	
	
	/**
	 * Procedimiento que responde a lanzar bomba
	 */
	public void lanzarBomba(){
		if (this.cantidadBomba > 0){
			this.cantidadBomba--;
		}
	}
	
		
	/**
	 * retorna si el robot esta vivo, si su nivel de escudo es 0 se lo destruye
	 */				
	public boolean estaVivo() {
		 return super.estaVivo();
	}


	/**
	 * Muestra el nivel de escudo del robot
	 * @return nivel de escudo del robot
	 */
	public int getNivelEscudo() {
		return nivelEscudo;
	}
	
	public Persona getPersonaCargada() {
		return personaCargada;
	}
	
	
	public void setPersonaCargada(Persona personaCargada) {
		this.personaCargada = personaCargada;
	}
	
	
	/**
	 * Este metodo se ejecuta cuando el elemento choca contra otro elemento.
	 * En el mismo se efectua la accion correspondiendo dependiendo del elemento
	 * contra el que se choco.
	 * @param elemento Elemento contra el que esta chocando
	 */
	@Override
	public void chocarContra(Elemento elemento) {	
		if (elemento instanceof BonusBateria){
			BonusBateria bonus = (BonusBateria) elemento;
			elemento.chocarContraBonusBateria(bonus);
		}
		if (elemento instanceof BonusEscudo){
			BonusEscudo bonus = (BonusEscudo) elemento;
			elemento.chocarContraBonusEscudo(bonus);
		}
		if (elemento instanceof Robot){
			girar(90);
			elemento.chocarContraRobot(this); //robot		
			
		}
	}
	

	/**
	 * Setea el nivel de escudo del robot
	 * @param nivelEscudo
	 */
	public void setNivelEscudo(int nivelEscudo) {
		this.nivelEscudo = nivelEscudo;
	}
	
	
	/**
	 * se decrementa el nivel de escudo del robot en una cantidad dada
	 * @param daño
	 */
	public void romperEscudo(int daño){
		this.setNivelEscudo(this.getNivelEscudo()-daño);
	}	
	
	/**
	 * muestra el nivel de energia del robot
	 * @return
	 */
	public int getNivelEnergia() {
		return nivelEnergia;
	}
	
	/**
	 * Setea el nivel de energia del robot
	 * @param nivelEnergia
	 */
	public void setNivelEnergia(int nivelEnergia) {
		this.nivelEnergia = nivelEnergia;
	}

	/**
	 * muestra la cantidad de municiones que posse el robot
	 * @return
	 */
	public int getCantidadMunicion() {
		return cantidadMunicion;
	}
	
	
	/**
	 * Setea la cantidad de municiones que tiene el robot
	 * @param cantidadMunicion
	 */
	public void setCantidadMunicion(int cantidadMunicion) {
		this.cantidadMunicion = cantidadMunicion;
	}
	
	
	/**
	 * Muestra la cantidad de bombas que tiene el robot
	 * @return
	 */
	public int getCantidadBomba() {
		return cantidadBomba;
	}
	
	
	/**
	 * Setea la cantidad de bombas que tiene el robot
	 * @param cantidadBomba
	 */
	public void setCantidadBomba(int cantidadBomba) {
		this.cantidadBomba = cantidadBomba;
	}
	
	/**
	 * muestr el escenario del robot
	 */
	public Escenario getEscenario() {
		return escenario;
	}


	/**
	 * setea el escenario para el robot
	 */
	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}

	
	/**
	 * devulve el estado de el robot si lleva una persona o no
	 * @return true o false
	 */
	public boolean isLlevaPersona() {
		return llevaPersona;
	}

	
	/**
	 * setea el estado del robot si lleva una persona o no
	 * @param llevaPersona
	 */
	public void setLlevaPersona(boolean llevaPersona) {
		this.llevaPersona = llevaPersona;
	}


	/**
	* Metodo para realizar acciones cuando el radar detecta elementos
	*/
	@Override
	public void elementosVistos(ArrayList<Elemento> elementosVistos) {
//		/**
//		 * Avanza el tanque si aun posee combustible
//		*/
//		for (int i=0; i<elementosVistos.size(); i++){
//			int angulo = this.angulo(this.getPosicion(),elementosVistos.get(i).getPosicion());
//			this.getMunicion().setAnguloDisparo(angulo);	
//			System.out.println("Robot ha disparado");			
//			
//			Municion municion = new Municion(this.getPosicion().getX(),this.getPosicion().getY(), escenario);
//			municion.setRobot(this);
//			municion.disparar(this, municion);
//
//
//		}
//		
	}


	public int angulo(Posicion pInicial, Posicion pFinal){
		
		double deltaX = pFinal.getX() - pInicial.getX();
		double deltaY = pFinal.getY() - pInicial.getY();
		double distancia = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
				
		double radianes = 0;
			
		if (distancia > 0){
				radianes = Math.acos(deltaX/distancia);
		}
		int angulo = (int)Math.toDegrees(radianes);
		

		if (deltaY < 0){
				angulo = 360 - angulo;
		}
		return angulo;
	}


	public Municion getMunicion() {
		return municion;
	}


	public void setMunicion(Municion municion) {
		this.municion = municion;
	}


	/**
	 * Rescata una persona y setea la bandera del robot
	 * @param persona
	 */
	public void rescatar(Persona persona) {
		this.llevaPersona= true;
		this.personaCargada=persona;
	}

}
