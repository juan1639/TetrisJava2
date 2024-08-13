package com.tetris.juan1639.logicaArrayFondo;

import java.awt.Color;
import java.awt.Graphics;

import com.tetris.juan1639.audio.Sonidos;
import com.tetris.juan1639.settings.Settings;

public class Fondo {
	
	private static Sonidos sonido;

	private int columna;
	private int fila;
	private int ancho;
	private int alto;
	private Boolean valor;

	public Fondo(int columna, int fila, int ancho, int alto) {

		this.columna = columna;
		this.fila = fila;
		this.ancho = ancho;
		this.alto = alto;
		this.valor = false;
		
		sonido = new Sonidos();
	}

	public void dibuja(Graphics g) {

		Color fondoNada = new Color(70, 62, 4);
		Color fondoRastroPieza = new Color(128, 128, 128);

		int x = this.columna * this.ancho;
		int y = this.fila * this.alto;

		if (this.valor) {

			g.setColor(fondoRastroPieza);
            g.fillRect(x, y, this.ancho, this.alto);

		} else {

			g.setColor(fondoNada);
            g.fillRect(x, y, this.ancho, this.alto);
		}
	}

	public static void check_lineDone(Settings sett) {

		/*
		 * if (!sett.isCheckeando_matriz()) { return; }
		 */

		int lineas_alavez = 0;
		int filas = sett.TILES_HEIGHT;
		int columnas = sett.TILES_WIDTH;

		for (int i = filas - 1; i > 0; i --) {

			Fondo[] matrizLinea = sett.tileFondo[i];

			Boolean hasta_cuatro = true;

			while (hasta_cuatro) {

				int contador_cols = 0;

				for (Fondo cols: matrizLinea) {

					if (cols.isValor()) {
						contador_cols ++;
					}
				}

				/*
				 * if (contador_cols == columnas) {
				 * 
				 * int actualizarLineas = sett.getLineas(); actualizarLineas ++;
				 * sett.setLineas(actualizarLineas); System.out.println("Lineas: " +
				 * sett.getLineas()); sonido.cargar_audio("src/disparo.wav");
				 * sonido.play_sonido();
				 * 
				 * lineas_alavez ++; actualizar_matrizFondo(sett, i, filas, columnas);
				 * 
				 * } else {
				 * 
				 * hasta_cuatro = false; break; }
				 */
			}
		}

		// **** esta 1ra infolineas NOVALE *******
		//instancia_infoLineas(lineas_alavez);
		//sett.setCheckeando_matriz(false);
	}

	private static void actualizar_matrizFondo(Settings sett, int filaActual, int filas, int columnas) {

		for (int i = filaActual; i > 0; i --) {
			for (int ii = 0; ii < columnas; ii ++) {

				if (sett.tileFondo[i - 1][ii].isValor()) {
					sett.tileFondo[i][ii].setValor(true);

				} else {
					sett.tileFondo[i][ii].setValor(false);
				}
			}
		}
	}

	// ==================================================================
	// 	G E T T E R S  &  S E T T E R S
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
