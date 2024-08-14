package com.tetris.juan1639.logicaPieza;

import java.awt.Color;
import java.awt.Graphics;

import com.tetris.juan1639.audio.Sonidos;
import com.tetris.juan1639.settings.Settings;

public class Pieza {
	
	public static final Integer ROTACIONES_LOOP = 4;

	private Integer x;
	private Integer y;
	private Integer[][] idPieza;
	private Integer columnas;
	private Integer filas;
	private Integer[] colorPieza;
	private Integer rotacion;
	private Settings settings;
	
	private Sonidos sonido = new Sonidos();

	public Pieza(
			Integer x,
			Integer y,
			Integer[][] idPieza,
			Integer col,
			Integer filas,
			Integer[] colorPieza,
			Settings settings) {

		this.x = x;
		this.y = y;
		this.idPieza = idPieza;
		this.columnas = col;
		this.filas = filas;
		this.colorPieza = colorPieza;
		this.settings = settings;
		
		this.rotacion = 0;
	}

	public void dibuja(Graphics g, Integer ancho, Integer alto) {
		
		Integer[] rgb = this.colorPieza;

		Integer rotacion_idPieza = this.rotacion * ROTACIONES_LOOP;
		Integer fin = rotacion_idPieza + ROTACIONES_LOOP;

		for (Integer i = rotacion_idPieza; i < fin; i ++) {

			Integer x = (this.x + this.idPieza[i][0]) * ancho;
			Integer y = (this.y + this.idPieza[i][1]) * alto;

			//g.setColor(new Color(rgb[3], rgb[4], rgb[5]));
			//g.fillRect(x, y, ancho, alto);

			g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
            int[] polX = {x, x + settings.TILE_X, x + settings.TILE_X, x};
            int[] polY = {y, y, y + settings.TILE_Y, y};
            g.fillPolygon(polX, polY, 3);
            
            g.setColor(new Color(rgb[3], rgb[4], rgb[5]));
            //g.fillRect(x, y, ancho, alto);
            int[] polX2 = {x, x, x + settings.TILE_X, x};
            int[] polY2 = {y, y + settings.TILE_Y, y + settings.TILE_Y, y};
            g.fillPolygon(polX2, polY2, 3);
		}
	}
	
	public void actualiza(Settings sett) {

		
		if (!sett.estado.isEnJuego()) {
			return;
		}
		
		// =================================================
		if (sett.controles.isIzquierda()) {

			this.x --;
			if (check_colision(sett)) {
				this.x ++;
			}

			sett.controles.setIzquierda(false);

		// -------------------------------------------------
		} else if (sett.controles.isDerecha()) {

			this.x ++;
			if (check_colision(sett)) {
				this.x --;
			}

			sett.controles.setDerecha(false);

		// -------------------------------------------------
		} else if (sett.controles.isAbajo()) {

			this.y ++;
			if (check_colision(sett)) {
				this.y --;

				if (this.y <= sett.Y_INICIAL) {

					sett.estado.setGameOver(true);
					sett.estado.setEnJuego(false);
					sett.setPausaRejugar(sett.TIEMPO_PAUSA_REJUGAR);
				}
				
				sonido.cargarAudio(settings.urlaudio.getPosaPieza1());
				sonido.playSonido();
				
				sett.setOtraPieza(true);
				sett.setCheckeandoMatriz(true);
				dejar_rastro(sett);
			}

			sett.controles.setAbajo(false);

		// -------------------------------------------------
		} else if (sett.controles.isRotar()) {

			int bck_rotacion = this.rotacion;
			this.rotacion ++;

			if (this.rotacion >= ROTACIONES_LOOP) {
				this.rotacion = 0;
			}

			if (check_colision(sett)) {
				this.rotacion = bck_rotacion;
			}

			System.out.println("Rot:" + this.rotacion);
			sett.controles.setRotar(false);
		}
	}

	public Boolean check_colision(Settings sett) {

		int rotacion_idPieza = this.rotacion * ROTACIONES_LOOP;
		int fin = rotacion_idPieza + ROTACIONES_LOOP;

		for (Integer i = rotacion_idPieza; i < fin; i ++) {

			Integer colX = this.x + this.idPieza[i][0];
			Integer colY = this.y + this.idPieza[i][1];

			if (colX > this.columnas - 1 || colX < 0) {
				return true;
			}

			if (colY > this.filas - 1 || colY < 0) {
				return true;
			}

			if (sett.tileFondo[colY][colX].isValor()) {
				return true;
			}
		}

		return false;
	}

	private void dejar_rastro(Settings sett) {

		Integer rotacion_idPieza = this.rotacion * ROTACIONES_LOOP;
		Integer fin = rotacion_idPieza + ROTACIONES_LOOP;

		for (Integer i = rotacion_idPieza; i < fin; i ++) {

			Integer rastroX = this.x + this.idPieza[i][0];
			Integer rastroY = this.y + this.idPieza[i][1];

			sett.tileFondo[rastroY][rastroX].setValor(true);
		}
	}

	public static void gravedadPiezas(Settings sett) {

		Integer[] gravedad = sett.getGravedad();
		// ***********************************************
        //Integer nivel = sett.getNivel();
		Integer nivel = 1;
		// ***********************************************
        Integer contador = sett.getIncrementoDificultad();
        contador ++;

        if (contador >= gravedad[nivel]) {
            contador = 0;
            sett.controles.setAbajo(true);
        }

        sett.setIncrementoDificultad(contador);
	}
	
	// =======================================================================
	// 	G E T T E R S  &  S E T T E R S
	// 	
	// -----------------------------------------------------------------------
	public Integer getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return this.y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer[][] getIdPieza() {
		return this.idPieza;
	}

	public void setIdPieza(Integer[][] idPieza) {
		this.idPieza = idPieza;
	}

	public Integer getRotacion() {
		return this.rotacion;
	}

	public void setRotacion(Integer rotacion) {
		this.rotacion = rotacion;
	}
}
