package controller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;



public class Controller {

	private MovingViolationsManagerView view;

	public JsonArray loadMovingViolations() throws FileNotFoundException {
		JsonParser parser = new JsonParser();
		JsonArray resp = new JsonArray();
		String datos1 = "./data/Moving_Violations_Issued_in_January_2018.json";
		String datos2 = "./data/Moving_Violations_Issued_in_February_2018.json";
		String datos3 = "./data/Moving_Violations_Issued_in_March_2018.json";
		String datos4 = "./data/Moving_Violations_Issued_in_April_2018.json";
		String datos5 = "./data/Moving_Violations_Issued_in_May_2018.json";
		String datos6 = "./data/Moving_Violations_Issued_in_June_2018.json";
		FileReader fr1 = new FileReader(datos1);
		FileReader fr2 = new FileReader(datos2);
		FileReader fr3 = new FileReader(datos3);
		FileReader fr4 = new FileReader(datos4);
		FileReader fr5 = new FileReader(datos5);
		FileReader fr6 = new FileReader(datos6);
		
		try {


			/* Cargar todos los JsonObject (servicio) definidos en un JsonArray en el archivo */
			JsonArray arr= (JsonArray) parser.parse(fr1);
			arr.add(parser.parse(fr2));
			arr.add(parser.parse(fr3));
			arr.add(parser.parse(fr4));
			arr.add(parser.parse(fr5));
			arr.add(parser.parse(fr6));
			
			
			System.out.println("Cargó");
			resp = arr;
			
		}
		catch (JsonIOException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (JsonSyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		
		return resp;
	}

	public Controller() {
		view = new MovingViolationsManagerView();
	}

	public void run()  {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:
				try {
					this.loadMovingViolations();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

				//				case 2:
				//					IQueue<VODaylyStatistic> dailyStatistics = this.getDailyStatistics();
				//					view.printDailyStatistics(dailyStatistics);
				//					break;

			case 2:
//				view.printMensage("Ingrese el número de infracciones a buscar");
//				int n = sc.nextInt();
//
//				IStack<VOMovingViolations> violations = this.nLastAccidents(n);
//				view.printMovingViolations(violations);
				break;

			case 3:	
				fin=true;
				sc.close();
				break;
			}
		}
	}
}