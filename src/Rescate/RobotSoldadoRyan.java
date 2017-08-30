package Rescate;

import java.util.ArrayList;

/**
 * clase encargada de las acciones del robot soldado ryan el cual, no ataca a los robot pero se encarga de
 * rescatar a las pesonar de la zona de rescate
 */
public class RobotSoldadoRyan extends Robot{
	
	
	/**
	 * Constructor de la clase Robot Soldado ryan
	 * @param posicionX la posicion eje x
	 * @param posicionY la posicion eje y
	 * @param escenario Escenario generico
	 */	
	public RobotSoldadoRyan(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);		
	}

	/**
	 * en su turno pregunta si su nivel de escudo se encuentra por encima del 40% realiza el mismo jugar que la 
	 * clase padre robot, en caso de encotrarse por debajo del porcentaje analiza la lista de elementos detectados
	 * y al encontrar un bonus escudo le dispara para obtenerlo
	 */
	@Override
	public void jugar() {
		this.getRadar().escanear();
		if (this.getNivelEscudo()>40){
			//solo avanza
		}
		else{ 												//tiene poco nivel de energia
			for(int i=0; i<this.getRadar().getElementosVistos().size(); i++){
				Elemento e = this.getRadar().getElementosVistos().get(i);
				if (e instanceof BonusEscudo){
					disparar();
				}
			}
		}
		avanzar();
	}

	@Override
	public void elementosVistos(ArrayList<Elemento> elementos) {
		// TODO Auto-generated method stub
		
	}
	
}
	

