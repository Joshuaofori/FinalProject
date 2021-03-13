package m1.project.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import m1.project.Daos.PersonDao;
import m1.project.Service.PersonService;
import m1.project.entities.Person;
import m1.project.util.PersonValueFactory;

import java.time.LocalDate;


public class HomeScreenController {

            PersonDao personDao = new PersonDao();
            Person person= new Person();
            @FXML
            TextField firstNameTxt = new TextField();
            @FXML
            TextField lastNameTxt = new TextField();
            @FXML
            TextField nickNameTxt = new TextField();
            @FXML
            TextField birthDayTxt = new TextField();
            @FXML
            TextField emailTxt = new TextField();
            @FXML
            TextField addressTxt = new TextField();
            @FXML
            TextField phoneTxt = new TextField();

            @FXML
            TableView<Person> tableView = new TableView<>();;

            @FXML
            TableColumn<Person,String> firstNameCol= new TableColumn<>("firstname");

            @FXML
            TableColumn<Person,String>lastNameCol=new TableColumn<>("lastname");
            @FXML
            TableColumn<Person,String> nickNameCol = new TableColumn<>("nickname");
            @FXML
            TableColumn<Person,String> phoneNumberCol = new TableColumn<>("phone Number");
            @FXML
            TableColumn<Person,String> emailAddressCol = new TableColumn<>("email Address");
            @FXML
            TableColumn<Person,LocalDate> birthDateCol = new TableColumn<>("birth Date");
            @FXML
            TableColumn<Person,String> addressCol = new TableColumn<>("address");
            @FXML
            Button addButton = new Button();
            @FXML
            Button deleteButton = new Button();
            @FXML
            Button exportButton = new Button();



            @FXML
            public void initialize(){
                firstNameTxt.setPromptText("first Name");
                lastNameTxt.setPromptText("Last Name");
                nickNameTxt.setPromptText("Nick name");
                emailTxt.setPromptText("email");
                addressTxt.setPromptText("address");
                phoneTxt.setPromptText("Phone Number");
                birthDayTxt.setPromptText("Birth Date");


                firstNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
                lastNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
                nickNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNickname()));
                phoneNumberCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone_number()));
                emailAddressCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail_address()));

                addressCol.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getAddress()));
                birthDateCol.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getBirth_date()));

                firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
               firstNameCol.setOnEditCommit(t -> {
                   t.getTableView().getItems().get(
                           t.getTablePosition().getRow()).setFirstname(t.getNewValue());
                   personDao.updatePerson("firstname",t.getNewValue(), t.getTableView().getItems().get(
                           t.getTablePosition().getRow()).getId());
               });
               //Sets cellFactory to be able to edit the last name column
                lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
                /**
                 * A method for on Edit commit to edit the oberservable list with the new
                changes made to the editing column
                 */
                lastNameCol.setOnEditCommit(t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setLastname(t.getNewValue());
                    personDao.updatePerson("lastname",t.getNewValue(), t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                });
                nickNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
                nickNameCol.setOnEditCommit(t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setNickname(t.getNewValue());
                    personDao.updatePerson("nickname",t.getNewValue(), t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                });
                phoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
                phoneNumberCol.setOnEditCommit(t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setNickname(t.getNewValue());
                    personDao.updatePerson("phone_number",t.getNewValue(), t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                });
                emailAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
                emailAddressCol.setOnEditCommit(t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setEmail_address(t.getNewValue());
                    personDao.updatePerson("email_address",t.getNewValue(), t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                });
                addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
                addressCol.setOnEditCommit(t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setAddress(t.getNewValue());
                    personDao.updatePerson("address",t.getNewValue(), t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                });


                tableView.getColumns().addAll(firstNameCol,lastNameCol,nickNameCol,phoneNumberCol,emailAddressCol,addressCol,birthDateCol);

                tableView.setItems(PersonService.getPerson());

                addButton.setOnAction(e -> {
                    PersonService.addPerson(new Person(0,firstNameTxt.getText(),lastNameTxt.getText(),nickNameTxt.getText(),
                            phoneTxt.getText(),addressTxt.getText(),emailTxt.getText(), LocalDate.parse( birthDayTxt.getText())));
                          firstNameTxt.clear();lastNameTxt.clear();nickNameTxt.clear();phoneTxt.clear();addressTxt.clear();
                          addressTxt.clear();emailTxt.clear();birthDayTxt.clear();
                    tableView.setItems(PersonService.getPerson());
                });

                tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, person, t1) -> {
                      if(tableView.getSelectionModel().getSelectedItem() !=null)
                          deleteButton.setDisable(false);
                      this.person=tableView.getSelectionModel().getSelectedItem();
                });
                this.person=null;
                deleteButton.setDisable(true);
                deleteButton.setOnAction(e->{
                   PersonService.deletePerson(person);

                });
                exportButton.setOnAction(e-> {
                    for(Person p:PersonService.getPerson())
                 PersonService.exportToFile(p);


                });


            }



            }



