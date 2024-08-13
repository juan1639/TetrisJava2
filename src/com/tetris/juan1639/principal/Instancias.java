package com.tetris.juan1639.principal;

import com.tetris.juan1639.logicaArrayFondo.Fondo;

public class Instancias {
	
	public static Fondo[][] instanciarMatrizFondo(
			Fondo[][] tileFondo, Integer filas, Integer columnas, Integer tileX, Integer tileY) {
		
        for (int i = 0; i < filas; i ++) {
            for (int ii = 0; ii < columnas; ii ++) {

                tileFondo[i][ii] = new Fondo(ii, i, tileX, tileY);
            }
        }
        
        return tileFondo;
	}
}
