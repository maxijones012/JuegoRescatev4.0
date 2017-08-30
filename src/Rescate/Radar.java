package Rescate;

import java.awt.Polygon;
import java.util.ArrayList;



public class Radar implements RadarListener{
	private int anguloApertura;
	private int alcance;
	private int alcanceMaximo=10;
	private int direccion;
	private Elemento duenio;  
	private Escenario escenario;
	
	private ArrayList<RadarListener> radarListener ;
	private ArrayList<Elemento> elementosVistos;
	
	
	
	/**
	 * 	crea un radar y se lo asigna a un robot
	 * @param satelite robot al que se le agrega el radar
	 */	
	public Radar(Elemento elemento){
		this.duenio= elemento;
		this.escenario = elemento.getEscenario();
		this.anguloApertura=elemento.getEscenario().getConfig().getAnguloApertura();
		this.alcance=elemento.getEscenario().getConfig().getAlcance();
		this.alcanceMaximo=elemento.getEscenario().getConfig().getAlcanceMaximo();
		this.elementosVistos= new ArrayList<Elemento>();
		this.radarListener= new ArrayList<RadarListener>();
	}
	
	
	/**
	 * gira con respecto a un  angula dado
	 * @param angulo
	 */
	public void girar (int angulo){
		int dirNueva = this.getDireccion() + angulo;
		while (dirNueva >= 360){
			dirNueva = dirNueva - 360;
		}
		this.setDireccion(dirNueva);
	}
	
	
	/**
	 * Escanea para detectar  elementos dentro de su alcance e informar a los que
	 * lo escuchan
	 */
   public void escanear(){
	   this.elementosVistos=null;
	   this.elementosVistos = this.escenario.detectarElementos(this);

	   for (RadarListener escuchadores: this.radarListener){
		   
		   if (this.elementosVistos.size() >0){
			   System.out.println("");
		   }
		   
		   escuchadores.elementosVistos(this.elementosVistos);
	   }
   }

	
	
	/**
	 * Utiliza el poligono para detectar elementos en un angulo determinado por su distrancia
	 * @return area de cobertura que cubre el robot
	 */
	public Polygon getAreaCobertura() {

	   	Polygon areaCobertura = new Polygon();
	  
	   	
		int duenioX= (int)duenio.getPosicion().getX()+10; 
		int duenioY= (int)duenio.getPosicion().getY()+10;
		
		int direccion = this.getDireccion();	
		
		//APUNTA CON LA MISMA DIRECCION DEL ROBOT
		if (this.getDuenio() instanceof Robot){
			direccion +=( (Robot)this.getDuenio()  ).getDireccion();
		}
		
		areaCobertura.addPoint(duenioX, duenioY);
		
		for (int i = ((direccion)-(this.getAnguloApertura()/2));
				 i < (direccion)+(this.getAnguloApertura()/2); 
				 i++) {
			
			int x= (int) (duenioX+ Math.cos(Math.toRadians(i))*this.getAlcance());
			int y= (int) (duenioY+ Math.sin(Math.toRadians(i))*this.getAlcance());
		
			areaCobertura.addPoint(x, y);
		}		
		
		
		return areaCobertura; //devuelvo el poligono
	}
	
	
	/***
	 * agrega un elemento a su lista de escuchadores
	 * @param listener
	 */
	public void addRadarListener(RadarListener listener) {
		this.radarListener.add(listener);
	}

	
	/***
	 * elimina un elemento de su lista de escuchadores
	 * @param listener
	 */
	public void removeRadarListener(RadarListener listener) {
		this.radarListener.remove(listener);
	}
	
	/**
	 * Devuelve el angulo de Apertura de vision del radar
	 * @return angulo de apertura del radar
	 */
	public int getAnguloApertura() {
		return anguloApertura;
	}
	
	
	/**
	 * Setea el angulo de apertura de vision del radar
	 * @param anguloApertura 
	 * 
	 */
	public void setAnguloApertura(int anguloApertura) {
		this.anguloApertura = anguloApertura;
		double angulo=(double)(anguloApertura);
		this.alcance=(int)(this.alcance - (this.alcanceMaximo*(angulo/360)));
	
	}
	
	
	/**
	 * Muestra el alcance que tiene el radar
	 * @return
	 */
	public int getAlcance() {
		return alcance;
	}
	
	
	
	/**
	 * Setea el alcance que tiene el radar
	 * @param alcance
	 */
	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}
	
	
	/**
	 * muestra la direccion del radar
	 * @return
	 */
	public int getDireccion() {
		return direccion;
	}

	
	
	/**
	 * setea la direccion del radar
	 * @param direccion
	 */
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public void elementosVistos(ArrayList<Elemento> elemento) {	
	}


	public void addElementosVistos(Elemento elementosVistos) {
		this.elementosVistos.add(elementosVistos);
	}
	
	public void removeElementosVistos(Elemento elementoVistos){
		this.elementosVistos.remove(elementoVistos);
	}


	public Elemento getDuenio() {
		return duenio;
	}


	public void setDuenio(Elemento duenio) {
		this.duenio = duenio;
	}


	public ArrayList<Elemento> getElementosVistos() {
		return elementosVistos;
	}

}

