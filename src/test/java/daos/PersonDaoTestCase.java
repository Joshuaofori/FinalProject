package daos;

import m1.project.Daos.DataSourceFactory;
import m1.project.Daos.PersonDao;
import m1.project.entities.Person;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonDaoTestCase {
    PersonDao personDao = new PersonDao();
    @Before
    public void initDb() throws Exception {
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS person (\r\n"
                        + "  idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + "  lastname VARCHAR(45) NOT NULL,\r\n"
                        + " firstname VARCHAR(45) NOT NULL,\r\n"	+ " nickname VARCHAR(45) NOT NULL,\r\n"
                        + " phone_number VARCHAR(15) NULL,\r\n"	+ " address VARCHAR(200)  NULL,\r\n"
                        + " email_address VARCHAR(150) NULL,\r\n"	+ "  birth_date DATETIME NULL);" );
        stmt.executeUpdate("DELETE FROM person");
        stmt.executeUpdate("INSERT INTO person(idperson,lastname, firstname, nickname, phone_number, address, email_address,birth_date) "
                + "VALUES (1, 'Ofori','Joshua','anhydrous','0758153585','1 rue de mons embarol','oforijoshua37@gmail.com', 20/02/1999 )");
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldListPersons() {

        // WHEN
        List<Person> films = personDao.listPersons();


        // THEN
        assertThat(films).hasSize(1);
        assertThat(films).extracting("id","lastname", "firstname", "nickname", "phone_number", "address", "email_address","birth_date").containsOnly(
                tuple(1, "Ofori","Joshua","anhydrous","0758153585","1 rue de mons embarol","oforijoshua37@gmail.com", new Date(20/02/1999)));


    }


	 @Test
	 public void shouldUpdatePerson() throws Exception {
         //    private Integer id;

			// WHEN

         personDao.updatePerson("firstname","kwame",1);
			// THEN
			Connection connection = DataSourceFactory.getDataSource().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE firstname='kwame'");
			assertThat(resultSet.next()).isTrue();
			assertThat(resultSet.getInt("idperson")).isNotNull();
			assertThat(resultSet.getString("firstname")).isEqualTo("kwame");
			assertThat(resultSet.next()).isFalse();
			assertThat(resultSet.next()).isFalse();
			resultSet.close();
			statement.close();
			connection.close();
	 }

	@Test
	public void shouldAddPerson() throws Exception {
		//    private Integer id;

		// WHEN
		Person person = new Person(2,"Samuel","Boakye","e-brace","07536780","76 rue du trichon","samuel@gmail.com",
				 new Date(20/02/1999));
		personDao.addPerson(person);
		// THEN
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE lastname='Samuel'");
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("idperson")).isNotNull();
		assertThat(resultSet.getString("firstname")).isEqualTo("Boakye");
		assertThat(resultSet.getDate("birth_date")).isEqualTo(new Date(20/02/1999));
		assertThat(resultSet.getString("nickname")).isEqualTo("e-brace");
		assertThat(resultSet.getString("phone_number")).isEqualTo("07536780");
		assertThat(resultSet.getString("address")).isEqualTo("76 rue du trichon");
		assertThat(resultSet.getString("email_address")).isEqualTo("samuel@gmail.com");
		assertThat(resultSet.next()).isFalse();
		assertThat(resultSet.next()).isFalse();
		resultSet.close();
		statement.close();
		connection.close();
	}
	@Test
	public void shouldDeletePerson(){
    	personDao.delete(1);
		List<Person> films = personDao.listPersons();


		// THEN
		assertThat(films).hasSize(0);
	}

}
