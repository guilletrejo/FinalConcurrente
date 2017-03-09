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
		this.colas = new Cola[this.rdp.getN_t()];
	}

	public void disparar_transicion(int transicion){
		boolean k = mtx.acquire(); 
		boolean [] vs,vc,m;
		boolean temp_m = false;
		boolean flag_hilo_despiertado = false;
		
		while(k & !flag_hilo_despiertado){
			k = rdp.disparar(transicion);
			if(k){
				
				vs = rdp.sensibilizadas();				
				
				for(int jj = 0; jj < colas.length; jj++){	
					for (int hh = 0; hh < vc.length; hh++){
						vc[hh] = vc[hh] || (colas[jj].quienes_estan() > 0); 
					}
				}							
				
				if(vc.length != vs.length)
					System.out.print("el tamaño de vs y vc es distinto");
				
				for(int rr = 0; rr< vc.length; rr++){
					m[rr] = vs[rr] && vc[rr];
					temp_m = m[rr] || temp_m;
				}
				
				if(temp_m != false){
					int next_transicion = politica.cual(m);
					System.out.printf("despierto al hilo de la cola %d", next_transicion);
					colas[next_transicion].release();
					flag_hilo_despiertado = true;
				}				
				else{
					k = false;
				}
			}
			else {
				mtx.release();
				System.out.printf("Me voy a la cola %d", transicion);
				colas[transicion].acquire();				
			}
		}		
		mtx.release();		
	}
}
