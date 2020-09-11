package Caso1;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

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
		this.hayClientes=true;
		this.clientesActuales=0;
	}

	public boolean enviarMensaje(Mensaje mensaje) {

		boolean entro = false;
		//		System.out.println(entro);
		synchronized (this) {
			System.out.println("size: "+buff.size()+"capacidad"+capacidad+" mensaje: "+mensaje.getContenido());
			if(buff.size()>=capacidad)
			{
				try {
					System.out.println("primer sinchronized");
					return entro;
				} catch (Exception e) {}
			}
		}

		synchronized (mensaje) { 
			buff.add(mensaje);
			entro=true;
			try {
				System.out.println("segundo sinchronized");
				ingresarCliente();
				mensaje.wait();
			} catch (Exception e) {	}

		}
		System.out.println(entro);
		return entro;

	}

	public void responderMensaje(Servidor serv) {
		Mensaje m;

		synchronized(this) {
			System.out.println("Capacidad responder: "+buff.size());
			if(buff.size() == 0)
			{
				serv.yield();
			}	
			m = (Mensaje) buff.remove(0); 
			m.responder();
			System.out.println("Respondio: "+m.getContenido());
		}

		synchronized(m)
		{
			if(m!=null) {
				m.notify();
			}
		}
	}

	public synchronized boolean getHayClientes() {
		return this.hayClientes;
	}

	public synchronized void terminoCliente() {
		this.clientesTotal--;
		if( clientesTotal == 0)
		{
			hayClientes = false;
		}
	}

	public synchronized void ingresarCliente()
	{

		this.clientesActuales++;
		if( clientesActuales == 1)
		{
			this.hayClientes = true;
		}


	}

	public synchronized int terminoEnvio()
	{
		return this.clientesActuales--;
	}

	public synchronized int clientesTotales() {
		// TODO Auto-generated method stub
		System.out.println(clientesTotal);
		return this.clientesTotal;
	}

}
