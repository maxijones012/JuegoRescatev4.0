package Rescate;


public class BonusEscudo extends Bonus{
	
	private int escudo = 10;
	
	public BonusEscudo(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
	}


	/**
	 * recarga el escudo del robot recibido por parametro
	 */
	@Override
	public void chocarContraRobot(Robot robot) { //TODO agregar por si es un satelite
		this.destruir(this);
	}
	
	
	/**
	 * recarga el escudo del dueño de la municion
	 */
	@Override
	public void chocarContraMunicion(Municion municion) {
		this.chocarContraRobot(municion.getRobot());
		this.destruir(this);
		//System.out.println("OBTENCION DE BATERIA: "+ municion.getRobot().getClass().getName());
	}
	
	/**
	 * al chocar contra un elemento este se destruye
	 */
	@Override
	public void chocarContra(Elemento elemento) {
		elemento.chocarContraBonusEscudo(this);
		destruir(this);
		
		
	}
	

	public int getEscudo() {
		return escudo;
	}


	public void setEscudo(int escudo) {
		this.escudo = escudo;
	}

}
