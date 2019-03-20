package model.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations implements Comparable<VOMovingViolations> 
{

	private int OBJECTID;
	
	private String TICKETISSUEDATE;
	
	private String LOCATION;
	
	private String ADDRESS_ID;
	
	private int FINEAMT;
	
	private String VIOLATIONCODE;
	
	private String ACCIDENTINDICATOR;
	
	private String TOTALPAID;

	public VOMovingViolations()
	{
	}

	public int getFineAMT()
	{
		return FINEAMT;
	}

	public int getAddressID()
	{
		if(ADDRESS_ID!=null && ADDRESS_ID!="null" && !ADDRESS_ID.equals("") )
		{
			return  Integer.parseInt(ADDRESS_ID);
		}
		return 0;
	}


	public boolean accident()
	{
		return ACCIDENTINDICATOR.equalsIgnoreCase("Yes");
	}

	/**
	 * @return id - Identificador �nico de la infracci�n
	 */
	public int getObjectId() 
	{
		return OBJECTID;
	}	


	/**
	 * @return location - Direcci�n en formato de texto.
	 */
	public String getLocation() {
		return LOCATION;
	}

	/**
	 * @return date - Fecha cuando se puso la infracci�n .
	 */
	public String getTicketIssueDate() {
		return TICKETISSUEDATE;
	}

	/**
	 * @return totalPaid - Cuanto dinero efectivamente pag� el que recibi� la infracci�n en USD.
	 */
	public double getTotalPaid() {
		return Double.parseDouble(TOTALPAID);
	}

	public String getViolationCode()
	{
		return VIOLATIONCODE;
	}

	public String toString()
	{
		return		"OBJECTID: "+OBJECTID+" LOCATION: "+LOCATION+" TICKETISSUEDATE: "+TICKETISSUEDATE+" VIOLATIONCODE: "+VIOLATIONCODE+" FINEAMT: "+FINEAMT;

	}

	public static class comparatorDate implements Comparator <VOMovingViolations>
	{
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1 )
		{
			LocalDateTime fecha1 = LocalDateTime.parse(arg0.getTicketIssueDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
			LocalDateTime fecha2 = LocalDateTime.parse(arg1.getTicketIssueDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
			return fecha1.compareTo(fecha2);
		}
	}

	public static class comparadorAddressID implements Comparator<VOMovingViolations>
	{
		public int compare(VOMovingViolations obj1, VOMovingViolations obj2)
		{
			return obj1.getAddressID()-obj2.getAddressID();
		}
	}

	public int compareTo(VOMovingViolations cmp) 
	{
		if(TICKETISSUEDATE.compareTo(cmp.getTicketIssueDate())==0)
		{
			return (OBJECTID-cmp.getObjectId());
		}

		else return TICKETISSUEDATE.compareTo(cmp.getTicketIssueDate());
	}


}