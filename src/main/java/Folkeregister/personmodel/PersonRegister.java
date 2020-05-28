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

    public ObservableList<Person> loadTestData() {
        people.clear();
        people.add(new Person("Hassan",30,15,12,1989,"m","15128972121","hassan@gmail.com","44 44 44 44"));
        people.add(new Person("Kari",10,02,05,2010,"f","02051072213","susa@gmail.com","66 44 44 44"));
        people.add(new Person("Hassan",30,19,10,1991,"m","19109199648","hassan@gmail.com","44 44 44 44"));
        return people;
    }

}
