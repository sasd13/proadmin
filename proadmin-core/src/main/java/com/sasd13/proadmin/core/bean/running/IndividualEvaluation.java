/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.core.bean.running;

/**
 *
 * @author Samir
 */
public class IndividualEvaluation extends Evaluation {
	
	private float mark;
	
	public float getMark() {
		return mark;
	}
	
	public void setMark(float mark) {
		this.mark = mark;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("IndividualEvaluation [");
		builder.append("id=" + getId());
		builder.append(", mark=" + getMark());
		builder.append("]");
		
		return builder.toString().trim();
	}
}
