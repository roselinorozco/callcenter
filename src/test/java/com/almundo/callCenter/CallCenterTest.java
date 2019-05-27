package com.almundo.callCenter;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.PriorityBlockingQueue;

import org.junit.jupiter.api.Test;

import com.almundo.callCenter.constants.EPositionEmployee;
import com.almundo.callCenter.model.Call;
import com.almundo.callCenter.model.Employee;

//Clase mediante la cual se implementan los casos de prueba de la aplicación.
public class CallCenterTest {

	@Test
	/*10 llamadas concurrentes*/
	public void tenConcurrentsCalls() throws InterruptedException {
		
		PriorityBlockingQueue<Employee> listEmployees = getListEmployees();
		Dispatcher dispatcher = new Dispatcher(10, listEmployees);

		for (int i = 0; i < 10; i++) { dispatcher.dispatchCall(new Call()); }

		int calls = dispatcher.closeCallCentert(15);

		assertEquals("Llamadas no pudieron ser atendidas!", 10, calls);
	}

	@Test
	/*15 llamadas concurrentes*/
	public void moreConcurrentsCalls() throws InterruptedException {
		
		PriorityBlockingQueue<Employee> listEmployees = getListEmployees();
		Dispatcher dispatcher = new Dispatcher(10, listEmployees);

		for (int i = 0; i < 15; i++) {
			dispatcher.dispatchCall(new Call());
		}

		int calls = dispatcher.closeCallCentert(25);

		assertEquals("Llamadas no pudieron ser atendidas!", 15, calls);
	}

	@Test
	public void lostCalls() throws InterruptedException {
		PriorityBlockingQueue<Employee> listEmployees = getListEmployees();
		Dispatcher dispatcher = new Dispatcher(5, listEmployees);

		for (int i = 0; i < 5; i++) {
			dispatcher.dispatchCall(new Call());
		}
		dispatcher.dispatchCall(new Call(20));

		int calls = dispatcher.closeCallCentert(10);

		assertEquals("Llamadas no pudieron ser atendidas!", 6, calls);
	}

	private PriorityBlockingQueue<Employee> getListEmployees() {
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
