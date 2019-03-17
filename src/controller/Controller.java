package controller;

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

	public void loadMovingViolations() {
		JsonParser parser = new JsonParser();

		try {
			String datos1 = "./T6_201910/data/Moving_Violations_Issued_in_January_2018.json";
			String datos2 = "./T6_201910/data/Moving_Violations_Issued_in_February_2018.json";
			String datos3 = "./T6_201910/data/Moving_Violations_Issued_in_March_2018.json";
			String datos4 = "./T6_201910/data/Moving_Violations_Issued_in_April_2018.json";
			String datos5 = "./T6_201910/data/Moving_Violations_Issued_in_May_2018.json";
			String datos6 = "./T6_201910/data/Moving_Violations_Issued_in_June_2018.json";

			/* Cargar todos los JsonObject (servicio) definidos en un JsonArray en el archivo */
			JsonArray arr= (JsonArray) parser.parse(new FileReader(datos1));
			arr.add((JsonArray)parser.parse(new FileReader(datos2)));
			arr.add((JsonArray)parser.parse(new FileReader(datos3)));
			arr.add((JsonArray)parser.parse(new FileReader(datos4)));
			arr.add((JsonArray)parser.parse(new FileReader(datos5)));
			arr.add((JsonArray)parser.parse(new FileReader(datos6)));
		}
	}

	public Controller() {
		view = new MovingViolationsManagerView();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:
				this.loadMovingViolations();
				break;

				//				case 2:
				//					IQueue<VODaylyStatistic> dailyStatistics = this.getDailyStatistics();
				//					view.printDailyStatistics(dailyStatistics);
				//					break;

			case 2:
				view.printMensage("Ingrese el número de infracciones a buscar");
				int n = sc.nextInt();

				IStack<VOMovingViolations> violations = this.nLastAccidents(n);
				view.printMovingViolations(violations);
				break;

			case 3:	
				fin=true;
				sc.close();
				break;
			}
		}
	}
}
