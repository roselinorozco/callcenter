package com.almundo.callCenter;

import java.util.concurrent.PriorityBlockingQueue;

import com.almundo.callCenter.constants.EPositionEmployee;
import com.almundo.callCenter.model.Call;
import com.almundo.callCenter.model.Employee;

/**
 * Call Center Simulator
 *
 */
public class App {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("***** WELCOME TO CALL CENTER SIMULATOR *****");

		// Se genera la lista de empleados disponibles.
		PriorityBlockingQueue<Employee> listEmployees = getListEmployees();
		Dispatcher dispatcher = new Dispatcher(10, listEmployees);

		for (int i = 0; i < 10; i++) {
			dispatcher.dispatchCall(new Call());
		}

		dispatcher.closeCallCentert(30);
	}

	private static PriorityBlockingQueue<Employee> getListEmployees() {

		PriorityBlockingQueue<Employee> listEmployees = new PriorityBlockingQueue<Employee>();

		// 1 director
		listEmployees.add(new Employee(EPositionEmployee.DIRECTOR, "Kaoru Kamilla"));

		// 2 supervisores
		listEmployees.add(new Employee(EPositionEmployee.SUPERVISOR, "Javier Linares"));
		listEmployees.add(new Employee(EPositionEmployee.SUPERVISOR, "Jesús Orozco"));

		// 10 operadores
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Roselin Orozco"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Alejandro Linares"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Sakura Card Captor"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Coronel Meow"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Coco Linares"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Esther Arbelaez"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "José Villamizar"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Selena Quintanilla"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Sia Furler"));
		listEmployees.add(new Employee(EPositionEmployee.OPERATOR, "Patricia Teheran"));

		return listEmployees;
	}
}
