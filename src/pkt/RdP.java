package pkt;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

public class RdP {
	private int n_p = 6;
	private int n_t = 4;
	Thread t;
	
	private static String[] nombre_transiciones = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private static String[] nombre_transiciones_test = {"T0","T1","T2","T3"};
	private int marcado_inicial[][] = {{1},{1},{1},{1},{1},{10},{0},{0},{0},{0},{0},{0},{0},
			{0},{10},{0},{0},{0},{1},{1},{10},{0},{0},{0},{0},{0},
			{1},{1},{1}};
	private int marcado_inicial_test[][] = {{1},{0},{0},{1},{0},{10}};
	private int matriz_pre [][] = new int [n_p][n_t];
	private int matriz_post [][] = new int [n_p][n_t];


	private int matriz_i [][];// = matriz_post - matriz_pre;

	private int marcado_actual[][];

	public RdP( boolean TEST){
		
		if (!TEST){
			n_p = 30;
			n_t = 20;
			matriz_post = get_mtx("text_files/mtx_final_i_post.txt");
			matriz_pre = get_mtx("text_files/mtx_final_i_pre.txt");
			marcado_actual = marcado_inicial;
			
		}
		else{
			matriz_post = get_mtx("text_files/mtx_prod_cons_i_post.txt");
			matriz_pre = get_mtx("text_files/mtx_prod_cons_i_pre.txt");
			marcado_actual = marcado_inicial_test;
		}

		matriz_i = resta(matriz_post,matriz_pre);		
	}

	public int getN_t() {
		return n_t;
	}

	public int getN_p() {
		return n_p;
	}

	public int[][] getMarcado_actual() {		
		
		return marcado_actual;
	}

	public void test_p (){
		//assert false;
		assert(marcado_actual[15][0]+marcado_actual[16][0]+marcado_actual[0][0] == 1);
		assert(1 == marcado_actual[1][0]+marcado_actual[7][0]);
		assert(1 == marcado_actual[11][0]+marcado_actual[16][0]+marcado_actual[2][0]);
		assert(1 == marcado_actual[24][0]+marcado_actual[8][0]+marcado_actual[3][0]);
		assert(1 == marcado_actual[12][0]+marcado_actual[22][0]+marcado_actual[4][0]);
		assert(10 == marcado_actual[5][0]+marcado_actual[6][0]+marcado_actual[7][0]
										+marcado_actual[8][0]+marcado_actual[9][0]+marcado_actual[10][0]
										+marcado_actual[11][0]+marcado_actual[12][0]+marcado_actual[13][0]);
		assert(10 == marcado_actual[14][0]+marcado_actual[15][0]+marcado_actual[16][0]+marcado_actual[17][0]);
		assert(1 == marcado_actual[8][0]+marcado_actual[10][0]+marcado_actual[12][0]
										+marcado_actual[18][0]+marcado_actual[21][0]+marcado_actual[22][0]
										+marcado_actual[25][0]+marcado_actual[24][0]+marcado_actual[23][0]);
		assert(1 == marcado_actual[9][0]+marcado_actual[16][0]+marcado_actual[19][0]);
		assert(10 == marcado_actual[20][0]+marcado_actual[21][0]+marcado_actual[22][0]
										+marcado_actual[25][0]+marcado_actual[24][0]+marcado_actual[23][0]);
		
		assert(1 == marcado_actual[25][0]+marcado_actual[6][0]+marcado_actual[26][0]);
		
		assert(1 == marcado_actual[9][0]+marcado_actual[10][0]+marcado_actual[27][0]
										+marcado_actual[15][0]+marcado_actual[17][0]+marcado_actual[23][0]);
		assert(1 == marcado_actual[28][0]+marcado_actual[13][0]+marcado_actual[21][0]);
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
	
	private static int[] suma_vec(int A [], int B[]){
		int [] resultado = new int[A.length];
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
		t = Thread.currentThread();
		int [] resultado_disparo = new int[n_p];
		int vector_dt[][] = new int[1][n_t]; 
		vector_dt [0][index] = 1;

		resultado_disparo = suma_vec(transponer(marcado_actual)[0],transponer(producto(matriz_i,transponer(vector_dt)))[0]);

		for(int i = 0;i < resultado_disparo.length;i++){
			if (resultado_disparo[i] < 0){
				return false;
			}
		}
		
		for (int j = 0; j < resultado_disparo.length; j++) {
			marcado_actual[j][0]=resultado_disparo[j]; 
		}
		test_p();
		//System.out.println("DISPARO OK::: " + nombre_transiciones[index]);
		return true;
	}

	public boolean[] sensibilizadas(){
		int [] suma_temp = new int[n_p];
		boolean [] resultado = new boolean[n_t];
		boolean flag = true;

		for(int i = 0 ; i < matriz_i[0].length ; i++){

			suma_temp = suma_vec(transponer(marcado_actual)[0],transponer(matriz_i)[i]);
			for(int j = 0; j < suma_temp.length ; j++){
				if (suma_temp[j] < 0){
					flag = false;
					break;
				}
			}
			resultado[i]= flag;
			flag = true;
		}

		return resultado;
	}
	
	public int[][] get_mtx(String path){
		Scanner scanner = null;
		int [][] mtx_i = new int [n_p][n_t];
		//int i = 0, j = 0;
		
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		while(scanner.hasNextInt()){
			for(int ii =0;ii<mtx_i.length;ii++){
				for(int jj =0;jj<mtx_i[0].length;jj++){
					if(scanner.hasNextInt())mtx_i[ii][jj] = scanner.nextInt();
					else break; 
					//System.out.print(mtx_i[jj][ii] + "\t");
				}
				//System.out.println(" ");
			}
		}
		return mtx_i;
	}
	
	
}
