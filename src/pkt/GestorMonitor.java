package pkt;

public class GestorMonitor {

	private static String[] name_t = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private Mutex mtx;
	private RdP rdp;
	private Cola[] colas;
	private Politicas politica;
	Thread t;


	public GestorMonitor(RdP rdp){
		this.rdp = rdp;
		this.mtx = rdp.getMtx();
		this.politica = new Politicas();
		this.colas =  new Cola[this.rdp.getN_t()];
		for(int jj = 0; jj < colas.length; jj++){
			colas[jj] = new Cola();			
		}
	}

	public void disparar_transicion(int transicion){
		boolean [] vs,vc,m;
		boolean k;
		int flag_disparo;

		k = mtx.acquire(); 	

		vc = new boolean[rdp.getN_t()];   
		m = new boolean[rdp.getN_t()];		
		vs = new boolean[rdp.getN_t()];		

		while(k){

			flag_disparo = rdp.disparar(transicion);
			System.out.println("SOy la flag  " + flag_disparo + "  Hilo:  " + Thread.currentThread());

			if (flag_disparo==0) k=false;
			else if (flag_disparo==1) k=true;
			else if (flag_disparo==2) break;
			
			if(k){
				vs = rdp.sensibilizadas();
				m = get_m(vs,vc,m);

				if(necesito_politica(m)){
					int next_transicion = politica.cual(m);					
					colas[next_transicion].release();
					break;					
				}				
				else{
					k = false;
					mtx.release();
				}
			}
			
			else {
				k = mtx.release();
				colas[transicion].acquire();
			}
		}
		
		return;
	}

	private boolean necesito_politica(boolean[] m) {
		for(boolean b : m){ if(b) return true; } return false;
	}

	private boolean[] get_m(boolean [] vs,boolean [] vc, boolean [] m) {
		for(int jj = 0; jj < this.colas.length; jj++){			
			vc[jj] = this.colas[jj].quienes_estan();
			m[jj] = vs[jj] & vc[jj];

		}
		return m;
	}

}
