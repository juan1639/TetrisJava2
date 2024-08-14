package com.tetris.juan1639.utilidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class AreaTexto {
	
	private int size;
	private int x;
	private int y;
	private String txt;
	private Integer[] rgb;

	public AreaTexto(int size, int x, int y, String txt, Integer[] rgb) {

		this.size = size;
		this.x = x;
		this.y = y;
		this.txt = txt;
		this.rgb = rgb;
	}
	
	public AreaTexto() {}

	public void dibuja(Graphics g) {
		
		Integer[] rgb = this.rgb;

		Font fuente = new Font("Helvetica", Font.BOLD, this.size);
		FontMetrics fm = g.getFontMetrics(fuente);
		final int ancho = fm.stringWidth(this.txt);

		g.setFont(fuente);
		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		g.drawString(this.txt, this.x - ancho / 2, this.y);
	}
}
