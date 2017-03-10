package pkt;

public class Cola {
	private int vector_vc;
	
	Cola (){
		this.vector_vc = 0;
	}
	
	public int quienes_estan(){
		return vector_vc;
	}
	
	public synchronized boolean acquire(){
		vector_vc ++;
		try{
			System.out.println("DORMIDO!");
			wait();
		}
		catch(InterruptedException e){}
		return true;
	}
	
	public synchronized void release(){
		vector_vc --;
		System.out.println("DESPIERTO!");
		notify();
	}	
}
