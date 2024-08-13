package com.tetris.juan1639.logicaPieza;

import java.awt.Color;
import java.awt.Graphics;

public class NextPieza {

	private Integer[] colorAreaNextPieza = {89, 89, 89, 182, 183, 184};

	private Integer x;
	private Integer y;
	private Integer[][] idPieza;
	private Integer[] colorPieza;

	public NextPieza(int x, Integer y, Integer[][] idPieza, Integer[] colorPieza) {

		this.x = x;
		this.y = y;
		this.idPieza = idPieza;
		this.colorPieza = colorPieza;
	}

	public void dibuja(Graphics g, Integer ancho, Integer alto) {

		area_nextPieza(g, ancho, alto);

		Integer[] rgb = this.colorPieza;

		Integer rotacion_idPieza = 0;
		Integer fin = rotacion_idPieza + Pieza.ROTACIONES_LOOP;

		for (Integer i = rotacion_idPieza; i < fin; i ++) {

			Integer x = (this.x + this.idPieza[i][0]) * ancho;
			Integer y = (this.y + this.idPieza[i][1]) * alto;

			//g.setColor(new Color(rgb[3], rgb[4], rgb[5]));
			//g.fillRect(x, y, ancho, alto);

			g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
            int[] polX = {x, x + 30, x + 30, x};
            int[] polY = {y, y, y + 30, y};
            g.fillPolygon(polX, polY, 3);

            g.setColor(new Color(rgb[3], rgb[4], rgb[5]));
            //g.fillRect(x, y, ancho, alto);
            int[] polX2 = {x, x, x + 30, x};
            int[] polY2 = {y, y + 30, y + 30, y};
            g.fillPolygon(polX2, polY2, 3);
		}
	}

	public void area_nextPieza(Graphics g, Integer ancho, Integer alto) {

		Integer x = (this.x - 2) * ancho;
		Integer y = (this.y - 3) * alto;
		Integer areaAncho = ancho * 6;
		Integer areaAlto = alto * 7;

		Integer[] rgb = colorAreaNextPieza;

		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		g.fillRoundRect(x, y, areaAncho, areaAlto, 16, 16);
		g.setColor(new Color(rgb[3], rgb[4], rgb[5]));
		g.drawRoundRect(x, y, areaAncho, areaAlto, 16, 16);
	}
}
