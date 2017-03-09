package pkt;

public class Colas {
	private boolean[] vector_vc;
	private int[] estado_colas;
	
	Colas (int cant_hilos){
		
	}// ??
	
	public boolean[] quienes_estan(){
		return vector_vc;
	}
	
	public boolean aquire(int index_c, int index_t){
		estado_colas[index_c] ++;
		vector_vc[index_t] = true;	
		System.out.printf("Estoy en colas me voy a dormir");
		return true;
	}
	
	public boolean release(int index_c, int index_t){
		if(vector_vc[index_t]){
			estado_colas[index_c]--;
			if(estado_colas[index_c]==0)vector_vc[index_t] = false;
			System.out.printf("Estoy en colas me despierto");
			return true;
			
		}
		else return false;
	}
	
}
