package com.sasd13.proadmin.aaa.dao;

import java.util.List;

import com.sasd13.proadmin.aaa.model.Preference;

public interface IPreferenceDAO {

	Preference find(String category, String name);

	List<Preference> findByCategory(String category);
}
