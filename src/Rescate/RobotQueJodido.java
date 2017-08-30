package Rescate;

import java.util.ArrayList;

/**
 * clase contenedora de el robot que jodido que NO rescata personas, se encarga de detectar robot que lleven 
 * personas y dispararles y atrapar bonus de energia cuando se cuentre por debajo del 20%
 *
 */
public class RobotQueJodido extends Robot{

	
	/**
	 * Constructor de la clase RobotQueJodido
	 * @param posicionX
	 * @param posicionY
	 * @param escenario
	 */
	public RobotQueJodido(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);
	}
	
	//TODO NO DEBE SALVAR PERSONAS
	@Override
	public void jugar() {
		this.getRadar().escanear();
		if (this.getNivelEnergia() > 250){
			for(int i=0; i<this.getRadar().getElementosVistos().size(); i++){
				Elemento e = this.getRadar().getElementosVistos().get(i);
				if(e instanceof Robot){
					Robot r = (Robot) e;
					if (r.isLlevaPersona()){
						disparar();
					}
				}
			}
		}
		else{
			this.getRadar().escanear();
			for(int i=0; i< this.getRadar().getElementosVistos().size() ;i++){
				Elemento e = this.getRadar().getElementosVistos().get(i);
				if(e instanceof BonusBateria){
					disparar();
				}
			}
		}
	avanzar();
	}

	@Override
	public void elementosVistos(ArrayList<Elemento> elementos) {	
		for (int i =0; i<elementos.size(); i++){
			Elemento e = elementos.get(i);
			if (e instanceof BonusBateria){
				System.out.println("sdcasdcasdcas");
				this.disparar();
			}
		}
		
	}
	
}
