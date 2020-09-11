package Caso1;

public class Mensaje {

	
	private int mensaje;
	
	public Mensaje(int pMensaje) {
		this.mensaje = pMensaje; 
	}

	public int getContenido() {
		return this.mensaje; 
	}

	public void responder() {
		mensaje++;
	}

}
