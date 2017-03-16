package pkt;

public class Pieza extends Thread {
	private String[] name_t;// = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private int estado; 
	private int[] transicion;// = {9,10,11,12};
	private int cant_piezas_terminadas;
	private GestorMonitor GM;

	public Pieza (GestorMonitor GM, String nombre_hilo, int [] transiciones, String[] nombre_transiciones){
		this.GM = GM;
		this.estado = 0; 
		this.setName(nombre_hilo);
		this.cant_piezas_terminadas = 0;
		this.transicion = transiciones;
		this.name_t = nombre_transiciones;
	}

	public void run() {
		while(cant_piezas_terminadas < 500){
		//if(true){

			for(int ii = 0; ii < transicion.length; ii++){		
				GM.disparar_transicion(transicion[ii]);				
				try {
					sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			
			cant_piezas_terminadas ++;
			System.out.printf("[%s] %d TERMINADAS \n",this.getName(),cant_piezas_terminadas);
		}
	}

	private int get_estado(){
		return this.estado;
	}

	private int[] get_transicion(){
		return this.transicion;
	}


}
