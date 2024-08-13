package com.tetris.juan1639.principal;

import com.tetris.juan1639.logicaArrayFondo.Fondo;
import com.tetris.juan1639.logicaPieza.NextPieza;
import com.tetris.juan1639.logicaPieza.Pieza;
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
}
