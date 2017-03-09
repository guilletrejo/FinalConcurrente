package pkt;

public class Politicas {
	
	Politicas(){
		
	}
	
	public int cual(boolean[] vector_m){
		int i;
		for (i = 0; i<22; i++){
			if (vector_m[i] == true) break;
		}	
		
		System.out.printf("Estoy en Politica elijo transicion " + i);
		return i;
	}
	
	
}
