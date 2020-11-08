package CONTROLLER;

import model.StudentDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class StudentsController {

    public StudentDAO studentDAO;


    @FXML
    public TableView<Student> studentTable;

    @FXML
    public TableColumn<Student, String> studentID;
    @FXML
    public TableColumn<Student, String> studentName;
    @FXML
    public TableColumn<Student, Integer> studentAge;
    @FXML
    public TableColumn<Student, String> studentAddress;
    @FXML
    public TableColumn<Student, Double> studentGPA;

    @FXML
    public TextField idField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField gpaField;
    @FXML
    private TextField searchID;


    @FXML
    PieChart pieChart;

    public void load(ActionEvent e) {
        try {
            final Double GIOI = 8.0;
            final Double KHA = 6.0;

            int HSGvalue = 0;
            int HSKvalue = 0;
            int HSYvalue = 0;
            for (Student s : studentList) {
                if (s.getGPA() > GIOI){
                    HSGvalue += 1;
                }
                else if (s.getGPA() > KHA && s.getGPA() < GIOI){
                    HSKvalue += 1;
                }
                else {
                    HSYvalue += 1;
                }
            }

            PieChart.Data HSG = new PieChart.Data("Hoc sinh Gioi", HSGvalue);
            PieChart.Data HSK = new PieChart.Data("Hoc sinh Kha", HSKvalue);
            PieChart.Data HSY = new PieChart.Data("Hoc sinh Yeu", HSYvalue);
            pieChart.getData().clear();
            pieChart.getData().addAll(HSG, HSK, HSY);
        } catch (Exception ex) {

        }


    }


    public ObservableList<Student> studentList = FXCollections.observableArrayList(
            new Student("1", "HO DUC HAI", 19, "Ha Noi", 9.0),
            new Student("2", "LE QUANG DUY", 20, "Vinh Phuc", 8.0),
            new Student("3", "NGUYEN HUU THO", 16, "Hai Phong", 4.0)

    );


    @FXML
    public void initialize() {


        studentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        studentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        studentGPA.setCellValueFactory(new PropertyValueFactory<>("GPA"));

        studentTable.setItems(studentList);


        searchID.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                seacherID((String) oldValue, (String) newValue);

            }
        });


        studentTable.setEditable(true);

        studentID.setCellFactory(TextFieldTableCell.forTableColumn());
        studentName.setCellFactory(TextFieldTableCell.forTableColumn());
        studentAge.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        studentAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        studentGPA.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


    }

    public void seacherID(String oldValue, String newValue) {
        ObservableList<Student> filteredList = FXCollections.observableArrayList();
        if (searchID == null || (newValue.length() < oldValue.length()) || newValue == null) {
            studentTable.setItems(studentList);
        } else {
            newValue = newValue.toUpperCase();
            for (Student students : studentTable.getItems()) {
                String filterID = students.getId();
                if (filterID.toUpperCase().contains(newValue) || filterID.toUpperCase().contains(newValue)) {
                    filteredList.add(students);
                }
            }
            studentTable.setItems(filteredList);
        }
    }

    @FXML
    public void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        Integer age = Integer.valueOf(ageField.getText());
        String address = addressField.getText();
        Double gpa = Double.valueOf(gpaField.getText());

        Student s = new Student(id, name, age, address, gpa);
        studentList.add(s);
    }

    public void delete(ActionEvent e) {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        studentList.remove(selected);
    }

    public void clearField(ActionEvent e) {
        idField.clear();
        nameField.clear();
        ageField.clear();
        addressField.clear();
        gpaField.clear();
    }


    public void idColumEdit(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        student.setId(studentStringCellEditEvent.getNewValue());
    }

    public void nameColumEdit(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        student.setName(studentStringCellEditEvent.getNewValue());
    }

    public void ageColumEdit(TableColumn.CellEditEvent<Student, Integer> studentIntegerCellEditEvent) {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        student.setAge(studentIntegerCellEditEvent.getNewValue());
    }

    public void addressColumEdit(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        student.setAddress(studentStringCellEditEvent.getNewValue());
    }

    public void gpaColumEdit(TableColumn.CellEditEvent<Student, Double> studentDoubleCellEditEvent) {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        student.setGPA(studentDoubleCellEditEvent.getNewValue());
    }
}
