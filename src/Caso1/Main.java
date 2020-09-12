package Caso1;

import java.io.FileInputStream;
import java.util.Properties;

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
	 * Inicializa todas las características de los objetos segun las especificaciones del archivo "configuracion.properties"
	 * @param args
	 */
	public static void main(String[] args) {		


		try{
			Properties configuracion = new Properties();

			FileInputStream input = new FileInputStream( "./data/configuracion.properties" );

			configuracion.load( input );

			int pTotales = Integer.parseInt(configuracion.getProperty("numero.clientes"));
			int servidores = Integer.parseInt(configuracion.getProperty("numero.servidores"));						
			int capacidad = Integer.parseInt(configuracion.getProperty("numero.capacidad"));
			int mensajes = Integer.parseInt(configuracion.getProperty("numero.mensajes"));


			Buffer buffer = new Buffer(capacidad, pTotales);

			for(int i=0; i<pTotales;i++)
			{
				int numMensajes = (int) (Math.random()*mensajes+1);
				Cliente c = new Cliente(numMensajes, buffer, i);
				c.start();
			}


			for (int i = 0; i < servidores; i++) {
				Servidor s= new Servidor(buffer, i);
				s.start();
			}
			
			System.out.println("Archivo cargado con exito.");

		}
		catch(Exception e)
		{
			System.out.println("Error al cargar la configuracion del programa.");
		}
	}

}
