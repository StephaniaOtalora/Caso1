package Caso1;

public class Mensaje {

	
	private int mensaje;
	
	private Cliente cliente;
	
	public Mensaje(int pMensaje, Cliente cliente) {
		this.mensaje = pMensaje; 
		this.cliente = cliente;
	}

	public int getContenido() {
		return this.mensaje; 
	}

	public void responder() {
		mensaje ++;
	}
	
	public Cliente getCliente() {
		return this.cliente; 
	}
}
