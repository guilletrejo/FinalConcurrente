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
		this.politica = new Politicas(this.rdp.getN_p(),this.rdp.getN_t());
		this.colas =  new Cola[this.rdp.getN_t()];
		for(int jj = 0; jj < colas.length; jj++){
			colas[jj] = new Cola();			
		}
	}

	public boolean disparar_transicion(int transicion){
		boolean [] vs,vc,m;
		boolean k;
		int flag_disparo;

		k = mtx.acquire(); 	
		Pieza p;

		vc = new boolean[rdp.getN_t()];   
		m = new boolean[rdp.getN_t()];		
		vs = new boolean[rdp.getN_t()];		

		while(k){
			
			p = (Pieza)Thread.currentThread();
			
			flag_disparo = rdp.disparar(transicion);
			//System.out.println("Thread  " + p.getName() + "  PrimeraTransicion:  " + p.getTransicion()[0]);

			if (flag_disparo==0) k=false;
			else if (flag_disparo==1) k=true;
			else if (flag_disparo==2) {
				//mtx.release();
				return false;
			
			}
			else if (flag_disparo==3) {
				p.setIndice(0);
				return false;
			}
			
			if(k){
				politica.disparoOK(transicion);
//				System.out.println("[GDM] get sens");
				vs = rdp.sensibilizadas();
				m = get_m(vs,vc,m);
//				System.out.println("[GDM] get VS");
//				for(int i = 0; i<vc.length;i++)System.out.print(" "+ vs[i]+"["+name_t[i]+"]");
//				System.out.println("");
//				System.out.println("[GDM] get VC");
//				for(int i = 0; i<vc.length;i++)System.out.print(" "+ vc[i]+"["+name_t[i]+"]");
//				System.out.println("");
				
				if(necesito_politica(m)){
					//System.out.println("[GDM] politica");
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
				//System.out.println("[GDM]HILO DORMIDO::"+ Thread.currentThread().getName()+ "Transicion::"+name_t[transicion]);
				colas[transicion].acquire();
				//System.out.println("[GDM]HILO DESPIERTO::"+ Thread.currentThread().getName()+ "Transicion::"+name_t[transicion]);
			}
		}
		
		return true;
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
