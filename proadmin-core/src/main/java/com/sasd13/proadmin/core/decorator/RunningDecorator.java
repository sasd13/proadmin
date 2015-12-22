package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.running.Running;

public class RunningDecorator extends Running {
	
	private Running running;
	private boolean deleted;
	
	public RunningDecorator(Running running) {
		this.running = running;
	}
	
	public Running getRunning() {
		return running;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
