package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Rescate.Config;
import Rescate.Elemento;
import Rescate.Escenario;
import Rescate.Robot;


public class JuegoUI extends JFrame implements JuegoListener{
	/**
	 * Clase JuegoUI
	 */
	private static final long serialVersionUID = 1L;
	private Config config = new Config();
	private Tablero tableroJuego;
	private Escenario escenario;
	
	public JuegoUI(Escenario escenario){
		this.escenario = escenario;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tableroJuego = new Tablero(escenario);
		tableroJuego.setBackground(Color.BLACK);
		tableroJuego.setForeground(Color.red);
		tableroJuego.setLocation(250, 250);		
		

		Container panel = this.getContentPane(); //PANEL		
		panel.setLayout(new BorderLayout());		
		
		
		agregarMenu(panel);		
		
				
//		VistaConsola(panel);
		
		panel.add(tableroJuego, BorderLayout.CENTER);
 
		this.setSize(config.getAnchoTablero(),config.getAltoTablero());			
		this.setVisible(true);	
		
		
	}
 

	public void VistaConsola(Container panel){
		
		
		JTextArea  texto = new JTextArea();
		texto.setVisible(true);
		texto.setSize(1000,1500);
		texto.setLayout(new BorderLayout());
		texto.setEditable(false);

		JTextArea  texto1 = new JTextArea();
		texto1.setVisible(true);
		texto1.setSize(1000,1500);
		texto1.setLayout(new BorderLayout());
		texto1.setEditable(false);

		for (int i=0; i< escenario.getElementos().size(); i++){											
			Elemento elemento = escenario.getElementos().get(i); 
			if (elemento instanceof Robot){
				Robot e1 = (Robot) elemento;
				texto.setText(null);
				texto.append("Robot: "+ e1.getClass().getName()+
							"\nVelocidad "+ e1.getVelocidad()+
							",\n Municion " + e1.getCantidadMunicion()+
							",\n Bomba "+ e1.getCantidadBomba());
			}
		}
		
		texto1.append("sdcasdcsadca");
		panel.add(texto, BorderLayout.SOUTH);
		
		panel.add(texto1, BorderLayout.SOUTH);
	}

	
	

	/**
	 * dijuba nuevamente todos los elementos que se encuentran el GUI
	 */
	@Override
	public void actualizar() {
		this.tableroJuego.actualizar();
		
		addKeyListener(tableroJuego);
		
	}
	
	
	/**
	 * Agregamos elementos menu al panel del juego
	 * @param panel
	 */
	public void agregarMenu(Container panel){
		//menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(800,20);
		
		JMenuItem mntmPausar = new JMenuItem("Pausar");		
		mntmPausar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				for(int i=0; i<escenario.getElementos().size(); i++){
					Elemento e1 = escenario.getElementos().get(i);
					e1.getEscenario().getConfig().setPausa(true);
				}
				JOptionPane.showMessageDialog(tableroJuego, "JUEGO PAUSADO");
			}
		});
		
		//AÑADIMOS EL BOTON DE PAUSA
		menuBar.add(mntmPausar);
		
		
		JMenuItem mntmReanudar = new JMenuItem("Reanudar");
		mntmReanudar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
					for(int i=0; i<escenario.getElementos().size(); i++){
						Elemento e1 = escenario.getElementos().get(i);
						e1.getEscenario().getConfig().setPausa(false);
					}
					}	
			});
		
		menuBar.add(mntmReanudar);
		
		//LO AÑADIMOS AL CONTAINER
		panel.add(menuBar);
	}


	@Override
	public void actualizarEstado(ArrayList<Elemento> elementos) {
		// TODO Auto-generated method stub
		
	}
	
	
	
 
}
