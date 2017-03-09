package pkt;

public class Mutex {
	private boolean mutex;

	public Mutex(){
		mutex = false;
	}
	
	public synchronized boolean aquire(){ //tomo el mutex
		if (mutex == true){			
			mutex = false;
			return true;
		}
		else return false;
	}

	public synchronized boolean release(){ //devuelvo el mutex
		if (mutex == false){			
			mutex = true;
			return true;
		}
		else return false;
	}

}
