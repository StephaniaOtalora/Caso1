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
		System.out.println("Cliente: "+id+"Mensajes: "+numMensajes);
		for(int i = 0; i<numMensajes; i++)
		{
			Mensaje mensaje= new Mensaje((int) (Math.random()*100+1));
			boolean entro = false;
			System.out.println("Cliente: "+id+"mensaje: "+mensaje.getContenido());
			while (!entro){
			entro = buff.enviarMensaje(mensaje);
			}
			
		}		
		buff.terminoCliente();
	}
	
	public int getIdf()
	{
		return this.id;
	}

}
