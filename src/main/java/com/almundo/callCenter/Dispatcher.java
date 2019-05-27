package com.almundo.callCenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.almundo.callCenter.model.Call;
import com.almundo.callCenter.model.Employee;

//Clase encargada de manejar las llamadas del call center.
public class Dispatcher {
	
	private final static Logger LOGGER = Logger.getLogger(Dispatcher.class.getName());

	private ExecutorService executorService;
	private PriorityBlockingQueue<Employee> queue;
	private int calls;
	private Employee employee;
	
	/* Recibe la cantidad de llamadas a procesar por el call center,
	 * Y la lista de empleados. 
	 */
	public Dispatcher(int concurrentsCalls, PriorityBlockingQueue<Employee> employees) {
		this.executorService = Executors.newFixedThreadPool(concurrentsCalls);
		this.queue = employees;
		this.calls = 0;
	}
	
	/* Asigna la llamada a un empleado disponible
	 * mediante el tipo PriorityBlockingQueue, para manejar la cola de llamadas en espera.
	 */ 
	public void dispatchCall(Call call) throws InterruptedException {
		this.employee = this.queue.take();
		if(this.employee == null) {
		  LOGGER.warning("Nuestros empleados estan ocupados!");
		} else {
		  this.executorService.submit(this.assingCall(employee,call));
		}
	}
	
	/* Task para asignar una llamada al empleado correspondiente
	 * disponible.
	 */
	private Runnable assingCall(Employee employee, Call call) {
		Runnable runnableTask = () -> {
			this.calls ++;
			this.employee.answerCall(call);
			this.queue.add(this.employee);
		};
		return runnableTask;
	}
	
	/* Método que espera que los threads terminen de ejecutar 
	 * para cerrar la llamada.
     */
	public int closeCallCentert(long time) {
		LOGGER.info("Cerrando llamadas en curso...");
		this.executorService.shutdown();
        try {
            if (!this.executorService.awaitTermination(time, TimeUnit.SECONDS)) {
            	LOGGER.warning("Ha ocurrido un error, no se completaron algunas llamadas!");
            	this.executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
        		LOGGER.warning("Interrupted!");
        }
		LOGGER.info("Total llamadas atendidas " + this.calls);
		
		return this.calls;
	}
}
