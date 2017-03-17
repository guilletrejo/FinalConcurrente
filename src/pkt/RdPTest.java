package pkt;

import static org.junit.Assert.*;

import org.junit.Test;

public class RdPTest {
	RdP rdp = new RdP(true);
	int [][]marcado_actual = new int[rdp.getN_p()][rdp.getN_t()];
	
	
	
	@Test
	public void testDisparar() {
		rdp.disparar(0);
		//rdp.disparar(1);
		//rdp.disparar(0);
		//rdp.disparar(1);
		marcado_actual = rdp.getMarcado_actual();
		
		for (int ii = 0 ; ii< marcado_actual.length;ii++)System.out.print(marcado_actual[ii][0]+"\n");
		assertEquals("AAAAAAAAAAa",1,marcado_actual[0][0]+marcado_actual[1][0]);
	}
	

}
