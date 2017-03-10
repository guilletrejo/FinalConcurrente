package pkt;

public class PiezaA2 implements Runnable {

	private int estado; 
	private int[] transicion = {0,2,13,5,7,8};
	private GestorMonitor GM;

	//	constructor

	public PiezaA2 (GestorMonitor GM){
		this.GM = GM;
		this.estado = 0; 
	}

	public void run() {
		for(int ii = 0; ii < transicion.length; ii++){			
			while(!GM.disparar_transicion(transicion[ii]));
		}
	}

	private int get_estado(){
		return this.estado;
	}

	private int[] get_transicion(){
		return this.transicion;
	}


}
