package com.tetris.juan1639.principal;

import java.awt.Color;

import com.tetris.juan1639.logicaArrayFondo.Fondo;
import com.tetris.juan1639.logicaPieza.NextPieza;
import com.tetris.juan1639.logicaPieza.Pieza;
import com.tetris.juan1639.marcadores.Marcadores;
import com.tetris.juan1639.settings.Colores;
import com.tetris.juan1639.settings.PlantillaPiezasFactory;
import com.tetris.juan1639.settings.Settings;

public class Instancias {
	
	public static Fondo[][] instanciarMatrizFondo(
			Fondo[][] tileFondo, Integer filas, Integer columnas, Integer tileX, Integer tileY) {
		
        for (Integer i = 0; i < filas; i ++) {
            for (Integer ii = 0; ii < columnas; ii ++) {

                tileFondo[i][ii] = new Fondo(ii, i, tileX, tileY);
            }
        }
        
        return tileFondo;
	}
	
	public static Object[] instanciarPieza(
			Settings settings,
			Pieza pieza,
			NextPieza verNextPieza,
			PlantillaPiezasFactory piezaFactory) {
		
        settings.setOtraPieza(false);
        
        Integer x = settings.X_INICIAL;
        Integer y = settings.Y_INICIAL;
        Integer col = settings.TILES_WIDTH;
        Integer filas = settings.TILES_HEIGHT;

        Integer nro_rnd = settings.getNextPieza();
        Integer elegida = nro_rnd;

        // ---------------------------------------------------
        // 	N E X T  -  P I E Z A  (Ver)
        //	
        // ---------------------------------------------------
        nro_rnd = (int) (Math.random() * 7);
        
        settings.setNextPieza(nro_rnd);
        
        verNextPieza = new NextPieza(
        		settings.X_NEXT, settings.Y_NEXT, piezaFactory.getPieza().get(nro_rnd), Colores.PIEZAS[nro_rnd]
        );

        // ---------------------------------------------------
        //	 P I E Z A  (Instanciar)
        //	
        // ---------------------------------------------------
        pieza = new Pieza(
        		x, y, piezaFactory.getPieza().get(elegida), col, filas, Colores.PIEZAS[elegida], settings
        );
        
		return new Object[] {verNextPieza, pieza};
	}
	
	public static Object[] instanciarMarcadores(
			Settings settings, Marcadores lineas, Marcadores nivel, Marcadores hi) {
		
		int[] argsInt = Marcadores.argsInt_instanciaMarcadores(settings);
        String[] argsTxt = Marcadores.argsString_instanciaMarcadores();

        Integer[] rgb = Colores.MARCADORES;
        
        lineas = new Marcadores(0, argsInt, argsTxt, new Color(rgb[0], rgb[1], rgb[2]));
        nivel = new Marcadores(1, argsInt, argsTxt, new Color(rgb[3], rgb[4], rgb[5]));
        hi = new Marcadores(2, argsInt, argsTxt, new Color(rgb[6], rgb[7], rgb[8]));
        
        return new Object[] {lineas, nivel, hi};
	}
}
