package report;

@Deprecated
public class Employee {
    private String name;
    private String surname;
    private String personalNumber;

    public void setName(String n) {
        name = n;
    }

    public void setSurname(String s) {
        surname = s;
    }

    public void setPersonalNumber(String p) {
        personalNumber = p;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

}
