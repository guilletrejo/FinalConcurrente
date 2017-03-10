package pkt;

public class PiezaC extends Thread {
	private static String[] name_t = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private int estado; 
	private int[] transicion = {14,15,16,17,18,19};
	private GestorMonitor GM;

	//	constructor

	public PiezaC (GestorMonitor GM){
		this.GM = GM;
		this.estado = 0; 
	}

	public void run() {
		for(int ii = 0; ii < transicion.length; ii++){	
			while(!GM.disparar_transicion(transicion[ii]));
			System.out.println("[PZAC] DISPARO  " + name_t[transicion[ii]] );
		}
		System.out.println("[PZAC] TERMINADO  " );
	}

	private int get_estado(){
		return this.estado;
	}

	private int[] get_transicion(){
		return this.transicion;
	}

}
