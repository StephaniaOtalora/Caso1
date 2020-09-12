package Caso1;
/**
 * 
 * @author Stephania Otalora
 * @author Cristhian Forigua
 *
 */
public class Main {

	/**
	 * Método main del proyecto. Se encarga de inicializar los threads de los clientes y de los servidores. Así mismo, 
	 * inicializa el buffer que comparte tanto cliente como servidor. El cual es el mismo tanto para clientes como servidores.
	 * @param args
	 */
	public static void main(String[] args) {
		int capacidad = 6;
		int pTotales = 3;
		int servidores = 4;
		
		Buffer buffer = new Buffer(capacidad, pTotales);
		
		
		for(int i=0; i<pTotales;i++)
		{
			int numMensajes = (int) (Math.random()*10+1);
			Cliente c = new Cliente(numMensajes, buffer, i);
			c.start();
		}
		
		for (int i = 0; i < servidores; i++) {
			Servidor s= new Servidor(buffer, i);
			s.start();
		}
		
	}

}
