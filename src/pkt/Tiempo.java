package pkt;

import java.util.Arrays;

public class Tiempo {
	private static String[] name_t = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	/* vector de tiempos asociados a cada transicion */
	private long [] ts;
	private long [] en_espera;
	private int[] alfa;
	private int[] beta;
	//private Clock clk;
	
	public Tiempo(int N_t){
		ts = new long [N_t];
		en_espera = new long [N_t];
		alfa = new int [N_t];
		beta = new int [N_t];
		//this.clk = clk;
		Arrays.fill(alfa, 0);
		Arrays.fill(beta, 10000);
		Arrays.fill(ts, System.currentTimeMillis());

		//alfa[14] =10;//T31
		alfa[0] = 21; 
		alfa[3] = 23;
		//alfa[4] = 4;
		alfa[6] = 5;
		alfa[7] = 10;
		alfa[9] = 23;
		alfa[10] = 40;
		alfa[13] = 24;
		alfa[11] = 15;
		alfa[16] = 17;
		alfa[18] = 19;
	}
	
	public void setNuevoTimeStamp(int t_index){
		ts[t_index] = System.currentTimeMillis();
		long tiempo_10sec = ts[t_index]/10000;
		return;
	}
	
	public boolean testVentanaTiempo(int t_index){
		long tiempo_actual = System.currentTimeMillis();
		if(tiempo_actual - ts[t_index]>=alfa[t_index] && tiempo_actual- ts[t_index] < beta[t_index]){
			return true;
		} else {
			return false;
		}		
	}
	
	public boolean alguien_esperando(int t_index){
		Thread t;
		t = Thread.currentThread();
		//System.out.println("[Tiempo] alguien_esperando -> en espera: " +en_espera[t_index] + " ->ID HILO " +t.getId());
		if (en_espera[t_index] == t.getId() || (en_espera[t_index] == 0)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean antes_ventana(int t_index) {
		long tiempo_actual  = System.currentTimeMillis();
		//System.out.println("Antes de la ventana!  " + (tiempo_actual - ts[t_index]));
		if (tiempo_actual - ts[t_index] < alfa[t_index]){
			return true;
		} else {
			return false;
		}
	}
	public void set_esperando(int t_index){
		en_espera[t_index] = Thread.currentThread().getId();
		
		return;
	}
	
	public void reset_esperando(int t_index){
		en_espera[t_index] = 0;
		return;
	}
	
	public long get_sleep(int t_index){
		long tiempo_actual = System.currentTimeMillis();
		long tiempo_10sec = tiempo_actual/10000;
		//System.out.println("Estoy en el sleep!  ");
		//System.out.println("[Tiempo] Calculo tiempo sleep -> ID HILO " + Thread.currentThread().getId() + "Tiempo " + (ts[t_index]+alfa[t_index]-tiempo_actual) );
		return ts[t_index]+alfa[t_index]-tiempo_actual;
	}
	
	public boolean tiene_tiempo(int t_index){
		if (alfa[t_index]==-1) return true;
		else return true;
	}

	
}
