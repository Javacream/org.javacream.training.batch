package org.javacream.training.batch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PeopleManager {
	private Set<Person> people;

	{
		people = new HashSet<>();
	}

	public Person create(String lastname, String firstname, Integer height) {
		Person p = new Person(lastname, firstname, height);
		people.add(p);
		return p;
	}

	public List<Person> findAll() {
		return new ArrayList<>(people);
	}

	public List<Person> findByLastname(String lastname) {
		ArrayList<Person> result = new ArrayList<>();
		for (Person p : people) {
			if (lastname.equals(p.getLastname())) {
				result.add(p);
			}
		}
		return result;
	}

	public List<Person> findByLastnameFunctional(String lastname){
		return people.stream().filter((Person p) -> {return p.getLastname().equals(lastname);}).collect(Collectors.toList());
	}

}
