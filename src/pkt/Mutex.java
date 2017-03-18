package pkt;

public class Mutex {
	private boolean mutex;

	public Mutex(){
		mutex = true;
	}
	
	public synchronized boolean acquire(){
		while (mutex == false){
			try{
				wait();
			}
			catch(InterruptedException e){}
		}
		mutex = false;
		return true;
	}

	public synchronized boolean release(){ 
		if (mutex == false){			
			mutex = true;
			notify();
			return true;
		}
		else return false;
	}

}
