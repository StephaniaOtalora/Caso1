package Caso1;

public class Servidor extends Thread{
	
	private static Buffer buff; 
	
	public Servidor(Buffer pBuff) {
		this.buff = pBuff; 
	}
	
	public void run() {
		while(buff.darTotalClientes()>0) {
			buff.responderMensaje();
			yield();
		}
	}
	

}
