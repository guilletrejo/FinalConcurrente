package pkt;

public class RdP {
	private static int n_p = 30;
	private static int n_t = 20;
	private static String[] plazas = {"InterbloqPzaB","m1","m2","m3","m4","P10","P11","P12","P13","P14","P15","P16","P17","P18","P20","P21","P22","P23","P27","P28","P30","P31","P32","P33","P34","P35","r1","r2","r3"};
	//private static String[] plazas = {"P1","P2","P3","P4"};
	


	private int marcado_inicial[][] = {{1},{1},{1},{1},{1},{10},{0},{0},{0},{0},{0},{0},{0},
										{0},{10},{0},{0},{0},{1},{1},{10},{0},{0},{0},{0},{0},
										{1},{1},{1}};
	
	private int matriz_pre [][] = {{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0}, //I-
									{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
									{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
									{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
									{0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
									{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
									{0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,0,1,0,0,0},
									{0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0}};

	
	private int matriz_post [][] = {{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0},
									{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
									{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
									{0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
									{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
									{0,0,0,0,1,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0}};
	

	//private int matriz_pre [][] = {{1,0},{0,1},{0,2},{0,0}};
	//private int matriz_post [][] = {{0,1},{1,0},{1,0},{0,1}};
	//private int marcado_inicial[][] = {{1},{0},{2},{1}};
	
	private int matriz_i [][];// = matriz_post - matriz_pre;
	// vector de transiciones a disparar

	private int marcado_actual[][];
	
	
	
	public RdP(){
		matriz_i = resta(matriz_post,matriz_pre);
		marcado_actual = marcado_inicial;

	}
	
	public int getN_t() {
		return n_t;
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
	
	private static int[][] resta(int A [][], int B[][]){
		int [][] resultado = new int[A.length][A[0].length];
		
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < A[0].length; j++){
				resultado[i][j] = A[i][j] - B[i][j];
			}
		}
		return resultado;
	}
	
	private static int[][] suma(int A [][], int B[][]){
		int [][] resultado = new int[A.length][A[0].length];
		
		for(int i = 0; i < A.length - 1; i++){
			for(int j = 0; j < A[0].length - 1; j++){
				resultado[i][j] = A[i][j] + B[i][j];
			}
		}
		return resultado;
	}
	
	private static int[] suma_vec(int A [], int B[]){
		int [] resultado = new int[A.length];
		//System.out.print(A);
			for(int j = 0; j < A.length; j++){
				resultado[j] = A[j] + B[j];
			}
		return resultado;
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
	
	public boolean disparar(int index){
		int [] resultado_disparo = new int[n_p];
		int vector_dt[][] = new int[1][n_t]; 
		vector_dt [0][index] = 1;

		resultado_disparo = suma_vec(transponer(marcado_actual)[0],transponer(producto(matriz_i,transponer(vector_dt)))[0]);
		
//		System.out.println("----------------MARCADO ANTES DISPARO----------------");
//		for (int j = 0; j < resultado_disparo.length; j++) {
//		     //marcado_actual[j][0]=resultado_disparo[j]; 
//		     System.out.println(plazas[j] + "->" + marcado_actual[j][0] + " ");
//		 }
//		System.out.println("--------------------------------------------------------");
		
		for(int i = 0;i < resultado_disparo.length;i++){
			if (resultado_disparo[i] < 0){
				System.out.printf("[RdP] NO SE PUEDE DISPARAR");
				return false;
			}
		}
		System.out.println("---------------------MARCADO DESPUES DE DISPARO--------------------");
		for (int j = 0; j < resultado_disparo.length; j++) {
		     marcado_actual[j][0]=resultado_disparo[j]; 
		     System.out.println(plazas[j] + "->" + resultado_disparo[j] + " ");
		 }
		System.out.println("--------------------------------------------------------------");
		System.out.printf("[RdP]  DISPARO OK");
		System.out.println();
		return true;
	}

	public int[] sensibilizadas(){
		int [] suma_temp = new int[n_p];
		int [] resultado = new int[n_t];
		int flag = 1;
		
		for(int i = 0 ; i < matriz_i[0].length ; i++){
			
			suma_temp = suma_vec(transponer(marcado_actual)[0],transponer(matriz_i)[i]);
			for(int j = 0; j < suma_temp.length ; j++){
				if (suma_temp[j] < 0){
					flag = 0;
					break;
				}
			}
			resultado[i]= flag;
			flag = 1;
		}
			
		return resultado;
	}
	
	

}
