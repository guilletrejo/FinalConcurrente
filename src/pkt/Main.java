package pkt;

public class Main {
	private static String[] transiciones = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	//private static String[] transiciones = {"T1","T2"};
	private static String[] hab = {"False", "True" };
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GestorMonitor GDM = new GestorMonitor();
		RdP rdp = new RdP();
		boolean [] sens = rdp.sensibilizadas();
		for (int j = 0; j < sens.length; j++) {
			System.out.println(j + "->" +transiciones[j] + " = " + sens[j] + " ");
		}
		System.out.println();
		System.out.println("[MAIN] DISPARO  " + transiciones[0]+ " = "  + rdp.disparar(0));
		System.out.println();
		sens = rdp.sensibilizadas();
		for (int j = 0; j < sens.length; j++) {
			System.out.println(j + "->" + transiciones[j] + " = " + sens[j] + " ");

		}
		System.out.println();
		System.out.println("[MAIN] DISPARO " + transiciones[2] +" = " + rdp.disparar(2));
		System.out.println();

		sens = rdp.sensibilizadas();
		for (int j = 0; j < sens.length; j++) {
			System.out.println(j + "->" + transiciones[j] + " = " + sens[j] + " ");

		}
	}

}
