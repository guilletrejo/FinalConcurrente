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
			System.out.println(t.getName() + " DORMIDO!");
			wait();
			System.out.println(t.getName() + " DESPIERTO!");
		}
		catch(InterruptedException e){}
		return true;
	}
	
	public synchronized void release(){
		vector_vc --;
		notify();
	}	
}
