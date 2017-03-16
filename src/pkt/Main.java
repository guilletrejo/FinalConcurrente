package pkt;

public class Main {
	private static String[] nombre_transiciones = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	
	private static int[] numero_transisiconesA1 = {1,3,4,6,8};
	private static int[] numero_transisiconesA2 = {2,13,5,7,8};
	private static int[] numero_transisiconesB = {9,10,11,12};
	private static int[] numero_transisiconesC = {14,15,16,17,18,19};
	//private static int[] numero_transisiconesA3 = {8};
	private static int[] numero_transisiconesA4 = {0};

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("Arranca la produccion de piezas\n");
		
		RdP rdp = new RdP();
		GestorMonitor GDM = new GestorMonitor(rdp);

		Pieza pzaB  = new Pieza(GDM,"PIEZA B",numero_transisiconesB,nombre_transiciones);
		Pieza pzaC  = new Pieza(GDM,"PIEZA C",numero_transisiconesC,nombre_transiciones);
		Pieza pzaA1 = new Pieza(GDM,"PIEZA A1",numero_transisiconesA1,nombre_transiciones);
		Pieza pzaA2 = new Pieza(GDM,"PIEZA A2",numero_transisiconesA2,nombre_transiciones);
		//Pieza pzaAG = new Pieza(GDM,"PIEZA AGEN",numero_transisiconesA3,nombre_transiciones);
		Pieza pzaAG2 = new Pieza(GDM,"PIEZA AGEN2",numero_transisiconesA4,nombre_transiciones);
		//PiezaA1 pzaA1 = new PiezaA1(GDM);
		//PiezaA2 pzaA2 = new PiezaA2(GDM);
		//PiezaC  pzaC  = new PiezaC(GDM);
		
		pzaA1.start();
		pzaA2.start();
		pzaB.start();
		pzaC.start();
		//pzaC.setName("[THREAD PIEZA C]");
		//pzaAG.start();
		pzaAG2.start();
		//PASAR SET NAME A CADA CLASE	
		
		

	}

}
