package Rescate;

import java.util.ArrayList;
import Grafica.PersonasRescatadas.PersonasRescatada;
/**
 * Clase ZonaRescate del juego
 *
 */
public class ZonaRescate extends Elemento implements PersonasRescatada{
											
	private ArrayList<Persona> personas;
	private Config config;
	private int i=0;

	/**
	 * Constructor de la clase ZonaRescate
	 * @param posicionX
	 * @param posicionY
	 * @param escenario
	 */
	public ZonaRescate(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
		this.config= escenario.getConfig();
		this.personas= new ArrayList<Persona>();
		this.setTamanio(new Tamanio(config.getAnchoZonaRescate(), config.getAltoZonaRescate()));		
		crearPersonas();
	}
	
	
	
	
	@Override
	public void jugar() {
	// TODO tendria que hacer algo?
			
	}
	@Override
	public void chocarContra(Elemento elemento) {
		elemento.chocarContraZonaRescate(this);	
	} 	

	@Override
	public void chocarContraRobot(Robot robot) {
		if (robot instanceof RobotQueJodido){
			//no puede rescatar personas de la zona de rescate
		}
		else{
			if (this.i<=10){
				if (!(robot.isLlevaPersona())){
					robot.rescatar(this.personas.get(i));
					this.personas.remove(i);
					i++;
				}			
			}
			else{
				System.out.println("zona de rescate NO TIENE MAS PERSONAS ");
			}
		}
	}
	
	
	
	@Override
	public void chocarContraMunicion(Municion municion) {}	

	@Override
	public void chocarContraBonusBateria(BonusBateria bonus) {}

	@Override
	public void chocarContraBonusEscudo(BonusEscudo bonus) {}

	
	
	
	
	
	
	/**
	 * Crea las personas que son rescatadas en el juego y las carga en la lista
	 */
	private void crearPersonas() {
		Persona p1 = new Persona("", "");
		personas.add(p1);
		Persona p2 = new Persona("", "");
		personas.add(p2);
		Persona p3 = new Persona("", "");
		personas.add(p3);
		Persona p4 = new Persona("", "");
		personas.add(p4);
		Persona p5 = new Persona("", "");
		personas.add(p5);
		Persona p6 = new Persona("", "");
		personas.add(p6);
		Persona p7 = new Persona("", "");
		personas.add(p7);
		Persona p8 = new Persona("", "");
		personas.add(p8);
		Persona p9 = new Persona("", "");
		personas.add(p9);
		Persona p10 = new Persona("", "");
		personas.add(p10);
		
	}
	
	
	
	
	
	
	
	
	
	

	
}
	