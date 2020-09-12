package Caso1;
/**
 * 
 * @author Stephania Otalora
 * @author Cristhian Forigua
 *
 */
public class Cliente extends Thread{
	// Id que va a identificar el thread de cada cliente.
	private int id;
	
	// Número de mensajes que va a producir el cliente. 
	private int numMensajes;

	// Buffer que comparten todos los clientes. Es donde se van a realizar las solicitudes de los mensajes
	public static Buffer buff;

	/**
	 * Método constructor del thread de cada cliente
	 * @param numMensajes Número de consultas que va a solicitar el cliente
	 * @param buff Buffer para recibir las consultas y responderlas. 
	 * @param id Identificador único de cada thread cliente
	 */
	public Cliente(int numMensajes, Buffer buff, int id) {
		this.numMensajes=numMensajes;
		this.buff=buff;
		this.id=id;
	}
	
	
	/**
	 * Método run de los thread cliente.
	 * Para cada consulta del cliente se inicializa el mensaje. El cliente intenta meter el mensaje hasta que se acepte la consulta
	 */
	public void run() {	
		System.out.println("Nuevo cliente. ID: "+id+" Realizará : "+numMensajes+" consultas");
		for(int i = 0; i<numMensajes; i++)
		{
			Mensaje mensaje= new Mensaje((int) (Math.random()*100+1), this.id);
			boolean entro = false;
			System.out.println("Cliente ID: "+id+" creó consulta con mensaje: "+mensaje.getContenido());
			while (!entro){
			entro = buff.enviarMensaje(mensaje);
			}
			
		}		
		buff.terminoCliente();
	}
	
	/**
	 * Método que retorna el identificador del thread 
	 * @return Identificador del thread cliente.
	 */
	public int getIdf()
	{
		return this.id;
	}

}
