package com.almundo.callCenter.model;

import java.util.Random;

// Clase que representa una llamada.
public class Call {
	
	private static final Integer minimumTime = 5;
	private static final Integer maximumTime = 10;
	
	private Integer duration;
	
	public Call(Integer duration) {
		this.duration = duration;
	}
	
	public Call() {
		this.duration = getDurationCall();
	}
	
	public Integer getDuration() {
		return this.duration;
	}
	
	private Integer getDurationCall() {
		Random random = new Random();
		int duration = this.maximumTime - this.minimumTime + 1;
		int randomDuration =  random.nextInt(duration) + this.minimumTime;
		return randomDuration;
	}

}
