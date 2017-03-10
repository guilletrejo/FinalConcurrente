package pkt;

public class GestorMonitor {

	private Mutex mtx;
	private RdP rdp;
	private Cola[] colas;
	private Politicas politica;
	
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
		else System.out.println("[GDM] Enter al monitor \n");
		boolean [] vs,vc,m;
		
		vc = new boolean[rdp.getN_t()]; //No me gusta en mayuscula  
		m = new boolean[rdp.getN_t()];
		
		
		boolean temp_m = false;
		boolean flag_hilo_despiertado = false; //despiertado ???
		
		while(k & !flag_hilo_despiertado){
			
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
				}
				
				if(temp_m != false){
					int next_transicion = politica.cual(m);
					System.out.printf("[GDM] despierto al hilo de la cola %d \n", next_transicion);
					colas[next_transicion].release();
					flag_hilo_despiertado = true;
				}				
				else{
					k = false;
				}
			}
			else {
				mtx.release();
				System.out.printf("[GDM] Me voy a la cola %d \n", transicion);
				colas[transicion].acquire();				
			}
		}		
		mtx.release();		
		System.out.println();
		System.out.println("[GDM] Salgo del monitor");
		return true;
	}
}
