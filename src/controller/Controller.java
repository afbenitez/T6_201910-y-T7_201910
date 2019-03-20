package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import com.google.gson.*;
import model.utils.Sort;
import model.vo.VOMovingViolations;
import model.data_structures.*;
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
	
	private ArregloDinamico<VOMovingViolations> datos;
	
	VOMovingViolations [] muestra;
	
	
	public Controller() 
	{
		view = new MovingViolationsManagerView();
		datos=new ArregloDinamico<VOMovingViolations>(300000);
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

				view.printMessage("Ingrese el addressID del cual desea obtener las infracciones con accidente");

				int address_id=sc.nextInt();

				ArregloDinamico<VOMovingViolations> z= this.infraccionesConAccidente().get(address_id);
				System.out.println("Se encontraron "+z.darTamanio()+" infracciones que terminaron en accidente con el AddressID indicado:");
				if(z!=null)
				{
					VOMovingViolations[] violaciones=new VOMovingViolations[z.darTamanio()];
					for(int i=0;i<z.darTamanio();i++)
					{
						violaciones[i]=z.darElemento(i);
					}
					Sort.sort(violaciones, new VOMovingViolations.comparatorDate());
					for(int i=0;i<violaciones.length;i++)
					{
						if(violaciones[i]!=null)
							view.printMessage(violaciones[i].toString());
						else break;
					}
				}
				else 
					view.printMessage("El codigo no es válido o no existen infracciones asociadas a la dirección ingresada");
				break;

			case 2: 

			

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
			for(Meses meses : Meses.values())
			{ 
				Gson gson=new Gson();
				BufferedReader br = new BufferedReader(new FileReader("./data/Moving_Violations_Issued_in_"+meses+"_2018.json"));
				VOMovingViolations[] actual =  gson.fromJson(br, VOMovingViolations[].class);
				for (int i = 0; i < actual.length; i++) 
				{
					datos.agregar(actual[i]);
					meses.contar();	
				}
				view.printMessage("Numero de infracciones en el mes de " + (meses.name()) + ": " + meses.darInfracciones());
			}	
		}
		catch(Exception e)
		{ 
			e.printStackTrace(); 
		}

		muestra=new VOMovingViolations[datos.darTamanio()];
		for(int i  = 0; i < datos.darTamanio(); i++)
		{
			muestra[i] = datos.darElemento(i);
		}
		Sort.sort(muestra, new VOMovingViolations.comparadorAddressID());
		view.printMessage("Se encontraron "+datos.darTamanio()+" MovingViolations");
	}

	public LinearHash<Integer,ArregloDinamico <VOMovingViolations>> infraccionesConAccidente()
	{
		LinearHash <Integer,ArregloDinamico<VOMovingViolations>> retorno= new LinearHash<Integer,ArregloDinamico<VOMovingViolations>>(20000);
		ArregloDinamico <VOMovingViolations> aux=new ArregloDinamico<VOMovingViolations>(200);
		int address_id=muestra[0].getAddressID();

		for(int i=0;i<muestra.length;i++)
		{
			if(muestra[i].getAddressID()==address_id && muestra[i].accident())
			{
				aux.agregar(muestra[i]);
			}
			else if(muestra[i].getAddressID()!=address_id)
			{
				retorno.put(address_id,aux);
				address_id=muestra[i].getAddressID();
				aux=new ArregloDinamico<VOMovingViolations>(200);
				if(muestra[i].accident())
					aux.agregar(muestra[i]);
			}
		}
		retorno.put(address_id,aux);

		return retorno;
	}

	
}