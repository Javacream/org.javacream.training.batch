package org.javacream.training.batch;

import org.junit.Assert;
import org.junit.Test;

public class PeopleManagerTest {

	@Test public void testPeopleManagerDuplicates() {
		PeopleManager peopleManager = new PeopleManager();
		peopleManager.create("Sawitzki", "Rainer", 183);
		peopleManager.create("Sawitzki", "Klaus", 181);
		Assert.assertEquals(2, peopleManager.findAll().size());
		peopleManager.create("Sawitzki", "Rainer", 183);
		Assert.assertEquals(2, peopleManager.findAll().size());
	}
	
	@Test public void testDataStructure() {
		PeopleManager peopleManager = new PeopleManager();
		Person p = peopleManager.create("Sawitzki", "Rainer", 183);
		//System.out.println(p1);
		Assert.assertEquals("Sawitzki", p.getLastname());
		Assert.assertEquals("Rainer", p.getFirstname());
		Assert.assertTrue(183 == p.getHeight());
		
	}

	@Test public void testPeopleManagerFindByLastname() {
		PeopleManager peopleManager = new PeopleManager();
		peopleManager.create("Sawitzki", "Rainer", 183);
		peopleManager.create("Mustermann", "Hans", 181);
		peopleManager.create("Sawitzki", "Klaus", 181);
		Assert.assertEquals(2, peopleManager.findByLastnameFunctional("Sawitzki").size());
		Assert.assertEquals(1, peopleManager.findByLastname("Mustermann").size());
	}

	
}
