package m1.project.Service;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import m1.project.Daos.PersonDao;
import m1.project.entities.Person;


import java.io.*;
import java.time.LocalDate;

public class PersonService {


   static PersonDao personDao = new PersonDao();
    private ObservableList<Person> personObservableList;

    private PersonService(){
        personDao.addPerson(new Person(1, "Ofori","Joshua","anhydrous","0758153585","1 rue de mons embarol",
                        "oforijoshua37@gmail.com", LocalDate.parse("1999-02-20")));
        personDao.addPerson(new Person(2,"Samuel","Boakye","e-brace","07536780","76 rue du trichon","samuel@gmail.com",
                LocalDate.parse("2001-02-20")));
        personDao.addPerson(new Person(2,"Adjei","Anthonh","e-brace","07536780","76 rue du trichon","samuel@gmail.com",
                LocalDate.parse("2001-02-20")));
        personDao.addPerson(new Person(2,"Harris","Boakye","e-brace","07536780","76 rue du trichon","samuel@gmail.com",
                LocalDate.parse("2001-02-20")));
        personDao.addPerson(new Person(2,"Chris","Boakye","e-brace","07536780","76 rue du trichon","samuel@gmail.com",
                LocalDate.parse("2001-02-20")));
        personObservableList= FXCollections.observableArrayList();
        for(int i=0;i<personDao.listPersons().size();i++){
            personObservableList.add(personDao.listPersons().get(i));
        }



    }
    public static ObservableList<Person> getPerson() {
        return PersonServiceHolder.INSTANCE.personObservableList;
    }

    public static void addPerson(Person person) {
        personDao.addPerson(person);
        PersonServiceHolder.INSTANCE.personObservableList.add(person);
    }
    public static void updatePerson(Person person){

//        addressCol.setOnEditCommit(t -> {
//            t.getTableView().getItems().get(
//                    t.getTablePosition().getRow()).setAddress(t.getNewValue());
//            personDao.updatePerson("address",t.getNewValue(), t.getTableView().getItems().get(
//                    t.getTablePosition().getRow()).getId());
//        });
    }
    public static void deletePerson(Person person){
        personDao.delete(person.getId());
        PersonServiceHolder.INSTANCE.personObservableList.remove(person);
    }
    public static void exportToFile(Person p ) {

        try {
            File f = new File("./src/main/resources/files/persons.vcf");
            FileOutputStream fop = new FileOutputStream(f,true);
            if (f.exists()) {
                String str = "BEGIN:VCARD\n" + "VERSION:4.0\n"+ "N:Person;;;\n" + "ID:" + p.getId() + "\n"
                        + "FIRSTNAME:" + p.getFirstname() + "\n" + "LASTNAME:" + p.getLastname()
                        + "\n" + "NICKNAME:" + p.getNickname() + "\n" + "PHONENUMBER:"
                        + p.getPhone_number() + "\n" + "EMAIL:" + p.getEmail_address() + "\n"
                        + "ADDRESS:" + p.getAddress() + "BIRTHDAY:"
                        + p.getBirth_date()+ "\n"

                        + "END:VCARD";

                fop.write(str.getBytes());

                fop.flush();
                fop.close();
                System.out.println("The data has been written");
            } else
                System.out.println("This file does not exist");
        }
        catch (IOException e){

        }

    }
    public static void readFile(){
        try{
                        BufferedReader br = null;
                String sCurrentLine;
                br = new BufferedReader(new FileReader("./src/main/resources/files/persons.vcf"));
                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                   // System.out.println(f.getAbsolutePath());
                }
                br.close();

}
        catch (IOException e){
            e.printStackTrace();

                }
    }



    private static class PersonServiceHolder {
        private static final PersonService INSTANCE = new PersonService();
    }
}
