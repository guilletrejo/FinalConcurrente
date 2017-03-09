package pkt;

public class Mutex {
	private boolean mutex;

	Mutex(){
		mutex = false;
	}


	public synchronized boolean aquire(){ //tomo el mutex
		if (mutex == true){			
			mutex = false;
			System.out.printf("Mutex cerrado");
			return true;
		}
		else return false;
	}

	public synchronized boolean release(){ //devuelvo el mutex
		if (mutex == false){			
			mutex = true;
			System.out.printf("Mutex abierto");
			return true;
		}
		else return false;
	}

}
