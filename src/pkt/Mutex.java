package pkt;

public class Mutex {
	private boolean mutex;

	public Mutex(){
		mutex = true;
	}
	
	public synchronized boolean acquire(){ //tomo el mutex
		while (mutex == false){
			try{
				wait();
			}
			catch(InterruptedException e){}
		}
		mutex = false;
		//System.out.printf("[MUTEX] Mutex cerrado \n");
		return true;
	}

	public synchronized boolean release(){ //devuelvo el mutex
		if (mutex == false){			
			mutex = true;
			//System.out.printf("[MUTEX] Mutex abierto \n");
			notifyAll();
			return true;
		}
		else return false;
	}

}
