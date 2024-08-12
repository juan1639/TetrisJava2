package com.tetris.juan1639.interfaces;

import com.tetris.juan1639.settings.Settings;

public interface IResetControlesEstados {
	
	default void resetControles(Boolean bool, Settings settings) {
		
		settings.controles.setIzquierda(bool);
		settings.controles.setDerecha(bool);
		settings.controles.setAbajo(bool);
		settings.controles.setRotar(bool);
	}
	
	default void resetEstados(Boolean bool, Settings settings) {
		
		settings.estado.setPreJuego(bool);
		settings.estado.setEnJuego(bool);
		settings.estado.setNivelSuperado(bool);
		settings.estado.setGameOver(bool);
	}
}
