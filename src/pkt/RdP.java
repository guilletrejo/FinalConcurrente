package pkt;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RdP {
	private int n_p = 6;
	private int n_t = 4;
	Thread t;
	private Mutex mtx;
	private Tiempo tiempo;
	//private static String[] plazas = {"InterbloqPzaB","m1","m2","m3","m4","P10","P11","P12","P13","P14","P15","P16","P17","P18","P20","P21","P22","P23","P27","P28","P30","P31","P32","P33","P34","P35","r1","r2","r3"};
	//private static String[] plazas = {"P1","P2","P3","P4"};
	private static String[] name_t = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	

	private int marcado_inicial[][] = {{1},{1},{1},{1},{1},{10},{0},{0},{0},{0},{0},{0},{0},
			{0},{10},{0},{0},{0},{1},{1},{10},{0},{0},{0},{0},{0},
			{1},{1},{1}};
	private int marcado_inicial_test[][] = {{1},{0},{0},{1},{0},{10}};
	private int matriz_pre [][] = new int [n_p][n_t];
	private int matriz_post [][] = new int [n_p][n_t];

	private int matriz_i [][];// = matriz_post - matriz_pre;

	private int marcado_actual[][];

	public RdP( boolean TEST, Mutex mtx){
		this.mtx = mtx;
		
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
		
		tiempo = new Tiempo(n_t);
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

	/* Rdp devolverá int. Si '0' es false. Si '1' es un true. Si '2' sacalo del monitor */
	public int disparar(int index){
		t = Thread.currentThread();
		int [] resultado_disparo = new int[n_p];
		int vector_dt[][] = new int[1][n_t]; 
		vector_dt [0][index] = 1;
		boolean ventana, k = false, antes;

		resultado_disparo = suma_vec(transponer(marcado_actual)[0],transponer(producto(matriz_i,transponer(vector_dt)))[0]);

		for(int i = 0;i < resultado_disparo.length;i++){
			if (resultado_disparo[i] < 0){
				return 0;
			}
		}
		
		//System.out.println("[RdP] Voy a disparar  " + index);
		/* ¡Tiempo! */
		if(tiempo.tiene_tiempo(index)){			
			ventana = tiempo.testVentanaTiempo(index);
		//	System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> Tiene Tiempo ->" +name_t[index] );
			if(ventana){
				k = tiempo.alguien_esperando(index);
				//k=true;
				//System.out.println("[RDP] Ventana OK" + k);
				//System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> Ventana OK ->" +name_t[index] );
			} else {
				//System.out.println("Estoy en antes  ");
				
				antes = tiempo.antes_ventana(index);
				mtx.release();
				if (antes){
					tiempo.set_esperando(index);
					//System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> Antes de Ventana ->" +name_t[index] );
					try {
					//	System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> Me voy a dormir! ->" +name_t[index] );
						Thread.sleep(tiempo.get_sleep(index));
						//System.out.println("Estoy despierto w8  "+Thread.currentThread().getName());
						///System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> Despierto! ->" +name_t[index] );
						return 2;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					//System.out.println("Estoy despues de la ventana  ");
					System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> Despues de la ventana ->" +name_t[index] );
					k = false;
				}
			}
		} else {
			//System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> NO Tiene Tiempo ->" +name_t[index] );
			k=true;
		}
		
		
		if (k){
			tiempo.reset_esperando(index);
			boolean [] sensibilizadas_prev = sensibilizadas();
			for (int j = 0; j < resultado_disparo.length; j++) {
				marcado_actual[j][0]=resultado_disparo[j]; 
			}
			boolean [] sensibilizadas_nueva = sensibilizadas();
			
			for (int j = 0; j < sensibilizadas_nueva.length; j++) {
				if(sensibilizadas_prev[j]==false && sensibilizadas_nueva[j]==true){
					tiempo.setNuevoTimeStamp(j);
				} 
			}
			//System.out.println("[RdP] Disparo OK " + name_t[index] + " Tiene tiempo "+tiempo.tiene_tiempo(index));
			//System.out.println("[THREAD "+ Thread.currentThread().getName() + "] -> Disparo OK ->" +name_t[index] );
			assert_test();
			return 1;
			
		} else {
			//System.out.println("[THREAD "+ Thread.currentThread().getName() + "] ->  no puedo disparar Me voy a la cola -> " +name_t[index] );
			return 0;
		}
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
			
			//scanner = new Scanner(new File("C:/Users/nati/Documents/FACULTAD/CUARTO/Programacion Concurrente/FinalConcurrente/mtx_final.txt"));
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
	
	public Mutex getMtx() {
		return mtx;
	}
	
	private void assert_test(){
		assert(1==(marcado_actual[15][0]+marcado_actual[16][0]+marcado_actual[0][0]));
		assert(1==(marcado_actual[1][0]+marcado_actual[7][0]));
		assert(1==(marcado_actual[11][0]+marcado_actual[16][0]+marcado_actual[2][0]));
		assert(1==(marcado_actual[24][0]+marcado_actual[8][0]+marcado_actual[3][0]));
		assert(1==(marcado_actual[12][0]+marcado_actual[22][0]+marcado_actual[4][0]));
		assert(10==(marcado_actual[5][0]+marcado_actual[6][0]+marcado_actual[7][0]
								+marcado_actual[8][0]+marcado_actual[9][0]+marcado_actual[10][0]
												+marcado_actual[11][0]+marcado_actual[12][0]+marcado_actual[13][0]));
		assert(10==(marcado_actual[14][0]+marcado_actual[15][0]+marcado_actual[16][0]+marcado_actual[17][0]));
		assert(1==(marcado_actual[8][0]+marcado_actual[10][0]+marcado_actual[12][0]
												+marcado_actual[18][0]+marcado_actual[21][0]+marcado_actual[22][0]
												+marcado_actual[25][0]+marcado_actual[24][0]+marcado_actual[23][0]));
		assert(1==(marcado_actual[9][0]+marcado_actual[16][0]+marcado_actual[19][0]));
		assert(10==(marcado_actual[20][0]+marcado_actual[21][0]+marcado_actual[22][0]
												+marcado_actual[25][0]+marcado_actual[24][0]+marcado_actual[23][0]));
				
		assert(1==(marcado_actual[25][0]+marcado_actual[6][0]+marcado_actual[26][0]));
				
		assert(1==(marcado_actual[9][0]+marcado_actual[10][0]+marcado_actual[27][0]
												+marcado_actual[15][0]+marcado_actual[17][0]+marcado_actual[23][0]));
		assert(1==(marcado_actual[28][0]+marcado_actual[13][0]+marcado_actual[21][0]));
	}
}
