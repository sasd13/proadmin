package proadmin.bean.member;

public class Teacher extends AcademicMember {

    private String password;

    public Teacher() { super(); }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
