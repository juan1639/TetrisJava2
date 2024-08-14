package com.tetris.juan1639.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.tetris.juan1639.audio.Sonidos;
import com.tetris.juan1639.interfaces.IResetControlesEstados;
import com.tetris.juan1639.logicaPieza.NextPieza;
import com.tetris.juan1639.logicaPieza.Pieza;
import com.tetris.juan1639.marcadores.Marcadores;
import com.tetris.juan1639.settings.Colores;
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

	private Marcadores lineas;
	private Marcadores nivel;
	private Marcadores hi;
	private Object[] marcadores;
	
	JButton botonInicio;
	public static final String NUEVA_PARTIDA = "Nueva Partida";
	public static final String MUSICA_ON = "Musica ON";
	public static final String MUSICA_OFF = "Musica OFF";

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

		setPreferredSize(new Dimension(settings.TILE_X * settings.TILES_WIDTH * 2,
				settings.TILE_Y * settings.TILES_HEIGHT + settings.TILE_Y));
		
		crearBotonInicio();
		
		comenzar();
	}

	private void comenzar() {
		
		Instancias.instanciarMatrizFondo(settings.tileFondo, settings.TILES_HEIGHT, settings.TILES_WIDTH,
				settings.TILE_X, settings.TILE_Y);

		if (settings.getOtraPieza()) {
			piezas = Instancias.instanciarPieza(settings, pieza, verNextPieza, piezaFactory);
		}

		marcadores = Instancias.instanciarMarcadores(settings, lineas, nivel, hi);

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

		lineas = (Marcadores) marcadores[0];
		nivel = (Marcadores) marcadores[1];
		hi = (Marcadores) marcadores[2];

		lineas.dibuja(g, settings.getLineas(), this);
		nivel.dibuja(g, settings.getNivel(), this);
		hi.dibuja(g, settings.getHiScore(), this);

		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// System.out.println("running...");

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
	
	private void crearBotonInicio() {
		
		Integer[] rgb;
		JLabel[] layout = new JLabel[12];
		
		rgb = Colores.TXT_NUEVA_PARTIDA;
		
		GridLayout miFlowLayout = new GridLayout(6, 2, 2, 2);
		setLayout(miFlowLayout);
		
		for (int i = 0; i < 12; i ++) {
			
			if (i == 1) {
				
				botonInicio = new JButton();
				botonInicio.setText(NUEVA_PARTIDA);
				botonInicio.setFont(new Font("arial", Font.BOLD, settings.SIZE_TXT_NUEVAPARTIDA));
				botonInicio.setEnabled(true);
				botonInicio.setFocusable(false);
				botonInicio.setForeground(new Color(rgb[0], rgb[1], rgb[2]));
				botonInicio.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
				
				botonInicio.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						distintasFuncionesBotonInicio();
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						super.mouseEntered(e);
						
						Integer[] rgb = Colores.BG_HOVER_BOTON_INICIO;
						botonInicio.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
						botonInicio.setFont(new Font("arial", Font.BOLD, settings.SIZE_TXT_NUEVAPARTIDA + 4));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						super.mouseExited(e);
						
						Color bgColor = UIManager.getColor("Button.background");
						botonInicio.setBackground(bgColor);
						botonInicio.setFont(new Font("arial", Font.BOLD, settings.SIZE_TXT_NUEVAPARTIDA));
					}
				});
				
				add(botonInicio);
				
			} else {
				layout[i] = new JLabel();
				add(layout[i]);
			}
		}
	}
	
	private void distintasFuncionesBotonInicio() {
		
		if (settings.estado.isPreJuego()) {
			
			System.out.println("Start game!");
			resetEstados(false, settings);
			settings.estado.setEnJuego(true);
			
			sonido.cargarAudio(settings.urlaudio.getMusicaFondo());
			sonido.playSonido();
			botonInicio.setText(MUSICA_ON);
			
			return;
			
		} else if (settings.estado.isEnJuego()) {
			
			if (sonido.isRunning()) {
				sonido.detenerSonido();
				botonInicio.setText(MUSICA_OFF);
				
				
			} else {
				sonido.playSonido();
				botonInicio.setText(MUSICA_ON);
			}
			
			return;
		}
	}
}
