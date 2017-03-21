package pkt;

import static org.junit.Assert.*;

import org.junit.Test;

public class RdPTestFinal {
	
	RdP rdp = new RdP(false);
	int [][]marcado_actual = new int[rdp.getN_p()][rdp.getN_t()];
	int [] vector1 = {0,1,3,4,6,8};
	//int [] vector1 = {0,1,2,3,0,1,0,1,0,1};
	
	@Test
	public void testDisparar() {
		for (int ii = 0; ii<vector1.length; ii++) rdp.disparar(vector1[ii]);
		marcado_actual = rdp.getMarcado_actual();
		assertEquals("P-invariante 1",1,marcado_actual[15][0]+marcado_actual[16][0]+marcado_actual[0][0]);
		assertEquals("P-invariante 2",1,marcado_actual[1][0]+marcado_actual[7][0]);
		assertEquals("P-invariante 3",1,marcado_actual[11][0]+marcado_actual[16][0]+marcado_actual[2][0]);
		assertEquals("P-invariante 4",1,marcado_actual[24][0]+marcado_actual[8][0]+marcado_actual[3][0]);
		assertEquals("P-invariante 5",1,marcado_actual[12][0]+marcado_actual[22][0]+marcado_actual[4][0]);
		assertEquals("P-invariante 6",10,marcado_actual[5][0]+marcado_actual[6][0]+marcado_actual[7][0]
										+marcado_actual[8][0]+marcado_actual[9][0]+marcado_actual[10][0]
										+marcado_actual[11][0]+marcado_actual[12][0]+marcado_actual[13][0]);
		assertEquals("P-invariante 7",10,marcado_actual[14][0]+marcado_actual[15][0]+marcado_actual[16][0]+marcado_actual[17][0]);
		assertEquals("P-invariante 8",1,marcado_actual[8][0]+marcado_actual[10][0]+marcado_actual[12][0]
										+marcado_actual[18][0]+marcado_actual[21][0]+marcado_actual[22][0]
										+marcado_actual[25][0]+marcado_actual[24][0]+marcado_actual[23][0]);
		assertEquals("P-invariante 9",1,marcado_actual[9][0]+marcado_actual[16][0]+marcado_actual[19][0]);
		assertEquals("P-invariante 10",10,marcado_actual[20][0]+marcado_actual[21][0]+marcado_actual[22][0]
										+marcado_actual[25][0]+marcado_actual[24][0]+marcado_actual[23][0]);
		
		assertEquals("P-invariante 11",1,marcado_actual[25][0]+marcado_actual[6][0]+marcado_actual[26][0]);
		
		assertEquals("P-invariante 12",1,marcado_actual[9][0]+marcado_actual[10][0]+marcado_actual[27][0]
										+marcado_actual[15][0]+marcado_actual[17][0]+marcado_actual[23][0]);
		assertEquals("P-invariante 13",1,marcado_actual[28][0]+marcado_actual[13][0]+marcado_actual[21][0]);
		
	}

}
