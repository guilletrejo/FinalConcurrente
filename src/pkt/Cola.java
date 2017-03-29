package pkt;

public class Cola {
	private boolean vector_vc;
	
	Cola (){
		this.vector_vc = false;
	}
	
	public boolean quienes_estan(){
		return vector_vc;
	}
	
	public synchronized boolean acquire(){
		vector_vc = true;
		try{
			//System.out.println("[Cola] HILO DORMIDO::"+ Thread.currentThread().getName());
			wait();
		}
		catch(InterruptedException e){}
		return true;
	}
	
	public synchronized void release(){
		vector_vc = false;
		notify();
		return;
	}	
}
