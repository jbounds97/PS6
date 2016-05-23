package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import domain.PersonDomainModel;
import java.util.Date;
import java.util.UUID;

public class Person_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddPerson() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			String expectedFirstName = "George";
			String expectedLastName = "Bush";
			Date expectedDate = dateFormat.parse("1946-07-06");
			String expectedCity = "Dallas";
			String expectedStreet = "Some Street";
			String expectedPostalCode = "55555";
			
			PersonDomainModel Person = new PersonDomainModel();
			Person.setFirstName("George");
			Person.setMiddleName("W");
			Person.setLastName("Bush");
			Person.setPostalCode(19242);
			Person.setCity("Toronto");
			Person.setBirthday(dateFormat.parse("1946-07-06"));
			PersonDAL.addPerson(Person);
			
			String actualFirstName = PersonDAL.getPerson(Person.getPersonID()).getFirstName();
			String actualMiddleName = PersonDAL.getPerson(Person.getPersonID()).getMiddleName();
			String actualLastName = PersonDAL.getPerson(Person.getPersonID()).getLastName();
			Date actualDate = PersonDAL.getPerson(Person.getPersonID()).getBirthday();
			String actualCity = PersonDAL.getPerson(Person.getPersonID()).getCity();
			String actualStreet = PersonDAL.getPerson(Person.getPersonID()).getStreet();
			int actualPostalCode = PersonDAL.getPerson(Person.getPersonID()).getPostalCode();
			UUID actualUUID = PersonDAL.getPerson(Person.getPersonID()).getPersonID();
			
			//Test variables in Person, do they match database?
			assertEquals(expectedFirstName, actualFirstName);
			assertEquals(expectedLastName, actualLastName);
			assertEquals(expectedDate, actualDate);
			assertEquals(expectedCity, actualCity);
			assertEquals(expectedStreet,actualStreet);
			assertEquals(expectedPostalCode, actualPostalCode);
			
			//Test update database
			Person.setFirstName("Jeb");
			PersonDAL.updatePerson(Person);
			actualFirstName = PersonDAL.getPerson(Person.getPersonID()).getFirstName();
			expectedFirstName = "Jeb";
			
			assertEquals(expectedFirstName, actualFirstName);
			
			//Test deleting database
			PersonDAL.deletePerson(Person.getPersonID());
			
			expectedFirstName = null;
			expectedLastName = null;
			expectedDate = null;
			expectedCity = null;
			expectedStreet = null;
			expectedPostalCode = null;
			
			
			actualFirstName = PersonDAL.getPerson(Person.getPersonID()).getFirstName();
			actualLastName = PersonDAL.getPerson(Person.getPersonID()).getLastName();
			actualDate = PersonDAL.getPerson(Person.getPersonID()).getBirthday();
			actualCity = PersonDAL.getPerson(Person.getPersonID()).getCity();
			actualStreet = PersonDAL.getPerson(Person.getPersonID()).getStreet();
			actualPostalCode = PersonDAL.getPerson(Person.getPersonID()).getPostalCode();
			
			assertEquals(expectedFirstName, actualFirstName);
			assertEquals(expectedLastName, actualLastName);
			assertEquals(expectedDate, actualDate);
			assertEquals(expectedCity, actualCity);
			assertEquals(expectedStreet,actualStreet);
			assertEquals(expectedPostalCode, actualPostalCode);
			
			

		}  catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
