package pkt;

public class Politicas {
	private double cont_a, cont_b , cont_c , cont_abc ;
	private int [][] matriz_P;
	private int [][] matriz_M; 
	private static int[] A = {0,1,3,4,6,8,2,13,5,7};
	private static int[] B = {9,10,11,12};
	private static int[] C = {14,15,16,17,18,19};
	
	double porc_a = 0.25, porc_b = 0.5, porc_c = 0.25;
	//double porc_a = 0.33, porc_b = 0.33, porc_c = 0.33;
	//double porc_a = 0.5, porc_b = 0.3, porc_c = 0.2;
	int index;
	
	
	Politicas(int n_p, int n_t){
		cont_a = 0;
		cont_b = 0; 
		cont_c = 0;
		cont_abc = 0;
		index = 1;
		matriz_P = new int [n_t][n_t];
		matriz_M = new int [1][n_t];
	}
	
	
	
	public int cual(boolean[] vector_m){
		int i;
		int j = 0;
		
		if(cont_abc != 0) actualizar_P();
		else set_P(A,B,C);
		
		for (i = 0; i<vector_m.length ; i++){
			if (vector_m[i] == true) {
				matriz_M[0][i] = i + 1;
			}
			else matriz_M[0][i] = 0;
		}		
		
		int result[][] = producto(matriz_P, transponer(matriz_M));
		
		for (i = 0; i<result.length; i++){
			if (result[i][0] > 0) {
				j=result[i][0]-1;
				break;
			}
		}
		
//		for (i = 0; i<vector_m.length; i++){
//			if (vector_m[i] == true) {
//				j=i;
//				break;
//			}
			
		//}
		//System.out.printf("Estoy en Politica elijo transicion %d \n" , j);
		
		return j;
	}
	
	private void actualizar_P(){
		double pa_a = cont_a/cont_abc - porc_a + 0.0001;
		double pa_b = cont_b/cont_abc - porc_b;
		double pa_c = cont_c/cont_abc - porc_c ;
		
		
		//System.out.println("PROB ACTUALES  A->" + pa_a + " B->"+ pa_b+ " C->" + pa_c);
		if(pa_a < pa_b && pa_a < pa_c && pa_b < pa_c)index = 0; //abc
		else if (pa_a < pa_b && pa_a < pa_c && pa_b > pa_c)index = 1; //acb
		else if (pa_a > pa_b && pa_a < pa_c && pa_b < pa_c)index = 2; //bac
		else if (pa_a > pa_b && pa_a > pa_c && pa_b < pa_c)index = 3;//bca
		else if (pa_a < pa_b && pa_a > pa_c && pa_b > pa_c)index = 4;//cab
		else if (pa_a > pa_b && pa_a > pa_c && pa_b > pa_c)index = 5;//cba
		
		//System.out.println("INDEX::::::::::" + index);
		switch(index){
			case 0: set_P(A,B,C);
					break;//for(int ii = 0; ii < matriz_P.length; ii++){
			case 1: set_P(A,C,B);
					break;
			case 2: set_P(B,A,C);
					break;
			case 3: set_P(B,C,A);
					break;
			case 4: set_P(C, A, B);
					break;
			case 5: set_P(C, B, A);
					break;
				
			}
		
		return;
	}
	public void disparoOK(int j){
		if (j == 8){
			cont_a = cont_a + 1;
			cont_abc++;
			System.out.println("[TERMINADAS A] "  + (int)cont_a + " -> [" + Thread.currentThread().getName() + "]" );
		}
		else if (j == 12){
			cont_b ++;
			cont_abc ++;
			System.out.println("[TERMINADAS B] "  + (int) cont_b + " -> [" + Thread.currentThread().getName() + "]" );
		}
		else if (j == 19){
			cont_c ++;
			cont_abc++;
			System.out.println("[TERMINADAS C] "  + (int) cont_c + " -> [" + Thread.currentThread().getName() + "]" );
		}
	}
	private void set_P(int [] vec_max_pri, int [] vec_med_pri, int [] vec_min_pri ){
		int i, j;
		for(i = 0; i < matriz_P.length; i++){
			for(j = 0; j < matriz_P[0].length; j++){
				matriz_P[i][j] = 0;
			}
		}
		int ii, jj;
			for(jj = 0; jj < vec_max_pri.length ;jj++){
				matriz_P[jj][vec_max_pri[jj]] = 1;
			}
			for(ii = 0; ii < vec_med_pri.length ;ii++){
				matriz_P[ii+jj][vec_med_pri[ii]] = 1;
			}
			for(int kk = 0; kk < vec_min_pri.length ;kk++){
				matriz_P[ii+jj+kk][vec_min_pri[kk]] = 1;
			}
		
//		System.out.println("[Politica]-------------INDICE:   " + index);
//		for(i = 0; i < matriz_P.length; i++){
//			for(j = 0; j < matriz_P[0].length; j++){
//				System.out.print(matriz_P[i][j] + "\t");
//			}
//			System.out.println(" ");
//		}
	}
	
	private int[][] transponer(int[][] A){
		int [][] resultado = new int[A[0].length][A.length];

		for (int x=0; x < A.length; x++) {
			for (int y=0; y < A[x].length; y++) {
				resultado[y][x] = A[x][y];
			}
		}

		return resultado;
	}

	private static int[][] producto(int A[][], int B[][]){
		int suma = 0;
		int result[][] = new int[A.length][B[0].length];
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < B[0].length; j++){
				suma = 0;
				for(int k = 0; k < B.length; k++){
					suma += A[i][k] * B[k][j];
				}
				result[i][j] = suma;
			}
		}
		return result;

	}
}