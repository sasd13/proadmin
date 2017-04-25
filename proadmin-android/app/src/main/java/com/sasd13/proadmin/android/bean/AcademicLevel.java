package com.sasd13.proadmin.android.bean;

public class AcademicLevel {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        AcademicLevel other = (AcademicLevel) obj;

        if (code == null && other.code != null)
            return false;
        else if (!code.equals(other.code))
            return false;

        return true;
    }
}
