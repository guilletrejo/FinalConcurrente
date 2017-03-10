package pkt;

public class PiezaA2 extends Thread {
	
	private static String[] name_t = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private int estado; 
	private int[] transicion = {0,2,13,5,7,8};
	private int cant_piezas_terminadas;
	private GestorMonitor GM;

	//	constructor

	public PiezaA2 (GestorMonitor GM){
		this.GM = GM;
		this.estado = 0; 
		this.setName("[THREAD PIEZA A2]");
		this.cant_piezas_terminadas = 0;
	}

	public void run() {
		while(true){
			for(int ii = 0; ii < transicion.length; ii++){			
				while(!GM.disparar_transicion(transicion[ii]));
				try {
					sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("[PZA_A2] DISPARO  " + name_t[transicion[ii]] );
			}
			cant_piezas_terminadas ++;
			System.out.printf("[PZA_A2] %d TERMINADAS \n", cant_piezas_terminadas);
		}
	}
	private int get_estado(){
		return this.estado;
	}

	private int[] get_transicion(){
		return this.transicion;
	}


}
