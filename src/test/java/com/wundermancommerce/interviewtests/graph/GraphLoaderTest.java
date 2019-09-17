package com.wundermancommerce.interviewtests.graph;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.service.GraphLoader;
import com.wundermancommerce.interviewtests.graph.service.GraphLoaderImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class GraphLoaderTest {


	private final GraphLoader graphLoader = new GraphLoaderImpl();

	@Before
	public void setup() {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String PEOPLE_CSV_NAME = Objects.requireNonNull(classLoader.getResource("people.csv")).getFile();
		String RELATIONSHIP_CSV_NAME = Objects.requireNonNull(classLoader.getResource("relationships.csv")).getFile();
		graphLoader.readData(PEOPLE_CSV_NAME, RELATIONSHIP_CSV_NAME);
	}

	@Test
	public void testRelationshipMatches() {

		Person bob = new Person("Bob", "bob@bob.com", 31);
		int actual = graphLoader.countRelationship(bob);
		assertEquals("Bob's relationship count doesnt match", 4, actual);

		Person jenny = new Person("Jenny", "jenny@toys.com", 52);
		actual = graphLoader.countRelationship(jenny);
		assertEquals("Jenny's relationship count doesnt match", 3, actual);

		Person nigel = new Person("Nigel", "nigel@marketing.com", 40);
		actual = graphLoader.countRelationship(nigel);
		assertEquals("Nigel's relationship count doesnt match", 2, actual);

		Person alan = new Person("Alan", "alan@lonely.org", 23);
		actual = graphLoader.countRelationship(alan);
		assertEquals("Alan's relationship count doesnt match", 0, actual);
	}

	@Test
	public void testExtendedFamilyRelationship() {
		Person jenny = new Person("Jenny", "jenny@toys.com", 52);
		int actual = graphLoader.countExtendedFamily(jenny);
		assertEquals("Jenny's relationship count doesnt match", 4, actual);

		Person bob = new Person("Bob", "bob@bob.com", 31);
		actual = graphLoader.countExtendedFamily(bob);
		assertEquals("Bob's relationship count doesnt match", 4, actual);

	}

	@Test
	public void testPeopleLoaded() {
		assertEquals(graphLoader.countNumberOfPeople(), 12);

	}

}
