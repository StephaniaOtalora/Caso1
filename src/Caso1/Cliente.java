package Caso1;

public class Cliente extends Thread{

	private int id;
	private int numMensajes;

	public static Buffer buff;

	public Cliente(int numMensajes, Buffer buff, int id) {
		this.numMensajes=numMensajes;
		this.buff=buff;
		this.id=id;
	}

	public void run() {		
		for(int i = 0; i<numMensajes; i++)
		{
			Mensaje mensaje= new Mensaje((int) (Math.random()*100+1), this);
			System.out.println("Cliente: "+id+" mensaje: "+mensaje.getContenido());
			buff.enviarMensaje(mensaje);
			System.out.println("Notify cliente: "+id);
		}		
		buff.terminoCliente();
	}
	
	public int getIdf()
	{
		return this.id;
	}

}
