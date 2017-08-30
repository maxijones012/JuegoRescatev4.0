package Rescate;


public class BonusBateria extends Bonus{
	
	private int energia = 10 ;
	
	
	public BonusBateria(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
	}

	
	
	/**
	 * recarga la energia del dueño de la municion
	 */
	@Override
	public void chocarContraMunicion(Municion municion) {
		this.chocarContraRobot(municion.getRobot());
	}
	
	
	/**
	 * recarga la energia del robot
	 */
	
	@Override
	public void chocarContraRobot(Robot robot) {
		this.destruir(this);
	}
	
	
	/**
	 * al chocar contra un elemento este se destruye
	 */
	@Override
	public void chocarContra(Elemento elemento) {
		elemento.chocarContraBonusBateria(this);
		this.destruir(this);
	}
    
	 
	/**
	 * obtiene la energia del bonus
	 * @return
	 */
	public int getEnergia() {
		return energia;
	}

	/**
	 * setea la energia que otorga el bonus
	 * @param energia
	 */
	public void setEnergia(int energia) {
		this.energia = energia;
	}

	
}
