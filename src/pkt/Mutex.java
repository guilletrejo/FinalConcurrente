package pkt;

public class Mutex {
	private boolean mutex;

	public Mutex(){
		mutex = false;
	}
	
	public synchronized boolean acquire(){ //tomo el mutex
		while (mutex == false){
			try{
				wait();
			}
			catch(InterruptedException e){}
		}
		mutex = false;
		System.out.printf("Mutex cerrado");
		return true;
	}

	public synchronized boolean release(){ //devuelvo el mutex
		if (mutex == false){			
			mutex = true;
			System.out.printf("Mutex abierto");
			notifyAll();
			return true;
		}
		else return false;
	}

}
