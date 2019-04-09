package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.*;

import model.data_structures.RedBlackTree;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller 
{

	private enum Meses
	{
		January(0),
		February(0), 
		March(0), 
		April(0), 
		May(0), 
		June(0);
		private int infracciones;

		private Meses(int cantidad)
		{ 
			this.infracciones = cantidad;
		}

		private void contar()
		{ 
			this.infracciones++; 
		}

		private int darInfracciones()
		{ 
			return infracciones; 
		}
	}
	private MovingViolationsManagerView view;
	
	private RedBlackTree<Integer,VOMovingViolations> arbol;
	
	VOMovingViolations [] muestra;
	
	public Controller() 
	{
		view = new MovingViolationsManagerView();
		arbol=new RedBlackTree<Integer,VOMovingViolations>();
	}

	public void run() 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		while(!fin)
		{
			view.printMenu();
			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				this.loadMovingViolations();
				break;
			case 1:		
				view.printMessage("Ingrese el ObjectID a consultar");
				int seleccion=sc.nextInt();
				this.buscarInformacion(seleccion);
				break;

			case 2: 
				view.printMessage("Ingrese un valor inferior al rango");
				int rango1=sc.nextInt();
				view.printMessage("Ingrese un valor superior al rango:");
				int rango2=sc.nextInt();
				this.buscarInformacionEnRango(rango1,rango2);
				break;
			case 3:
				sc.close();
				fin=true;
			}
		}
	}

	public void loadMovingViolations() 
	{
		try
		{
			for(Meses mes: Meses.values())
			{ 
				Gson gson=new Gson();
				
				BufferedReader br = new BufferedReader(new FileReader("./data/Moving_Violations_Issued_in_"+mes+"_2018.json"));
				VOMovingViolations[] actual =  gson.fromJson(br, VOMovingViolations[].class);
				
				for (int i = 0; i < actual.length; i++) 
				{
					arbol.put(actual[i].getObjectId(), actual[i]);
					mes.contar();	
				}
				
				view.printMessage("Numero de infracciones en el mes de " + (mes.name()) + ": " + mes.darInfracciones());
				
			}
			
			view.printMessage("El total de infracciones fue de: " + arbol.size());
		}

		catch(Exception e)
		{ 
			e.printStackTrace(); 
		}
	}

	public void buscarInformacion(int objectID)
	{
		view.printMessage(arbol.get(objectID).toString());
	}

	public void buscarInformacionEnRango(int inferior,int superior)
	{
		Iterator<VOMovingViolations> it = arbol.valuesInRange(inferior, superior); 
		
		while( it.hasNext())
		{
			view.printMessage(it.next().toString());
		}
	}
}