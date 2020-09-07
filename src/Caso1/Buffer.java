package Caso1;

import java.util.ArrayList;

public class Buffer {

	private ArrayList buff;
	private int capacidad;
	Object lleno, vacio;
	private int clientesTotal; 

	public Buffer(int capacidad, int pTotales)
	{
		this.capacidad=capacidad;
		buff= new ArrayList();
		lleno= new Object ();
		vacio= new Object ();
		clientesTotal=pTotales; 
	}
	
	public void enviarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		synchronized (lleno) {
			while(buff.size()==capacidad)
			{
				try {
					lleno.wait();
				} catch (Exception e) {}
			}

		}

		synchronized (this) { 
			buff.add(mensaje);			
		}

		synchronized (vacio) {
			vacio.notify();
		}

	}

	public Mensaje responderMensaje() {
		if (buff.size()!=0) {
			Mensaje m = (Mensaje)buff.remove(0); 
			m.responder();
			
		}
		return null;
	}

	public synchronized int darTotalClientes() {
		return this.clientesTotal;
	}

	public synchronized void terminoCliente() {
		this.clientesTotal--;
	}

}
