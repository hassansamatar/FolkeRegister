package Folkeregister.personmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class PersonRegister {
    private static final long serialVersionUID = 1;
    private transient ObservableList<Person> people = FXCollections.observableArrayList();
    public List<Person> getRegister() {
        return people;
    }
    public void add(Person p) {
        people.add(p);
    }
    public void removeAll() {
        people.clear();
    }
    public void remove(Person p) {
        people.remove(p);
    }

    public void attachTableView(TableView tv) {
        tv.setItems(people);
    }
    public ObservableList<Person> loadTestData() {
       people.clear();
        people.add(new Person("Hassan",30,15,12,1989,"m","15128978121","hassan@gmail.com","44 44 44 44"));
        people.add(new Person("Kari",9,02,05,2010,"f","02051092213","susa@gmail.com","66 44 44 44"));
       // people.add(new Person("Hassan",30,15,9,1892,"hassan@gmail.com","44 44 44 44"));
        return people;
    }

}
