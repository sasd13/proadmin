/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.core.bean.member;

import com.sasd13.proadmin.core.bean.AcademicLevel;

/**
 *
 * @author Samir
 */
public class Student extends AcademicMember {
	
	private AcademicLevel academicLevel;
	
	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}
	
	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Student [");
		builder.append("id=" + getId());
		builder.append(", number=" + getNumber());
		builder.append(", firstName=" + getFirstName());
		builder.append(", lastName=" + getLastName());
		builder.append(", email=" + getEmail());
		builder.append(", academicLevel=" + getAcademicLevel());
		builder.append("]");
		
		return builder.toString().trim();
	}
}
