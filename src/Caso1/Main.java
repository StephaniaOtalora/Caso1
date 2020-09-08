package Caso1;

public class Main {

	public static void main(String[] args) {
		int capacidad = 6;
		int pTotales = 10;
		int servidores = 4;
		
		Buffer buffer = new Buffer(capacidad, pTotales);
		
		for (int i = 0; i < servidores; i++) {
			Servidor s= new Servidor(buffer, i);
			s.start();
		}
		
		for(int i=0; i<pTotales;i++)
		{
			int numMensajes = (int) (Math.random()*10+1);
			Cliente c = new Cliente(numMensajes, buffer, i);
			c.start();
		}
		
	}

}
