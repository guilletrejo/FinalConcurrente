package pkt;

import java.util.concurrent.Semaphore;

public class Mutex {
	private final Semaphore semaforo;

	public Mutex(){
		semaforo = new Semaphore(1,true);
	}
	
	public boolean acquire(){
		try{
			semaforo.acquire();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		return true;
	}

	public boolean release(){ 
		semaforo.release();
		return true;
	}

}
