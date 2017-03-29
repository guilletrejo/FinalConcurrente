package pkt;



public class Main {
	private static String[] nombre_transiciones = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private static String[] nombre_transiciones_test = {"T0","T1","T2","T3"};
	
	private static int[] numero_transisiconesA1 = {1,3,4,6,8};
	private static int[] numero_transisiconesA2 = {2,13,5,7,8};
	private static int[] numero_transisiconesB = {9,10,11,12};
	private static int[] numero_transisiconesC = {14,15,16,17,18,19};

	//private static int[] numero_transisiconesA4 = {0};

	private static int[] numero_transisiconesA = {0};
	//private static int[] numero_transisiconesA4 = {0};
	private static int[] numero_transisiconesProd = {0,1};
	private static int[] numero_transisiconesCons = {2,3};


	private static boolean TEST = false;
	public static void main(String[] args) {
		System.out.printf("Arranca la produccion de piezas\n");
		
//		Clock clk = new Clock();
//		clk.start();
		
		Mutex mtx = new Mutex();
		RdP rdp = new RdP(TEST, mtx);
		GestorMonitor GDM = new GestorMonitor(rdp);

				

		
		if (!TEST){
			
			
			Pieza pzaA = new Pieza(GDM,"PIEZA AGEN",numero_transisiconesA,nombre_transiciones);
			Pieza pzaA1 = new Pieza(GDM,"PIEZA A1",numero_transisiconesA1,nombre_transiciones);
			Pieza pzaA2 = new Pieza(GDM,"PIEZA A2",numero_transisiconesA2,nombre_transiciones);
			Pieza pzaB  = new Pieza(GDM,"PIEZA B",numero_transisiconesB,nombre_transiciones);
			Pieza pzaC  = new Pieza(GDM,"PIEZA C",numero_transisiconesC,nombre_transiciones);
			
			Pieza pzaAA = new Pieza(GDM,"PIEZA AGEN",numero_transisiconesA,nombre_transiciones);
			Pieza pzaAA1 = new Pieza(GDM,"PIEZA AA1",numero_transisiconesA1,nombre_transiciones);
//			Pieza pzaAA2 = new Pieza(GDM,"PIEZA AA2",numero_transisiconesA2,nombre_transiciones)\
			Pieza pzaB1  = new Pieza(GDM,"PIEZA B",numero_transisiconesB,nombre_transiciones);
			Pieza pzaBB  = new Pieza(GDM,"PIEZA BB",numero_transisiconesB,nombre_transiciones);
			Pieza pzaCC  = new Pieza(GDM,"PIEZA CC",numero_transisiconesC,nombre_transiciones);
			
			pzaB.start();
			pzaAA1.start();
			pzaA.start();
			pzaA1.start();
			pzaA2.start();
			pzaC.start();
			//pzaBB.start();
	//	pzaB1.start();
			//pzaCC.start();

			
		}
		else{
			Pieza productor  = new Pieza(GDM,"PRODUCTOR",numero_transisiconesProd,nombre_transiciones_test);
			Pieza consumidor  = new Pieza(GDM,"CONSUMIDOR",numero_transisiconesCons,nombre_transiciones_test);
			
			productor.start();
			consumidor.start();
		}
	}

}
