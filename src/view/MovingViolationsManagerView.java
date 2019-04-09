package view;

public class MovingViolationsManagerView 
{	
	public void printMenu()
	{
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 7----------------------");
		System.out.println("0. Cargar los datos");
		System.out.println("1. Buscar información de un ObjectID");
		System.out.println("2. Buscar información de un ObjectID por un rango dado");
		System.out.println("3. Salir");
		System.out.println("Digite el número de opción para ejecutar la tarea, luego presione enter: (Ej., 1):");	
	}
	
	public void printMessage(String mensaje) 
	{
		System.out.println(mensaje);
	}
	
}
