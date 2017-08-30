package Grafica;

import java.util.ArrayList;

import Rescate.Elemento;

public interface JuegoListener {
	public void actualizar();

	public void actualizarEstado(ArrayList<Elemento> elementos);
}

