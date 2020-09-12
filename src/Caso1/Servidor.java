package Caso1;
/**
 * 
 * @author Stephania Otalora
 * @author Cristhian Forigua
 *
 */
public class Servidor extends Thread{
	// Buffer que va a manejar la recepci�n y respuesta de los mensajes de los clientes
	private static Buffer buff; 
	
	// Identificador del thread del servidor 
	private int id;
	
	/**
	 * M�todo constructor del thread del servidor
	 * @param pBuff Buffer en donde se recibiran y respondr�n los mensajes
	 * @param id Id �nico para cada thread del servidor
	 */
	public Servidor(Buffer pBuff, int id) {
		this.buff = pBuff; 
		this.id=id;
	}
	
	/**
	 * M�todo ejecutado por el thread al iniciar. Mientras haya clientes en el buffer a los que les faltan mensajes se 
	 * siguen respondiendo las consultas. 
	 */
	public void run() {
		while(buff.getHayClientes()||buff.clientesTotales()>0) {
			buff.responderMensaje();
			yield();
			System.out.println("Faltan "+buff.clientesTotales()+" clientes por terminar");
		}
		System.out.println("No hay m�s clientes por atender");
	}
	

}
