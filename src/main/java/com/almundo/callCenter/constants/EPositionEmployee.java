package com.almundo.callCenter.constants;

//Enumerador que representa el tipo de empleado.
public enum EPositionEmployee {
	
    OPERATOR(0, "Operador"), SUPERVISOR(1, "Supervisor"), DIRECTOR(2, "Director");
	
	private Integer idPosition;
	private String description;
	
	public Integer getIdPosition() {
		return this.idPosition;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	EPositionEmployee(Integer idPosition, String description){
		this.idPosition = idPosition;
		this.description = description;
	}
}
