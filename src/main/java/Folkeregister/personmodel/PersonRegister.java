package Folkeregister.personmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRegister implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObservableList<Person> people = FXCollections.observableArrayList();
    public List<Person> getRegister() {
        return people;
    }
    public void add(Person p) {
        people.add(p);
    }
    public void removeAll(){
        people.clear();
    }
    public void remove(Person p) {
        people.remove(p);
    }
    public void attachTableView(TableView tv) {
        tv.setItems(people);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Person p : people){
            sb.append(p.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(people));
    }
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Person> list = (List<Person>) s.readObject();
        people = FXCollections.observableArrayList();
        people.addAll(list);
    }
    /*
     * Filtering.
     */
    public ObservableList<Person> filterByName(String name) {
        return people.stream().
                filter(p -> p.getName().toLowerCase().
                        matches(String.format("%s%s%s",".*", name.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Person> filterByGender(String gender) {
        return people.stream().
                filter(p -> p.getGender().toLowerCase().
                        matches(String.format("%s%s%s",".*", gender.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Person> filterByFodselsnummer(String fodselsnummer) {
        return people.stream().
                filter(p -> p.getFodselsnummer().toLowerCase().
                        matches(String.format("%s%s%s",".*", fodselsnummer.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Person> filterByEmail(String email) {
        return people.stream().
                filter(p -> p.getEmail().
                        matches(String.format("%s%s%s",".*", email, ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Person> filterByPhone(String phone) {
        return people.stream().
                filter(p -> p.getPhone().
                        matches(String.format("%s%s%s",".*", phone, ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Person> loadTestData() {
        people.clear();
        people.add(new Person("Ole Normann",30,15,12,1989,"m","15128972121","ole@gmail.com","47 40 42 91"));
        people.add(new Person("Kari Petersen",10,02,05,2010,"f","02051072213","susa@gmail.com","91 32 48 45"));
        people.add(new Person("Hassan",28,19,10,1991,"m","19109199548","hassan@gmail.com","40 30 06 64"));
        people.add(new Person("Ali Akber",77,16,8,1942,"m","16084299549","Ali@ali.com","97 73 56 35"));
        people.add(new Person("Lidia Nani",117,11,12,1902,"f","11120249888","lidia@gmail.com","66 44 44 44"));
        people.add(new Person("Miski Leap Year",0,29,02,2020,"f","29022099861","lidia@gmail.com","46 32 67 51"));
        return people;
    }

}
