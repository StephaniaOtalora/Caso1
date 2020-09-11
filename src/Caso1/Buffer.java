package Caso1;

import java.util.ArrayList;

public class Buffer {

	private ArrayList buff;
	private int capacidad;
	private int clientesTotal;
	private boolean hayClientes;
	private int clientesActuales;

	public Buffer(int capacidad, int pTotales)
	{
		this.capacidad=capacidad;
		this.buff= new ArrayList();
		this.clientesTotal=pTotales;
		this.hayClientes=false;
		this.clientesActuales=0;
	}

	public boolean enviarMensaje(Mensaje mensaje) {

		boolean entro = false;
		synchronized (this) {
			if(buff.size()==capacidad)
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
				mensaje.wait();
			} catch (Exception e) {	}

		}
		
		return entro;

	}

	public Mensaje responderMensaje() {
		Mensaje m = null; 

		synchronized(this) {
			if (buff.size()!=0) {
				m = (Mensaje)buff.remove(0); 
				m.responder();
				System.out.println("Respondio: "+m.getContenido());
			}
		}
		synchronized(m)
		{
			m.notify();
		}
		return m;
	}

	public synchronized boolean getHayClientes() {
		return this.hayClientes;
	}

	public synchronized void terminoCliente() {
		this.clientesActuales--;
		if( clientesActuales == 0)
		{
			hayClientes = false;
		}
	}
	
	public synchronized void ingresarCliente()
	{
		clientesActuales++;
		if( clientesActuales == 1)
		{
			hayClientes = true;
		}
	}

}
