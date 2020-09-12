package Caso1;

import java.util.ArrayList;
/**
 * 
 * @author Stephania Otalora
 * @author Cristhian Forigua
 *
 */
public class Buffer {
	// Arrelgo de mensajes en el buffer 
	private ArrayList<Mensaje> buff;
	
	// Capacidad del buffer. Indica cuantas consultas va a poder almacenar el buffer 
	private int capacidad;
	
	// Indica cuantos clientes en total van a realizar consultas en el buffer
	private int clientesTotal;
	
	// Indica si todavía hay clientes que espera acceder al buffer
	private boolean hayClientes;
	
	// Indica cuantos clientes hay actualmente en el bufer
	private int clientesActuales;

	/**
	 * Método constructor del Buffer. Se inicializan todos los parámetros. 
	 * Se inicializa clientesActuales en 0 ya que al inicio no hay ningún cliente
	 * Se incializa hayClientes en False porque no hay clientes al inicio
	 * @param capacidad Capacidad de consultas que puede almacenar el buffer
	 * @param pTotales  Cuantos clientes en total van a mandar consultas al buffer
	 */
	public Buffer(int capacidad, int pTotales)
	{
		this.capacidad=capacidad;
		this.buff= new ArrayList<>();
		this.clientesTotal=pTotales;
		this.hayClientes=true;
		this.clientesActuales=0;
	}
	
	/**
	 * Método que intenta ingresar un mensaje al buffer compartido. 
	 * Si el buffer se encuentra lleno retorna false indicando que no se pudo ingresar el mensaje en el buffer. De lo
	 * contrario retorna true 
	 * @param mensaje Objeto Mensaje encargado de manejar la consulta del cliente. Consulta que se intenta ingresar al buffer
	 * @return true si la consulta es recibida por el buffer, flase de lo contrario. 
	 */
	public boolean enviarMensaje(Mensaje mensaje) {

		boolean entro = false;
		synchronized (this) {
			System.out.println("Buffer. Ocupados:"+buff.size()+". Libres:"+(capacidad- buff.size()));
			if(buff.size()>=capacidad)
			{
				try {
					return entro;
				} catch (Exception e) {}
			}
		}

		synchronized (mensaje) { 
			buff.add(mensaje);
			entro=true;
			try {
				System.out.println("Ingresa consulta. Cliente ID "+mensaje.getIdCliente() +" con mensaje: " +mensaje.getContenido());
				ingresarCliente();
				mensaje.wait();
			} catch (Exception e) {	}

		}
		return entro;

	}

	/**
	 * Responde un mensaje del buffer. Si no hay consultas en el buffer se ignora. De lo contrario, se remueve una 
	 * consulta del buffer, ser responde el mensaje de la consulta y se notifica al cliente de que su mensaje fue respondido
	 */
	public void responderMensaje() {
		Mensaje m;

		synchronized(this) {
			if(buff.size() == 0)
			{
				return;
			}	
			m = (Mensaje) buff.remove(0); 
			m.responder();
			System.out.println("Se respondió consulta Cliente ID "+m.getIdCliente()+" con respuesta:"+m.getContenido());
		}

		synchronized(m)
		{
			if(m!=null) {
				m.notify();
			}
		}
	}

	/**
	 * Indica si hay clientes o no en el buffer
	 * @return
	 */
	public synchronized boolean getHayClientes() {
		return this.hayClientes;
	}

	/**
	 * Actualiza el número de clientes que hacen falta por terminar sus consutlas. 
	 * Disminuye en uno el valot de la variable clientesTotal
	 */
	public synchronized void terminoCliente() {
		this.clientesTotal--;
		if( clientesTotal == 0)
		{
			hayClientes = false;
		}
	}

	/**
	 * Actualiza el número de clientes actuales en el buffer. 
	 * Aumenta en uno la variable clientesActuales
	 */
	public synchronized void ingresarCliente()
	{

		this.clientesActuales++;
		if( clientesActuales == 1)
		{
			this.hayClientes = true;
		}


	}

	/**
	 * Indica que un mensaje fue respondido. 
	 * Disminuye en uno la variable clientesActuales
	 * @return
	 */
	public synchronized int terminoEnvio()
	{
		return this.clientesActuales--;
	}

	/**
	 * Retorna total de clientes que hacen falta por terminar las consultas
	 * @return Total de clientes por terminar sus consultas
	 */
	public synchronized int clientesTotales() {
		return this.clientesTotal;
	}

}
