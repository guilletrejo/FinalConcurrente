package pkt;

public class Mutex {
	private boolean mutex;

	Mutex(){
		mutex = false;
	}
	
	
	public boolean aquire(){ //tomo el mutex
		if (mutex == true){			
			mutex = false;
			return true;
		}
		else return false;
	}

	public boolean release(){ //devuelvo el mutex
		if (mutex == false){			
			mutex = true;
			return true;
		}
		else return false;
	}

}
