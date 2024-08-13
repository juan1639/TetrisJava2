package com.tetris.juan1639.settings;

import java.util.HashMap;

import com.tetris.juan1639.logicaArrayFondo.Fondo;

public class Settings {
	
	public final Integer FPS = 60;
	public final Integer[] COLOR_FONDOS = { 153, 154, 155, 72, 72, 72 };
	
	public final Integer TILE_X = 30;
	public final Integer TILE_Y = 30;
	public final Integer TILES_WIDTH = 14;
	public final Integer TILES_HEIGHT = 20;
	
	public final Fondo[][] tileFondo = new Fondo[TILES_HEIGHT][TILES_WIDTH];
	
	public HashMap<String, Integer> scores = new HashMap<>();
	
	// ============================================================
	// CONSTRUCTOR (SINGLETON)
	// 
	// ------------------------------------------------------------
	private Settings() {}
	// SINGLETON
	private static Settings INSTANCIA = new Settings();
	public static Settings getInstancia() {return INSTANCIA;}
	
	// ------------------------------------------------------------
	// Instancias Controles, Estado, UrlAudio
	// 
	// ------------------------------------------------------------
	public Controles controles = new Controles();
	public Estado estado = new Estado();
	public UrlAudio urlaudio = new UrlAudio();
	
	// ====================================================================
	public class Controles {

		private Boolean izquierda = false;
		private Boolean derecha = false;
		private Boolean abajo = false;
		private Boolean rotar = false;

		public Controles() {}

		public Boolean isIzquierda() {
			return izquierda;
		}

		public void setIzquierda(Boolean izquierda) {
			this.izquierda = izquierda;
		}

		public Boolean isDerecha() {
			return derecha;
		}

		public void setDerecha(Boolean derecha) {
			this.derecha = derecha;
		}
		
		public Boolean isAbajo() {
			return abajo;
		}

		public void setAbajo(Boolean abajo) {
			this.abajo = abajo;
		}
		
		public Boolean getRotar() {
			return rotar;
		}

		public void setRotar(Boolean rotar) {
			this.rotar = rotar;
		}
	}

	// ====================================================================
	public class Estado {

		private Boolean preJuego = true;
		private Boolean enJuego = false;
		private Boolean nivelSuperado = false;
		private Boolean gameOver = false;

		public Estado() {}

		public Boolean isPreJuego() {
			return preJuego;
		}

		public void setPreJuego(Boolean preJuego) {
			this.preJuego = preJuego;
		}
		
		public Boolean isEnJuego() {
			return enJuego;
		}

		public void setEnJuego(Boolean enJuego) {
			this.enJuego = enJuego;
		}

		public Boolean isNivelSuperado() {
			return nivelSuperado;
		}

		public void setNivelSuperado(Boolean nivelSuperado) {
			this.nivelSuperado = nivelSuperado;
		}

		public Boolean isGameOver() {
			return gameOver;
		}

		public void setGameOver(Boolean gameOver) {
			this.gameOver = gameOver;
		}
	}
	
	// ====================================================================
	public class UrlAudio {
		
		private String ruta = "media/";
		
		private String linea = ruta + "disparo.wav";
		private String gameOver = ruta + "gameover.wav";
		private String levelUp = ruta + "levelup.wav";
		private String musicaFondo = ruta + "russia-tetris-game-puzzle.wav";
		private String lineaCrash = ruta + "crash.wav";
		private String posaPieza1 = ruta + "dieThrow1.wav";
		private String posaPieza2 = ruta + "dieThrow2.wav";
		
		public UrlAudio() {}

		public String getLinea() {
			return linea;
		}

		public void setLinea(String linea) {
			this.linea = linea;
		}

		public String getGameOver() {
			return gameOver;
		}

		public void setGameOver(String gameOver) {
			this.gameOver = gameOver;
		}

		public String getLevelUp() {
			return levelUp;
		}

		public void setLevelUp(String levelUp) {
			this.levelUp = levelUp;
		}

		public String getMusicaFondo() {
			return musicaFondo;
		}

		public void setMusicaFondo(String musicaFondo) {
			this.musicaFondo = musicaFondo;
		}

		public String getLineaCrash() {
			return lineaCrash;
		}

		public void setLineaCrash(String lineaCrash) {
			this.lineaCrash = lineaCrash;
		}

		public String getPosaPieza1() {
			return posaPieza1;
		}

		public void setPosaPieza1(String posaPieza1) {
			this.posaPieza1 = posaPieza1;
		}

		public String getPosaPieza2() {
			return posaPieza2;
		}

		public void setPosaPieza2(String posaPieza2) {
			this.posaPieza2 = posaPieza2;
		}
	}
}
