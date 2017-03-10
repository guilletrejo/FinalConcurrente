package pkt;

public class PiezaC implements Runnable {

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
		}
	}

	private int get_estado(){
		return this.estado;
	}

	private int[] get_transicion(){
		return this.transicion;
	}

}
