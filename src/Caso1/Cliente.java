package Caso1;

public class Cliente extends Thread{

	private int numMensajes;

	public static Buffer buff;

	public Cliente(int numMensajes, Buffer buff) {
		this.numMensajes=numMensajes;
		this.buff=buff;
	}

	public void run() {		
		for(int i = 0; i<numMensajes; i++)
		{
			Mensaje mensaje= new Mensaje((int) (Math.random()*100), this);
			buff.enviarMensaje(mensaje);

		}
		
		buff.terminoCliente();
	}

}
