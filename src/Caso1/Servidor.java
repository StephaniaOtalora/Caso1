package Caso1;
/**
 * 
 * @author Stephania Otalora
 * @author Cristhian Forigua
 *
 */
public class Servidor extends Thread{
	// Buffer que va a manejar la recepción y respuesta de los mensajes de los clientes
	private static Buffer buff; 
	
	// Identificador del thread del servidor 
	private int id;
	
	/**
	 * Método constructor del thread del servidor
	 * @param pBuff Buffer en donde se recibiran y respondrán los mensajes
	 * @param id Id único para cada thread del servidor
	 */
	public Servidor(Buffer pBuff, int id) {
		this.buff = pBuff; 
		this.id=id;
	}
	
	/**
	 * Método ejecutado por el thread al iniciar. Mientras haya clientes en el buffer a los que les faltan mensajes se 
	 * siguen respondiendo las consultas. 
	 */
	public void run() {
		while(buff.getHayClientes()||buff.clientesTotales()>0) {
			buff.responderMensaje();
			yield();
			System.out.println("Faltan "+buff.clientesTotales()+" clientes por terminar");
		}
		System.out.println("No hay más clientes por atender");
	}
	

}
