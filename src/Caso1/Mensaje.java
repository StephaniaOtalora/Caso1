package Caso1;

public class Mensaje {
	
	private int mensaje; 
	
	public Mensaje(int pMensaje) {
		this.mensaje = pMensaje; 
	}
	
	private int getContenido() {
		return this.mensaje; 
	}
	
	private void responder() {
		mensaje ++;
	}
}
