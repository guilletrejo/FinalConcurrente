package pkt;

public class GestorMonitor {
	
	private static String[] name_t = {"T0","T11","T12","T13","T15","T16","T17","T18","T19","T21","T22","T23","T24","T3","T31","T32","T33","T34","T35","T36"};
	private Mutex mtx;
	private RdP rdp;
	private Cola[] colas;
	private Politicas politica;
	Thread t;
	
	
	public GestorMonitor(RdP rdp){
		this.mtx = new Mutex();
		this.politica = new Politicas();
		this.rdp = rdp;
		this.colas =  new Cola[this.rdp.getN_t()];
		for(int jj = 0; jj < colas.length; jj++){
			colas[jj] = new Cola();
		}
	}

	public boolean disparar_transicion(int transicion){
		boolean k = mtx.acquire(); 
		if (k == false) return false; 
		else{
			t = Thread.currentThread();
			System.out.println("[GDM] Enter al monitor - >" + t.getName() + "\n");
		}
		boolean [] vs,vc,m;
		
		vc = new boolean[rdp.getN_t()]; //No me gusta en mayuscula  
		m = new boolean[rdp.getN_t()];
		
		
		boolean temp_m = false;
		boolean flag_hilo_despiertado = false; //despiertado ???
		
		while(k){
			
			k = rdp.disparar(transicion);
			if(k){
				vs = rdp.sensibilizadas();				
				
//				for(int jj = 0; jj < colas.length; jj++){	
//					for (int hh = 0; hh < vc.length; hh++){
//						vc[hh] = vc[hh] || (colas[jj].quienes_estan() > 0); 
//					}
//				}							
//				
//				if(vc.length != vs.length)
//					System.out.print("el tamaño de vs y vc es distinto");
//				
//				for(int rr = 0; rr< vc.length; rr++){
//					m[rr] = vs[rr] && vc[rr];
//					temp_m = m[rr] || temp_m;
//				}
				
				for(int jj = 0; jj < colas.length; jj++){
					vc[jj] = (this.colas[jj].quienes_estan() > 0);
					m[jj] = vs[jj] & vc[jj];
					if (m[jj] == true) temp_m = true;
				}
				
				if(temp_m != false){
					int next_transicion = politica.cual(m);
					System.out.printf("[GDM] despierto al hilo de la cola " + name_t[next_transicion] + "\n" );
					colas[next_transicion].release();
					flag_hilo_despiertado = true;
					break;
				}				
				else{
					k = false;
				}
			}
			else {
				mtx.release();
				System.out.printf("[GDM] Me voy a la cola " + name_t[transicion] + t.getName() + "\n" );
				colas[transicion].acquire();		
				return false;
			}
		}	
		System.out.println();
		System.out.println("[GDM] Salgo del monitor- >" + t.getName() + "\n");
		mtx.release();				
		return true;
	}
}
