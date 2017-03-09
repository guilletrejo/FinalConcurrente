package pkt;

public class GestorMonitor {

	private Mutex mtx;
	private RdP rdp;
	private Colas colas;
	private Politicas politica;
	
	
	public GestorMonitor(Mutex mtx, RdP rdp, Politicas politica, Colas colas){
		this.mtx = mtx;
		this.rdp = rdp;
		this.colas = colas;
		this.politica = politica;
	}

	public void disparar_transicion(int transicion){
		boolean k = mtx.aquire(); 
		boolean [] vs,vc,m;
		boolean flag_hilo_despiertado = false;
		
		while(k & !flag_hilo_despiertado){
			k = rdp.disparar(transicion);
			if(k){
				vs = rdp.sensibilizadas();
				vc = colas.quienes_estan();
				m = vs * vc; 
				if(m != 0){
					colas.release( , politica.cual(m));
					flag_hilo_despiertado = true;
				}				
				else{
					k = false;
				}
			}
			else {
				mtx.release();
				colas.aquire(index_c, index_t);				
			}
		}
		
		mtx.release();		
	}
}
