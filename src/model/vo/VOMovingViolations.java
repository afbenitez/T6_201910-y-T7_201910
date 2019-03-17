package model.vo;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {

	/**
	 * Id del objeto
	 */
	private String objectId;
	
	/**
	 * row
	 */
	private String row;

	/**
	 * Location del objeto
	 */
	private String location;

	/**
	 * Id de la dirección
	 */
	private String addressId;

	/**
	 * street segid
	 */
	private String segid;

	/**
	 * Coordenada x.
	 */
	private String xcoord;

	/**
	 * Coordenada y.
	 */
	private String ycoord;

	/**
	 * Tipo de ticket.
	 */
	private String ticketType;

	/**
	 * Valor multa.
	 */
	private String fine;

	/**
	 * Valor pago.
	 */
	private String paid;

	/**
	 * Penalty 1.
	 */
	private String penalty1;

	/**
	 * Penalty 2.
	 */
	private String penalty2;

	/**
	 * Indicador de accidente.
	 */
	private String accidentId;

	/**
	 * Fecha del ticket.
	 */
	private String ticketIssue;

	/**
	 * Código violado.
	 */
	private String violationCode;
	
	/**
	 * Descripción de la violación.
	 */
	private String violationDesc;

	/**
	 * Id de fila.
	 */
	private String rowId;

	/**
	 * @param objectId
	 * @param row
	 * @param location
	 * @param addressId
	 * @param segid
	 * @param xcoord
	 * @param ycoord
	 * @param ticketType
	 * @param fine
	 * @param paid
	 * @param penalty1
	 * @param penalty2
	 * @param accidentId
	 * @param ticketIssue
	 * @param violationCode
	 * @param violationDesc
	 * @param rowId
	 */
	public VOMovingViolations(String objectId, String row, String location, String addressId, String segid,
			String xcoord, String ycoord, String ticketType, String fine, String paid, String penalty1, String penalty2,
			String accidentId, String ticketIssue, String violationCode, String violationDesc, String rowId) {
		this.objectId = objectId;
		this.row = row;
		this.location = location;
		this.addressId = addressId;
		this.segid = segid;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.ticketType = ticketType;
		this.fine = fine;
		this.paid = paid;
		this.penalty1 = penalty1;
		this.penalty2 = penalty2;
		this.accidentId = accidentId;
		this.ticketIssue = ticketIssue;
		this.violationCode = violationCode;
		this.violationDesc = violationDesc;
		this.rowId = rowId;
	}

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the row
	 */
	public String getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(String row) {
		this.row = row;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the segid
	 */
	public String getSegid() {
		return segid;
	}

	/**
	 * @param segid the segid to set
	 */
	public void setSegid(String segid) {
		this.segid = segid;
	}

	/**
	 * @return the xcoord
	 */
	public String getXcoord() {
		return xcoord;
	}

	/**
	 * @param xcoord the xcoord to set
	 */
	public void setXcoord(String xcoord) {
		this.xcoord = xcoord;
	}

	/**
	 * @return the ycoord
	 */
	public String getYcoord() {
		return ycoord;
	}

	/**
	 * @param ycoord the ycoord to set
	 */
	public void setYcoord(String ycoord) {
		this.ycoord = ycoord;
	}

	/**
	 * @return the ticketType
	 */
	public String getTicketType() {
		return ticketType;
	}

	/**
	 * @param ticketType the ticketType to set
	 */
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	/**
	 * @return the fine
	 */
	public String getFine() {
		return fine;
	}

	/**
	 * @param fine the fine to set
	 */
	public void setFine(String fine) {
		this.fine = fine;
	}

	/**
	 * @return the paid
	 */
	public String getPaid() {
		return paid;
	}

	/**
	 * @param paid the paid to set
	 */
	public void setPaid(String paid) {
		this.paid = paid;
	}

	/**
	 * @return the penalty1
	 */
	public String getPenalty1() {
		return penalty1;
	}

	/**
	 * @param penalty1 the penalty1 to set
	 */
	public void setPenalty1(String penalty1) {
		this.penalty1 = penalty1;
	}

	/**
	 * @return the penalty2
	 */
	public String getPenalty2() {
		return penalty2;
	}

	/**
	 * @param penalty2 the penalty2 to set
	 */
	public void setPenalty2(String penalty2) {
		this.penalty2 = penalty2;
	}

	/**
	 * @return the accidentId
	 */
	public String getAccidentId() {
		return accidentId;
	}

	/**
	 * @param accidentId the accidentId to set
	 */
	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}

	/**
	 * @return the ticketIssue
	 */
	public String getTicketIssue() {
		return ticketIssue;
	}

	/**
	 * @param ticketIssue the ticketIssue to set
	 */
	public void setTicketIssue(String ticketIssue) {
		this.ticketIssue = ticketIssue;
	}

	/**
	 * @return the violationCode
	 */
	public String getViolationCode() {
		return violationCode;
	}

	/**
	 * @param violationCode the violationCode to set
	 */
	public void setViolationCode(String violationCode) {
		this.violationCode = violationCode;
	}

	/**
	 * @return the violationDesc
	 */
	public String getViolationDesc() {
		return violationDesc;
	}

	/**
	 * @param violationDesc the violationDesc to set
	 */
	public void setViolationDesc(String violationDesc) {
		this.violationDesc = violationDesc;
	}

	/**
	 * @return the rowId
	 */
	public String getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}


	}