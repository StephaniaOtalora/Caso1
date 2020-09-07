package Caso1;

import java.util.ArrayList;

public class Buffer {

	private ArrayList buff	;
	private int capacidad;
	Object lleno, vacio;

	public Buffer(int capacidad)
	{
		this.capacidad=capacidad;
		buff= new ArrayList();
		lleno= new Object ();
		vacio= new Object ();
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

}
