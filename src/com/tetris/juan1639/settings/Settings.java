package com.tetris.juan1639.settings;

import com.tetris.juan1639.logicaArrayFondo.Fondo;

public class Settings {
	
	public final Integer FPS = 60;
	public final Integer[] COLOR_FONDOS = { 153, 154, 155, 72, 72, 72 };
	public final Integer SIZE_TXT_NUEVAPARTIDA = 32;
	
	public final Integer TILE_X = 30;
	public final Integer TILE_Y = 30;
	public final Integer TILES_WIDTH = 14;
	public final Integer TILES_HEIGHT = 20;
	
	public final Fondo[][] tileFondo = new Fondo[TILES_HEIGHT][TILES_WIDTH];
	
	public final Integer X_INICIAL = 7;
	public final Integer Y_INICIAL = 2;
	public final Integer X_NEXT = 19;
	public final Integer Y_NEXT = 16;
	
	public final String PIEZAS = "zsljoit";
	
	public final Integer TIEMPO_PAUSA_REJUGAR = 120;
	
	private Integer lineas = 0;
	private Integer nivel = 1;
	private Integer hiScore = 27;
	
	private Integer[] gravedad = {
		45, 30, 25, 23, 20, 18, 16, 15, 12, 10,
		10, 8, 7, 5, 5, 3, 3, 3, 2, 2, 1
	};

	private Integer[][] levelUpLines = {
		{0, 3}, {0, 7}, {0, 12},
		{0, 15}, {0, 20}, {0, 24},
		{0, 32}, {0, 35}, {0, 40},
		{0, 44}, {0, 47}, {0, 50},
		{0, 55}, {0, 60}, {0, 70},
		{0, 75}, {0, 80}, {0, 85},
		{0, 90}, {0, 95}, {0, 100}
	};

	private Integer incrementoDificultad = 0;
	private Boolean checkeandoMatriz = false;
	private Integer nextPieza = 6;
	private Boolean otraPieza = true;
	
	private Integer pausaRejugar = 99;
	private Boolean fireWorks = false;
	
	// -----------------------------------------------------------
	//	 G E T T E R S  &  S E T T E R S
	// 	
	// -----------------------------------------------------------
	
	public Integer[] getGravedad() {
		return gravedad;
	}

	public Integer getLineas() {
		return lineas;
	}

	public void setLineas(Integer lineas) {
		this.lineas = lineas;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getHiScore() {
		return hiScore;
	}

	public void setHiScore(Integer hiScore) {
		this.hiScore = hiScore;
	}

	public void setGravedad(Integer[] gravedad) {
		this.gravedad = gravedad;
	}

	public Integer[][] getLevelUpLines() {
		return levelUpLines;
	}

	public void setLevelUpLines(Integer[][] goal_lines) {
		this.levelUpLines = goal_lines;
	}

	public Integer getIncrementoDificultad() {
		return incrementoDificultad;
	}

	public void setIncrementoDificultad(Integer incremento_dificultad) {
		this.incrementoDificultad = incremento_dificultad;
	}

	public Boolean getCheckeandoMatriz() {
		return checkeandoMatriz;
	}

	public void setCheckeandoMatriz(Boolean checkeando_matriz) {
		this.checkeandoMatriz = checkeando_matriz;
	}

	public Integer getNextPieza() {
		return nextPieza;
	}

	public void setNextPieza(Integer next_pieza) {
		this.nextPieza = next_pieza;
	}

	public Boolean getOtraPieza() {
		return otraPieza;
	}

	public void setOtraPieza(Boolean otraPieza) {
		this.otraPieza = otraPieza;
	}

	public Integer getPausaRejugar() {
		return pausaRejugar;
	}

	public void setPausaRejugar(Integer pausa_rejugar) {
		this.pausaRejugar = pausa_rejugar;
	}

	public Boolean getFireWorks() {
		return fireWorks;
	}

	public void setFireWorks(Boolean fireWorks) {
		this.fireWorks = fireWorks;
	}

	// ============================================================
	// CONSTRUCTOR PRINCIPAL 'Settings' (SINGLETON)
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
		
		public Boolean isRotar() {
			return rotar;
		}

		public void setRotar(Boolean rotar) {
			this.rotar = rotar;
		}
	}

	// ====================================================================
	public class Estado {

		private Boolean preJuego = false;
		private Boolean enJuego = true;
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
