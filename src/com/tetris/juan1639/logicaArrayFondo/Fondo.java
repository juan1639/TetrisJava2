package com.tetris.juan1639.logicaArrayFondo;

import java.awt.Color;
import java.awt.Graphics;

import com.tetris.juan1639.audio.Sonidos;
import com.tetris.juan1639.settings.Settings;

public class Fondo {

	private static Sonidos sonido = new Sonidos();
	
	private int columna;
	private int fila;
	private int ancho;
	private int alto;
	private Boolean valor;

	public Fondo(Integer columna, Integer fila, Integer ancho, Integer alto) {

		this.columna = columna;
		this.fila = fila;
		this.ancho = ancho;
		this.alto = alto;
		this.valor = false;

		// if (this.fila == 3 && this.columna == 3) this.valor = true;

		sonido = new Sonidos();
	}

	public void dibuja(Graphics g) {

		Color fondoNada = new Color(72, 71, 70);
		Color fondoRastroPieza = new Color(109, 99, 10);

		int x = this.columna * this.ancho;
		int y = this.fila * this.alto;

		if (this.valor) {

			g.setColor(fondoRastroPieza);

		} else {
			g.setColor(fondoNada);
		}

		g.fillRect(x, y, this.ancho, this.alto);
	}

	public static void check_lineDone(Settings sett) {

		if (!sett.getCheckeandoMatriz()) {
			return;
		}

		Integer lineas_alavez = 0;
		Integer filas = sett.TILES_HEIGHT;
		Integer columnas = sett.TILES_WIDTH;

		for (Integer i = filas - 1; i > 0; i--) {

			Fondo[] matrizLinea = sett.tileFondo[i];

			Boolean hasta_cuatro = true;

			while (hasta_cuatro) {

				Integer contador_cols = 0;

				for (Fondo cols : matrizLinea) {

					if (cols.isValor()) {
						contador_cols++;
					}
				}

				if (contador_cols == columnas) {

					int actualizarLineas = sett.getLineas();
					actualizarLineas++;
					sett.setLineas(actualizarLineas);
					System.out.println("Lineas: " + sett.getLineas());
					
					sonido.cargarAudio(sett.urlaudio.getLineaCrash());
					sonido.playSonido();

					lineas_alavez++;
					actualizar_matrizFondo(sett, i, filas, columnas);

				} else {

					hasta_cuatro = false;
					break;
				}
			}
		}
		
		Integer nuevoNivel = (int) Math.floor(sett.getLineas() / 7);
		sett.setNivel(nuevoNivel + 1);
		
		// Para que checkee SOLO una vez lo ponemos a False
		sett.setCheckeandoMatriz(false);
	}

	private static void actualizar_matrizFondo(Settings sett, int filaActual, int filas, int columnas) {

		for (int i = filaActual; i > 0; i--) {
			for (int ii = 0; ii < columnas; ii++) {

				if (sett.tileFondo[i - 1][ii].isValor()) {
					sett.tileFondo[i][ii].setValor(true);

				} else {
					sett.tileFondo[i][ii].setValor(false);
				}
			}
		}
	}

	// ==================================================================
	// G E T T E R S & S E T T E R S
	//
	// ------------------------------------------------------------------
	public int getColumna() {
		return this.columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getFila() {
		return this.fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public Boolean isValor() {
		return this.valor;
	}

	public void setValor(Boolean valor) {
		this.valor = valor;
	}
}
