package br.com.insight.hourapp.web.entities.enums;

/**
 * @author Marcos Vinicius
 * @apiNote Define se um registro em "SummaryOfHoursWorked" é do tipo ATRASO ou EXTRA
 */
public enum HourType {

	LATE(1, "ATRASO"),
	OVERTIME(2, "EXTRA");
	
	private int cod;
	private String description;
	
	HourType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static HourType getDescriptionByCod(int cod) {
		if(cod == OVERTIME.cod) {
			return OVERTIME;
		} else if(cod == LATE.cod) {
			return LATE;
		} else {
			return null;
		}
	}
	
}
