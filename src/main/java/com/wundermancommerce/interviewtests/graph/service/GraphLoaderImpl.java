package com.wundermancommerce.interviewtests.graph.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relationship;
import com.wundermancommerce.interviewtests.graph.util.CsvImporter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * Friends and family loader and executor
 *
 * @author Bobur Umurzokov
 * @version 1.0
 * @since 2019-09-17
 */
public class GraphLoaderImpl implements GraphLoader {


	private static final Function<Person, String> email = Person::getEmail;
	private static final String FAMILY = "FAMILY";
	private static final String FRIEND = "FRIEND";
	private List<Person> personList = Lists.newArrayList();
	private List<Relationship> relationshipList = Lists.newArrayList();
	private final Map<Person, LinkedList<Person>> familyRelationship = Maps.newHashMap();
	private final Map<Person, LinkedList<Person>> friendsRelationship = Maps.newHashMap();

	@Override
	public int countRelationship(Person person) {
		return familyRelationship.getOrDefault(person, Lists.newLinkedList()).size()
				+ friendsRelationship.getOrDefault(person, Lists.newLinkedList()).size();
	}

	@Override
	public int countExtendedFamily(Person person) {
		Map<Person, Boolean> visited = Maps.newHashMap();
		search(person, visited);
		return visited.size();
	}

	@Override
	public int countNumberOfPeople() {
		return personList.size();
	}

	@Override
	public void readData(String peopleCsvName,
	                     String relationshipCsvName) {
		personList = CsvImporter.importToListOfPerson(peopleCsvName);
		relationshipList = CsvImporter.importToListOfRelationship(relationshipCsvName);

		Map<String, Person> mapOfPerson =
				personList.stream().collect(Collectors.toMap(email, Function.identity()));
		// construct undirected graph for friends or family
		relationshipList.forEach(
				r -> addRelationShipData(mapOfPerson.get(r.getPersonFrom()),
						mapOfPerson.get(r.getPersonTo()), r.getRelatedBy()));
	}


	private void addRelationShipData(Person source,
	                                 Person destination,
	                                 String relatedBy) {
		if (FAMILY.equalsIgnoreCase(relatedBy)) {
			familyRelationship.putIfAbsent(source, Lists.newLinkedList());
			familyRelationship.putIfAbsent(destination, Lists.newLinkedList());
			familyRelationship.get(source).addFirst(destination);
			familyRelationship.get(destination).addFirst(source);

		}
		if (FRIEND.equalsIgnoreCase(relatedBy)) {
			friendsRelationship.putIfAbsent(source, Lists.newLinkedList());
			friendsRelationship.putIfAbsent(destination, Lists.newLinkedList());
			friendsRelationship.get(source).addFirst(destination);
			friendsRelationship.get(destination).addFirst(source);
		}

	}


	// Perform depth first search
	private void search(Person person, Map<Person, Boolean> visited) {
		visited.put(person, true);
		LinkedList<Person> edges = familyRelationship.getOrDefault(person, Lists.newLinkedList());

		for (Person edge : edges) {
			if (isNotVisited(visited, edge)) {
				search(edge, visited);
			}
		}
	}

	private boolean isNotVisited(Map<Person, Boolean> visited, Person person) {
		return isNull(visited.get(person)) || !visited.get(person);
	}

}
