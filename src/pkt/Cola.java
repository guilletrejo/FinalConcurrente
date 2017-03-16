package pkt;

public class Cola {
	private int vector_vc;
	Thread t;
	
	Cola (){
		this.vector_vc = 0;
	}
	
	public int quienes_estan(){
		return vector_vc;
	}
	
	public synchronized boolean acquire(){
		t = Thread.currentThread();
		vector_vc ++;
		try{
			wait();
		}
		catch(InterruptedException e){}
		return true;
	}
	
	public synchronized void release(){
		vector_vc --;
		notify();
	}	
}
