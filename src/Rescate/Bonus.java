package Rescate;


/**
 * Clase bonus
 * @author Maxi Jones
 *
 */
public abstract class Bonus extends Elemento{

	private int tiempoDeVida;
	private Config config;
	
	
	
	public Bonus(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
		this.config = escenario.getConfig();
		this.setTamanio(new Tamanio(config.getAnchoBonus(), config.getAltoBonus()));
		
	}
	
	
	@Override
	public void chocarContraMunicion(Municion municion) {}
	
	
	
	/**
	 * Consulta el estado de un bonus
	 */
	@Override
	public boolean estaVivo() {
		return super.estaVivo();
	}
	
	
	/**
	 * juega el bonus hasta que termine a su tiempo de vida 
	 */
	@Override
	public void jugar() {
		if (this.getTiempoDeVida()>=0){
			this.setTiempoDeVida(this.getTiempoDeVida()-1);
			this.estaVivo();			
		}
		else{
			destruir(this);
		}
	}
	
	
	/**
	 * retorna la llamada al elemento contra el que choco
	 */
	@Override
	public void chocarContra(Elemento elemento) {
		destruir(this);
		elemento.chocarContraBonus();
	}	
	

	
	public void chocarContraRobot(Robot robot){
		destruir(this);
	}   //se redefine en las subclases de bonus
	
	
	/**
	 * retorna el tiempo de vida del bonus
	 * 
	 * @return Tiempo de vida seteado
	 */
	public int getTiempoDeVida() {
		return tiempoDeVida;
	}
	
	
	/**
	 * Setea el tiempo de vida del bonus
	 * 
	 */
	public void setTiempoDeVida(int tiempoDeVida) {
		this.tiempoDeVida = tiempoDeVida;
	}


	
	public void chocarContraBonusBateria(BonusBateria bonus) {}
	
	public void chocarContraBonusEscudo(BonusEscudo bonus) {}
}
