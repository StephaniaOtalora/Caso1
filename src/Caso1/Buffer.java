package Caso1;

import java.util.ArrayList;

public class Buffer {

	private ArrayList buff;
	private int capacidad;
	Object vacio,lleno ;
	private int clientesTotal;

	public Buffer(int capacidad, int pTotales)
	{
		this.capacidad=capacidad;
		buff= new ArrayList();
		lleno= new Object ();;
		vacio= new Object ();
		clientesTotal=pTotales; 
	}

	public void enviarMensaje(Mensaje mensaje) {

		synchronized (lleno) {
			while(buff.size()==capacidad)
			{
				try {
					mensaje.wait();
				} catch (Exception e) {}
			}
		}

		synchronized (this) { 
			buff.add(mensaje);			
			try {
				mensaje.wait();
			} catch (Exception e) {	}

		}

		synchronized (vacio) {
			try {
				vacio.notify();
			} catch (Exception e) {	}
		}

	}

	public Mensaje responderMensaje() {
		Mensaje m = null; 

		synchronized (vacio) {
			while(buff.size()==0)
				try {
					vacio.wait();
				} catch (Exception e) {	}
		}

		synchronized(this) {
			if (buff.size()!=0) {
				m = (Mensaje)buff.remove(0); 
				m.responder();
				System.out.println("Cliente: "+m.getCliente().getIdf()+" respuesta: "+m.getContenido());
				Cliente c= m.getCliente();
				c.notify();
			}
		}
		return m;
	}

	public synchronized int darTotalClientes() {
		return this.clientesTotal;
	}

	public synchronized void terminoCliente() {
		this.clientesTotal--;
	}

}
