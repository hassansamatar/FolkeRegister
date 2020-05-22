package Folkeregister.personmodel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Person implements Serializable {
    private static final long serialVersionUID = 1;
    private transient SimpleStringProperty name;
    private transient SimpleIntegerProperty age;
    private transient SimpleIntegerProperty day;
    private transient SimpleIntegerProperty month;
    private transient SimpleIntegerProperty year;
    private transient SimpleStringProperty gender;
    private transient SimpleStringProperty fodselsnummer;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty phone;
    public Person(String name, int age, int day, int month,int year,String gender,String fodselsnummer, String email, String phone) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.day = new SimpleIntegerProperty(day);
        this.month = new SimpleIntegerProperty(month);
        this.year = new SimpleIntegerProperty(year);
        this.gender = new SimpleStringProperty(gender);
        this.fodselsnummer = new SimpleStringProperty(fodselsnummer);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.getValue();
    }

    public final void setAge(int age) {

        this.age.set(age);
    }

    public int getDay() {
        return day.getValue();
    }

    public void setDay(int day) {
        this.day.set(day);
    }
    public int getMonth() {
        return month.getValue();
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getYear() {
        return year.getValue();
    }

    public void setYear(int year) {
        this.year.set(year);
    }
    public String getGender() {
        return gender.getValue();
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }
    public String getFodselsnummer() {
        return fodselsnummer.getValue();
    }
    public void setFodselsnummer(String fodselsnummer) {
        this.fodselsnummer.set(fodselsnummer);
    }


    public String getEmail() {
        return email.getValue();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.getValue();
    }

    public void setPhone(String phone) {


        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                name.getValue(), age.getValue(), day.getValue(),month.getValue(),year.getValue(),gender.getValue(),fodselsnummer.getValue(),
                email.getValue(), phone.getValue());
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getName());
        s.writeInt(getAge());
        s.writeInt(getDay());
        s.writeInt(getMonth());
        s.writeInt(getYear());
        s.writeUTF(getEmail());
        s.writeUTF(getPhone());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String name = s.readUTF();
        int age = s.readInt();
        int day = s.readInt();
        int month = s.readInt();
        int year = s.readInt();
        String email = s.readUTF();
        String phone = s.readUTF();
        this.name = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();
        this.day = new SimpleIntegerProperty();
        this.month = new SimpleIntegerProperty();
        this.year = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        setName(name);
        setAge(age);
        setDay(day);
        setMonth(month);
        setYear(year);
        setEmail(email);
        setPhone(phone);
    }


}