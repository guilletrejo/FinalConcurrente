package pkt;

public class PiezaA1 extends Thread {
	private int estado; 
	private static String[] name_t = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private int[] transicion = {0,1,3,4,6,8};
	private GestorMonitor GM;

	//	constructor

	public PiezaA1 (GestorMonitor GM){
		this.GM = GM;
		this.estado = 0; 
		this.setName("[THREAD PIEZA A1]"); 
	}

	public void run() {
		for(int ii = 0; ii < transicion.length; ii++){			
			
			while(!GM.disparar_transicion(transicion[ii]));
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("[PZA_A1] DISPARO  " + name_t[transicion[ii]] );
		}
		System.out.println("[PZA_A1] TERMINADO  " );
	}
	
	private int get_estado(){
		return this.estado;
	}

	private int[] get_transicion(){
		return this.transicion;
	}

}
