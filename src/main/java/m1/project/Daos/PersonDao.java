package m1.project.Daos;

import m1.project.entities.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static m1.project.Daos.DataSourceFactory.getDataSource;

public class PersonDao {
    public List<Person> listPersons() {

               initDb();

        List<Person> listOfPerson = new ArrayList<>();

        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet results = statement.executeQuery("SELECT * FROM person")
                ) {
                    while (results.next()) {
                        listOfPerson.add(new Person(results.getInt("idperson"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date")
                        ));

                    }
                    statement.close();
                }
            }
            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return listOfPerson;

    }

    public Person getPerson(String firstname) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(

                    "SELECT * FROM person WHERE firstname =?")) {
                statement.setString(1, firstname);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Person(results.getInt("idperson"),
                                results.getString("lasttname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date")
                        );
                    }
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
        return null;
    }


    public Person addPerson(Person person) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date) VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, person.getLastname());
                statement.setString(2, person.getFirstname());
                statement.setString(3, person.getNickname());
                statement.setString(4, person.getPhone_number());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmail_address());
                statement.setDate(7,  (person.getBirth_date()));
                statement.executeUpdate();
                ResultSet ids = statement.getGeneratedKeys();
                if (ids.next()) {
                    return new Person(ids.getInt(1), person.getLastname(), person.getFirstname(), person.getNickname(), person.getPhone_number(),
                            person.getAddress(), person.getEmail_address(), person.getBirth_date());
                }

                statement.close();
            }
            connection.close();
        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePerson(String name, String value, Integer id) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE  person SET " + name + "  = ? WHERE idperson = ? ")) {
//                statement.setString(1, name);
                statement.setString(1, value);
                statement.setInt(2, id);
                statement.executeUpdate();

                statement.close();

            }
            connection.close();
            return true;
        } catch (SQLException e) {
            // Manage Exception
            System.out.println("couldn't update");
            e.printStackTrace();
        }
        return false;

    }

    public void delete(int id) {

        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE idperson = ? ")) {

                // set the corresponding param
                statement.setInt(1, id);
                // execute the delete statement
                statement.executeUpdate();
                connection.close();

            }

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean updatePersonDate(String name, Date value, Integer id) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE  person SET " + name + "  = ? WHERE idperson = ? ")) {
//                statement.setString(1, name);
                statement.setDate(1, value);
                statement.setInt(2, id);
                statement.executeUpdate();

                statement.close();

            }
            connection.close();
            return true;
        } catch (SQLException e) {
            // Manage Exception
            System.out.println("couldn't update");
            e.printStackTrace();
        }
        return false;

    }
    
    public void initDb() {
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS person (\r\n"
                            + "  idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + "  lastname VARCHAR(45) NOT NULL,\r\n"
                            + " firstname VARCHAR(45) NOT NULL,\r\n" + " nickname VARCHAR(45) NOT NULL,\r\n"
                            + " phone_number VARCHAR(15) NULL,\r\n" + " address VARCHAR(200)  NULL,\r\n"
                            + " email_address VARCHAR(150) NULL,\r\n" + "  birth_date DATETIME NULL);");
            stmt.close();
            connection.close();
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
    }

    }
    }

