package com.tetris.juan1639.settings;

import java.util.ArrayList;

public class PlantillaPiezasFactory {

	private Integer[][] z = {
		{0, 0}, {0, -1}, {-1, -1}, {1, 0},
		{0, 0}, {0, -1}, {-1, 0}, {-1, 1},
		{0, 0}, {0, -1}, {-1, -1}, {1, 0},
		{0, 0}, {0, -1}, {-1, 0}, {-1, 1}
	};

	private Integer[][] s = {
		{0, 0}, {0, -1}, {1, -1}, {-1, 0},
		{0, 0}, {0, 1}, {-1, -1}, {-1, 0},
		{0, 0}, {0, -1}, {1, -1}, {-1, 0},
		{0, 0}, {0, 1}, {-1, -1}, {-1, 0}
	};

	private Integer[][] l = {
		{0, 0}, {0, -1}, {0, -2}, {1, 0},
		{0, 0}, {-1, 0}, {1, 0}, {1, -1},
		{0, 0}, {0, -1}, {0, -2}, {-1, -2},
		{0, 0}, {0, -1}, {1, -1}, {2, -1}
	};

	private Integer[][] j = {
		{0, 0}, {1, 0}, {1, -1}, {1, -2},
		{0, 0}, {0, -1}, {-1, -1}, {-2, -1},
		{0, 0}, {0, -1}, {0, -2}, {1, -2},
		{0, 0}, {0, -1}, {1, 0}, {2, 0}
	};

	private Integer[][] o = {
		{0, 0}, {0, -1}, {1, -1}, {1, 0},
		{0, 0}, {0, -1}, {1, -1}, {1, 0},
		{0, 0}, {0, -1}, {1, -1}, {1, 0},
		{0, 0}, {0, -1}, {1, -1}, {1, 0}
	};

	private Integer[][] i = {
		{0, 0}, {-1, 0}, {1, 0}, {2, 0},
		{0, 0}, {0, -1}, {0, -2}, {0, -3},
		{0, 0}, {-1, 0}, {1, 0}, {2, 0},
		{0, 0}, {0, -1}, {0, -2}, {0, -3}
	};

	private Integer[][] t = {
		{0, 0}, {0, -1}, {-1, 0}, {1, 0},
		{0, 0}, {0, -1}, {0, -2}, {-1, -1},
		{0, 0}, {-1, 0}, {1, 0}, {0, 1},
		{0, 0}, {0, -1}, {0, -2}, {1, -1}
	};
	
	private ArrayList<Integer[][]> pieza = new ArrayList<>();
	
	// ===================================================================
	// 	S I N G L E T O N
	// 
	// -------------------------------------------------------------------
	private PlantillaPiezasFactory() {

		this.z = z;
		this.s = s;
		this.l = l;
		this.j = j;
		this.o = o;
		this.i = i;
		this.t = t;

		this.pieza = pieza;
		this.pieza.add(this.z);
		this.pieza.add(this.s);
		this.pieza.add(this.l);
		this.pieza.add(this.j);
		this.pieza.add(this.o);
		this.pieza.add(this.i);
		this.pieza.add(this.t);
	}
	
	private static PlantillaPiezasFactory INSTANCIA = new PlantillaPiezasFactory();
	public static PlantillaPiezasFactory getInstancia() {return INSTANCIA;}
	
	// =====================================================================
	// 	G E T T E R S  &  S E T T E R S
	//	
	// ---------------------------------------------------------------------
    public ArrayList<Integer[][]> getPieza() {
		return this.pieza;
	}
}
