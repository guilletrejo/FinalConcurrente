package pkt;

import static org.junit.Assert.*;

import org.junit.Test;

public class RdPTestProdCons {
	RdP rdp = new RdP(true);
	int [][]marcado_actual = new int[rdp.getN_p()][rdp.getN_t()];
	int [] vector1 = {2,3,0,1,1,3,0,1,0,1};
	int [] vector2 = {0,1,2,3,0,1,0,1,0,1};
	
	
	@Test
	public void testVector1() {
		for (int ii = 0; ii<vector1.length; ii++) rdp.disparar(vector1[ii]);
		marcado_actual = rdp.getMarcado_actual();
	//	assertEquals("AAAAAAAAAAa",2,marcado_actual[0][0]+marcado_actual[1][0]);
		assertEquals("P-invariante M(P0) y M(P1)",1,marcado_actual[0][0]+marcado_actual[1][0]);
		assertEquals("P-invariante M(P3) y M(P4)",1,marcado_actual[3][0]+marcado_actual[4][0]);
		assertEquals("P-invariante M(P2) y M(P5)",10,marcado_actual[2][0]+marcado_actual[5][0]);
	}
	
	@Test
	public void testVector2() {
		for (int ii = 0; ii<vector2.length; ii++) rdp.disparar(vector2[ii]);
		marcado_actual = rdp.getMarcado_actual();
	//	assertEquals("AAAAAAAAAAa",2,marcado_actual[0][0]+marcado_actual[1][0]);
		assertEquals("P-invariante M(P0) y M(P1)",1,marcado_actual[0][0]+marcado_actual[1][0]);
		assertEquals("P-invariante M(P3) y M(P4)",1,marcado_actual[3][0]+marcado_actual[4][0]);
		assertEquals("P-invariante M(P2) y M(P5)",10,marcado_actual[2][0]+marcado_actual[5][0]);
	}
	
}
