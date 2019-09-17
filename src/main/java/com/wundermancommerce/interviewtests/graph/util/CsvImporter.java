package com.wundermancommerce.interviewtests.graph.util;

import com.google.common.collect.Lists;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relationship;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvImporter {

	private static final String COMMA_SEPARATOR = ",";
	private static final Function<String, Person> MAP_TO_PERSON = (line) -> {
		String[] p = line.split(COMMA_SEPARATOR);
		return new Person(p[0], p[1], Integer.parseInt(p[2]));
	};
	private static final Function<String, Relationship> MAP_TO_RELATION_SHIP = (line) -> {
		String[] p = line.split(COMMA_SEPARATOR);
		return new Relationship(p[0], p[2], p[1]);
	};

//	/**
//	 * Load a given list of members and their relationship from csv file.
//	 *
//	 * @param peopleCsvName csv file name corresponding to list of people
//	 * @param relationshipCsvName csv file name corresponding to relationship data
//	 */

	private static void validateFileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("CSV file names shouldn't be empty");
		}
	}

	public static List<Person> importToListOfPerson(String peopleCsvName){
		validateFileName(peopleCsvName);
		return importCsv(peopleCsvName, MAP_TO_PERSON);
	}

	public static List<Relationship> importToListOfRelationship(String relationshipCsvName){
		validateFileName(relationshipCsvName);
		return importCsv(relationshipCsvName, MAP_TO_RELATION_SHIP);
	}

	// Import the csv file into corresponding list of type
	private static <K> List<K> importCsv(String inputFilePath,
	                              Function<String, K> mappingTo) {
		List<K> inputList = Lists.newArrayList();
		try {

			File inputF = new File(inputFilePath);
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

			inputList = br.lines()
					.filter(line -> !line.isEmpty())  // ignore empty lines
					.map(mappingTo).collect(Collectors.toList());
			br.close();
		} catch (IOException e) {
			System.out.println("Error loading file : cause " + e.getCause());

		}
		return inputList;
	}
}
