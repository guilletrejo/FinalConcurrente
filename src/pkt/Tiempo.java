package pkt;

import java.util.Arrays;

public class Tiempo {
	
	/* vector de tiempos asociados a cada transicion */
	private long [] ts;
	private long [] en_espera;
	private int[] alfa;
	private int[] beta;
	
	public Tiempo(int N_t){
		ts = new long [N_t];
		en_espera = new long [N_t];
		alfa = new int [N_t];
		beta = new int [N_t];
		Arrays.fill(alfa, -1);
		

		alfa[3] = 28;
		alfa[6] = 3;
		alfa[7] = 8;
		alfa[13] = 22;
		alfa[11] = 13;
		alfa[16] = 19;
		alfa[18] = 16;
		
		beta[3] = 32;
		beta[6] = 7;
		beta[7] = 12;
		beta[13] = 26;
		beta[11] = 17;
		beta[16] = 23;
		beta[18] = 20;
	
	}
	
	public void setNuevoTimeStamp(int t_index){
		ts[t_index] = System.currentTimeMillis();
		long tiempo_10sec = ts[t_index]/10000;
		return;
	}
	
	public boolean testVentanaTiempo(int t_index){
		long tiempo_actual = System.currentTimeMillis();
		long tiempo_10sec = tiempo_actual/10000;
		System.out.println(tiempo_10sec);
		System.out.println(ts[t_index]);
		System.out.println("testVentanaTiempo  " + (tiempo_actual - ts[t_index]));
		System.out.println("Indice  " + t_index);
		
		if(tiempo_actual - ts[t_index]>alfa[t_index] && tiempo_actual- ts[t_index] < beta[t_index]){
			return true;
		} else {
			return false;
		}		
	}
	
	public boolean alguien_esperando(int t_index){
		Thread t;
		t = Thread.currentThread();
		if (en_espera[t_index] == t.getId() || (en_espera[t_index] == 0)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean antes_ventana(int t_index) {
		long tiempo_actual = System.currentTimeMillis();
		long tiempo_10sec = tiempo_actual/10000;
		System.out.println("Antes de la ventana!  " + (tiempo_actual - ts[t_index]));
		
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
		System.out.println("Estoy en el sleep!  ");

		return ts[t_index]+alfa[t_index]-tiempo_actual;
	}
	
	public boolean tiene_tiempo(int t_index){
		if (alfa[t_index]==-1) return false;
		else return true;
	}

	
}
