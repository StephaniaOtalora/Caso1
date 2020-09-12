package Caso1;
/**
 * 
 * @author Stephania Otalora
 * @author Cristhian Forigua
 *
 */
public class Mensaje {

	// Mensaje de la consulta del cliente
	private int mensaje;
	
	/**
	 * Método constructor del mensaje del cliente
	 * @param pMensaje Contenido de la consulta del cliente
	 */
	private int idCliente; 
	
	public Mensaje(int pMensaje, int pIdCliente) {
		this.mensaje = pMensaje; 
		this.idCliente = pIdCliente; 
	}

	/**
	 * Retorna el contenido del mensaje del cliente
	 * @return contenido del mensaje
	 */
	public int getContenido() {
		return this.mensaje; 
	}

	/**
	 * Responde el mensaje del cliente. Aumenta en 1 el contenido del mensaje:
	 */
	public void responder() {
		mensaje++;
	}
	
	public int getIdCliente() {
		return this.idCliente; 
	}

}
