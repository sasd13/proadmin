package com.sasd13.proadmin.android.model;

public class Team {

    private long id;
    private String number, name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Team other = (Team) obj;

        if (number == null && other.number != null)
            return false;
        else if (!number.equals(other.number))
            return false;

        return true;
    }
}
