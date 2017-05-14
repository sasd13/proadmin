/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.aaa.dao.IPreferenceDAO;
import com.sasd13.proadmin.aaa.model.Preference;

@Repository
public class PreferenceDAO extends AbstractDAO implements IPreferenceDAO, IConditionnal {

	public PreferenceDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Preference find(String category, String name) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Preference prf where prf.category = :category and prf.name = :name");

		Query query = currentSession().createQuery(builder.toString());
		query.setParameter("category", category);
		query.setParameter("name", name);

		return (Preference) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Preference> findByCategory(String category) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Preference prf where prf.category = :category");

		Query query = currentSession().createQuery(builder.toString());
		query.setParameter("category", category);

		return (List<Preference>) query.getResultList();
	}
}
