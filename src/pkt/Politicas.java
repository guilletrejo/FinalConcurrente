package pkt;

public class Politicas {
	
	Politicas(){
		
	}
	
	public int cual(boolean[] vector_m){
		int i;
		int j = 0;
		for (i = 0; i<19; i++){
			if (vector_m[i] == true) j=i;
		}		
		//System.out.printf("Estoy en Politica elijo transicion %d \n" , j);
		return j;
	}
	
	
}
