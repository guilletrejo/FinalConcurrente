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
		}
		catch(InterruptedException e){}
		return true;
	}
	
	public synchronized void release(){
		t = Thread.currentThread();
		vector_vc --;
		notify();
		System.out.println(t.getName() + " DESPIERTO!");
	}	
}
