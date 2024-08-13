package com.tetris.juan1639.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.tetris.juan1639.audio.Sonidos;
import com.tetris.juan1639.interfaces.IResetControlesEstados;
import com.tetris.juan1639.logicaPieza.NextPieza;
import com.tetris.juan1639.logicaPieza.Pieza;
import com.tetris.juan1639.settings.PlantillaPiezasFactory;
import com.tetris.juan1639.settings.Settings;

public class Ventana extends JPanel implements ActionListener, IResetControlesEstados {

	private static final long serialVersionUID = 6133593608232968591L;

	private Timer timer;
	private Sonidos sonido = new Sonidos();
	
	private Pieza pieza;
	private NextPieza verNextPieza;
	private PlantillaPiezasFactory piezaFactory;
	private Object[] piezas;

	private Settings settings;
	
	public Ventana() {

		inicializa();
	}

	private void inicializa() {

		settings = Settings.getInstancia();
		piezaFactory = PlantillaPiezasFactory.getInstancia();
		
		addKeyListener(new Controles());

		Integer[] rgb = settings.COLOR_FONDOS;
		setBackground(new Color(rgb[0], rgb[1], rgb[2]));

		setFocusable(true);
		setPreferredSize(
				new Dimension(settings.TILE_X * settings.TILES_WIDTH * 2, settings.TILE_Y * settings.TILES_HEIGHT));

		GridLayout miFlowLayout = new GridLayout(3, 2, 9, 9);
		setLayout(miFlowLayout);

		JButton boton1 = new JButton("Botón 1");
		boton1.setVisible(false);
		JButton boton2 = new JButton("Botón 2");
		JButton boton3 = new JButton("Botón 3");
		boton3.setVisible(false);
		JButton boton4 = new JButton("Botón 4");
		JButton boton5 = new JButton("Botón 5");
		boton5.setVisible(false);
		JButton boton6 = new JButton("Botón 6");
		add(boton1);
		add(boton2);
		add(boton3);
		add(boton4);
		add(boton5);
		//add(boton6);

		comenzar();
	}

	private void comenzar() {

		sonido.cargarAudio(settings.urlaudio.getGameOver());
		sonido.playSonido();

		Instancias.instanciarMatrizFondo(settings.tileFondo, settings.TILES_HEIGHT, settings.TILES_WIDTH,
				settings.TILE_X, settings.TILE_Y);
		
		if (settings.getOtraPieza()) {
			piezas = Instancias.instanciarPieza(settings, pieza, verNextPieza, piezaFactory);
		}
		
		timer = new Timer((Integer) (1000 / settings.FPS), this);
		timer.start();
		timer.setRepeats(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		renderiza(g);
	}

	private void renderiza(Graphics g) {
		
		for (Integer i = 0; i < settings.TILES_HEIGHT; i++) {
			for (Integer ii = 0; ii < settings.TILES_WIDTH; ii++) {

				settings.tileFondo[i][ii].dibuja(g);
			}
		}
		
		pieza = (Pieza) piezas[1];
		verNextPieza = (NextPieza) piezas[0];
		
		if (pieza != null) {
			pieza.dibuja(g, settings.TILE_X, settings.TILE_Y);
		}
		
		if (verNextPieza != null) {
			verNextPieza.dibuja(g, settings.TILE_X, settings.TILE_Y);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//System.out.println("running...");
		
		Pieza.gravedadPiezas(settings);
		pieza.actualiza(settings);
		
		if (settings.getOtraPieza()) {
			piezas = Instancias.instanciarPieza(settings, pieza, verNextPieza, piezaFactory);
		}
		
		repaint();
	}

	private class Controles extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (settings.estado.isEnJuego()) {

				if (key == KeyEvent.VK_LEFT) {
					resetControles(false, settings);
					settings.controles.setIzquierda(true);
				}

				if (key == KeyEvent.VK_RIGHT) {
					resetControles(false, settings);
					settings.controles.setDerecha(true);
				}

				if (key == KeyEvent.VK_DOWN) {
					resetControles(false, settings);
					settings.controles.setAbajo(true);
				}

				if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_CONTROL) {
					resetControles(false, settings);
					settings.controles.setRotar(true);
				}
			}

			if (key == KeyEvent.VK_ESCAPE) {
				Toolkit.getDefaultToolkit().beep();
				System.exit(0);
			}
		}
	}
}
