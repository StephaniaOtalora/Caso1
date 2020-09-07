package Caso1;

public class Cliente extends Thread{

	private int numMensajes;

	public static Buffer buff;

	private Mensaje mensaje;


	public Cliente(int numMensajes, Buffer buff) {
		this.numMensajes=numMensajes;
		this.buff=buff;
		this.mensaje= null;
	}

	public void run() {		
		for(int i = 0; i<numMensajes; i++)
		{
			mensaje= new Mensaje((int) (Math.random()*100));
			buff.enviarMensaje(mensaje);

		}
	}

}
