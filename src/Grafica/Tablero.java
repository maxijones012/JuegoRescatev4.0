package Grafica;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import Rescate.Config;
import Rescate.Elemento;
import Rescate.Escenario;
import Rescate.Movible;
import Rescate.Robot;

public class Tablero extends Canvas implements KeyListener{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private HashMap<String, BufferedImage> imagenes;
	private Config config; 
	private Escenario escenario;
	private final int ANCHO = 30;//robot humano
	private final int ALTO = 30;//robot humano
	
//	
	private Point posicionRobot;
//	private int angulo; 
//	
	
	public Tablero(Escenario escenario){
		this.escenario = escenario;
		this.config=escenario.getConfig();
		posicionRobot = new Point();
	}




	/**
	 * Actualiza el juego en cada pasada
	 */
	public void actualizar(){
		
		limpiar();
		
 		dibujar(); 				
		

		//Muestra el buffer sobre el cual se estuvo dibujando.
		this.createBufferStrategy(1);
		this.getBufferStrategy().show();
	}

	
	
	/**
	 * Dibuja los elementos
	 */
	private void dibujar() {
				
		for (int i=0; i< escenario.getElementos().size(); i++){
			//pinto cada elemento
			Elemento e = escenario.getElementos().get(i);//creo un elemento manipulable			
			e.getEscenario().getConfig();
			int x = (int)e.getPosicion().getX();
			int y = (int)e.getPosicion().getY();
			
			//busca el nombre de la clase con el nombre del archivo
			BufferedImage eImagen =  getImagen(e.getClass().getName());

			if (e instanceof Movible){
				Movible e2 = (Movible) e;				
				eImagen = rotar(eImagen, e2.getDireccion());
				if (e2 instanceof Robot){
					Robot robot = (Robot) e2;
					this.getG2D().drawPolygon(robot.getRadar().getAreaCobertura());					
				}
			}
			
			
			this.getG2D().drawImage(eImagen, x, y, e.getTamanio().getAncho(), e.getTamanio().getAlto(), null);//age(eImagen, x, y, null);				
		
		}
	}



	private Graphics2D getG2D(){
		
		if (this.getBufferStrategy() == null)
			return (Graphics2D) this.getGraphics();
		else 
			return (Graphics2D)this.getBufferStrategy().getDrawGraphics();		
	}
	
	 
	@Override
	public void paint(Graphics g) {
		actualizar();
	}
	
	/**
	 * Dibuja el fondo
	 */
	private void limpiar(){
		this.getG2D().drawImage(getImagen("fondo"), 0, 0, getWidth(), getHeight(), null);
	}
	
	
	private BufferedImage getImagen(String key){

		if (imagenes == null)
			imagenes = new HashMap<String, BufferedImage>();
		
		BufferedImage img = imagenes.get(key);

		if (img == null) {
			img = cargarImagen(System.getProperty("user.dir") + File.separator + "img" + File.separator + key + ".png");

			if (img != null){

				if (key != "fondo")
					img = cambiarTamanio(img, ANCHO, ALTO);
				
				imagenes.put(key, img);
			}
		}
		
		return img;
	}
	
	private BufferedImage cargarImagen(String fileName){
		try {
			return ImageIO.read(new File(fileName));
		} catch (Exception e) {
			System.out.println("No se encontro la imagen " + fileName);

			return null;
		}
		
	}
	
	private BufferedImage cambiarTamanio(BufferedImage img, int ancho, int alto){

		BufferedImage newImage = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);

		Graphics g = newImage.createGraphics();
		g.drawImage(img, 0, 0, ancho, alto, null);
		g.dispose();
		
		return newImage;
	}

	 
	
	public void inicializar() {

		this.setFocusable(true);
		
		//Crea dos buffers para dibujar.
		this.createBufferStrategy(2);
		
		this.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			config.setAbajo(true);
			config.setArriba(false);
			config.setIzquierda(false);
			config.setDerecha(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP){
			config.setAbajo(false);
			config.setArriba(true);
			config.setIzquierda(false);
			config.setDerecha(false);	
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			config.setAbajo(false);
			config.setArriba(false);
			config.setIzquierda(false);
			config.setDerecha(true);	
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			config.setAbajo(false);
			config.setArriba(false);
			config.setIzquierda(true);
			config.setDerecha(false);	
		}

		actualizar();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("*Tecla presionada*");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("keyTyped - " + posicionRobot.x);
	}
	

	
	
	public BufferedImage rotar(BufferedImage image, int angulo){
		Dimension dim = calcularDimension(image, angulo);

		int w2 = (int)dim.getWidth();
		int h2 = (int)dim.getHeight();		

		int w = image.getWidth();
		int h = image.getHeight();		

		BufferedImage image2 = new BufferedImage(w2, h2, BufferedImage.TRANSLUCENT);

		Graphics2D g2d = (Graphics2D)image2.getGraphics();

		double x = (w2- w) / 2.0;
		double y = (h2-h) / 2.0;

		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(angulo), w/2, h/2);

		g2d.drawRenderedImage(image, at);

		return image2;
	}

	/**
	 * Calcula la dimension
	 * @param img
	 * @param angulo
	 * @return
	 */
	private Dimension calcularDimension(BufferedImage img, int angulo){
		double w = (double)img.getWidth();
		double h = (double)img.getHeight();

		double x1 = Math.abs(w * Math.cos(Math.toRadians(angulo))); 
		double x2 = Math.abs(h * Math.sin(Math.toRadians(angulo)));

		double y1 = Math.abs(h * Math.cos(Math.toRadians(angulo))); 
		double y2 = Math.abs(w * Math.sin(Math.toRadians(angulo)));

		return new Dimension((int)(x1+x2), (int)(y1+y2));
	}



}

