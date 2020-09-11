package Caso1;

public class Servidor extends Thread{
	
	private static Buffer buff; 
	private int id;
	
	public Servidor(Buffer pBuff, int id) {
		this.buff = pBuff; 
		this.id=id;
	}
	
	public void run() {
		
		while(buff.getHayClientes()||buff.clientesTotales()>0) {
			
			buff.responderMensaje(this);
			System.out.println("totales"+buff.clientesTotales());
		}
	}
	

}
