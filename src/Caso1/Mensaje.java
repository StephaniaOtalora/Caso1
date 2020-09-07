package Caso1;

public class Mensaje {
	
	private int mensaje;
	
	private Cliente cliente;
	
	public Mensaje(int pMensaje, Cliente cliente) {
		this.mensaje = pMensaje; 
		this.cliente = cliente;
	}
	
	private int getContenido() {
		return this.mensaje; 
	}
	
	private void responder() {
		mensaje ++;
	}
}
