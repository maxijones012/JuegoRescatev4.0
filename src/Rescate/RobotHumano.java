package Rescate;

import java.util.ArrayList;

public class RobotHumano extends Robot{	
	
	
	public RobotHumano(int posicionX, int posicionY, Escenario escenario) {
		super(posicionX, posicionY, escenario);

	}
	
	
	@Override
	public void jugar() {
		this.getRadar().escanear();
		
		if (super.getConfig().isDerecha()){
			super.setDireccion(0);		
			super.avanzar();
		}
		else{
			if (super.getConfig().isIzquierda()){
				super.setDireccion(180);
				super.avanzar();
			}
			else{
				if (super.getConfig().isArriba()){
					super.setDireccion(270);
					super.avanzar();
				}
				else{
					super.setDireccion(90); 
					super.avanzar();
				}
			}
		}				
		
		
	}
	
	
	
	@Override
	public void chocarContra(Elemento elemento) {
		super.chocarContra(elemento);
		
	}

	@Override
	public void chocarContraPared() {
		super.setDireccion(this.getDireccion()-180);
		super.avanzar();
	}
	
	@Override
	public void chocarContraBonusBateria(BonusBateria bonus) {
	}
	
	@Override
	public void chocarContraBonusEscudo(BonusEscudo bonus) {
	}
	
	@Override
	public void chocarContraMunicion(Municion municion) {
	}
	@Override
	public void chocarContraRefugio(Refugio refugio) {
	}
	
	@Override
	public void chocarContraBomba(Bomba bomba) {
	}
	
	@Override
	public void chocarContraZonaRescate(ZonaRescate zonaRescate) {
	}


	@Override
	public void elementosVistos(ArrayList<Elemento> elementos) {
		// TODO Auto-generated method stub
		
	}

}
