package Rescate;

import java.util.ArrayList;

/**
 * 
 * Clase refugio del juego
 *
 */
public class Refugio extends Elemento{
	
	ArrayList<Persona> personasEnRefugio = new ArrayList<>();
	/**
	 * Constructor de la clase Refugio 
	 * @param posicionX
	 * @param posicionY
	 * @param escenario
	 */
	public Refugio(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
		
	}

	@Override
	public void jugar() {} /*no realiza ningun movimiento en el jugar*/			
	

	
	@Override
	public void chocarContra(Elemento elemento) {	
		elemento.chocarContraRefugio(this);
	}
	
	
	@Override
	public void chocarContraBonusBateria(BonusBateria bonus) {}
	
	@Override
	public void chocarContraBonusEscudo(BonusEscudo bonus) {}

	/**
	 * Verifica el choque contra un robot
	 */
	public void chocarContraRobot(Robot robot) {
		
		//tiene personas (?
			if (robot.isLlevaPersona()){
				robot.dejarPersona(this);
				System.out.println("ROBOT DEJO PERSONA EN REFUGIO");
			}			
	}

	public void entregarPersonaEnRefugio(Persona personaCargada) {
		personasEnRefugio.add(personaCargada);
		
	}


	
	
	
	
}
