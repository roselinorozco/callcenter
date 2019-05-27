package com.almundo.callCenter.model;

import java.util.logging.Logger;

import com.almundo.callCenter.constants.EPositionEmployee;

//Clase que representa un empleado.
public class Employee implements Comparable<Employee>{
	
	private final static Logger LOGGER = Logger.getLogger(Employee.class.getName());
	private EPositionEmployee position;
	private String name;
	
	public Employee(EPositionEmployee position) {
		this.position = position;
	}
	
	public Employee(EPositionEmployee position, String name) {
		this.position = position;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		if(name != null) {
			return name;
		}else {
			return "Anonymous";
		}
	}
	
	public EPositionEmployee getPosition() {
		return this.position;
	}
	
	public int compareTo(Employee employee) {
		return this.position.getIdPosition().compareTo(employee.position.getIdPosition());
	}
	
	public void answerCall(Call call) {
	
		LOGGER.info("Llamada atendida por " + this.toString());
		try {
			Thread.sleep(call.getDuration() * 1000);
		} catch (InterruptedException e) {
			LOGGER.warning(this.toString()+": La llamada ha sido interrumpida.");
		}
		LOGGER.info(this.toString()+" La llamada ha finalizado." +  " Duración " + call.getDuration() + " segundos");
	
	}
	
	@Override
	public String toString() {
		return this.getName() + " ("+this.position.getDescription()+")";
	}
}
