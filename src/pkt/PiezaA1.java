package pkt;

public class PiezaA1 implements Runnable {
	private int estado; 
	private int[] transicion = {0,1,3,4,6,8};
	private GestorMonitor GM;

	//	constructor

	public PiezaA1 (GestorMonitor GM){
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
