package br.com.insight.hourapp.web.resources.enums;

public enum CalculateMode {
	
	MARKER_LESS_SCHEDULE(1),
	SCHEDULE_LESS_MARKER(2);
	
	private int cod;
	
	CalculateMode(int cod){
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}
	
	public static CalculateMode valueOfCod(int cod) {
		if(cod == SCHEDULE_LESS_MARKER.cod) {
			return SCHEDULE_LESS_MARKER;
		} else {
			return MARKER_LESS_SCHEDULE;
		}
	}
	
}
