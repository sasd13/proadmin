package com.sasd13.proadmin.handler;

import com.sasd13.proadmin.SettingsActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.SettingsForm;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.ws.task.ReadTask;
import com.sasd13.proadmin.ws.task.UpdateTask;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class SettingsHandler implements Promise {

    private SettingsActivity settingsActivity;
    private boolean isActionRead;
    private ReadTask<Teacher> readTaskTeacher;

    public SettingsHandler(SettingsActivity settingsActivity) {
        this.settingsActivity = settingsActivity;
    }

    public void readTeacher(long id) {
        isActionRead = true;

        readTaskTeacher = new ReadTask<>(this, Teacher.class);
        readTaskTeacher.execute(id);
    }

    @Override
    public void onLoad() {
        if (isActionRead) {
            settingsActivity.onLoad();
        }
    }

    @Override
    public void onSuccess() {
        if (isActionRead) {
            isActionRead = false;

            onReadTaskSucceeded();
        } else {
            onUpdateTaskSucceeded();
        }
    }

    private void onReadTaskSucceeded() {
        try {
            Teacher teacher = readTaskTeacher.getResults().get(0);

            Cache.keep(settingsActivity, teacher);
            settingsActivity.onReadSucceeded(teacher);
        } catch (IndexOutOfBoundsException e) {
            settingsActivity.onError("Erreur de chargement des données");
        }
    }

    private void onUpdateTaskSucceeded() {
        settingsActivity.onUpdateSucceeded();
    }

    @Override
    public void onFail() {
        settingsActivity.onError("Echec de la connexion au serveur");
    }

    public void updateTeacher(Teacher teacher, SettingsForm settingsForm) {
        try {
            editTeacherWithForm(teacher, settingsForm);

            UpdateTask<Teacher> updateTask = new UpdateTask<>(this, Teacher.class);
            updateTask.execute(teacher);
        } catch (FormException e) {
            settingsActivity.onError(e.getMessage());
        }
    }

    private void editTeacherWithForm(Teacher teacher, SettingsForm settingsForm) throws FormException {
        Teacher teacherFromForm = settingsForm.getEditable();

        teacher.setFirstName(teacherFromForm.getFirstName());
        teacher.setLastName(teacherFromForm.getLastName());
        teacher.setEmail(teacherFromForm.getEmail());
    }

    public Teacher readTeacherFromCache(long id) {
        return Cache.load(settingsActivity, id, Teacher.class);
    }
}
