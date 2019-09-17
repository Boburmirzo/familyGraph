package com.wundermancommerce.interviewtests.graph.service;

import com.wundermancommerce.interviewtests.graph.model.Person;

/**
 * Friends and family graph loader.
 */
public interface GraphLoader {

    /**
     * Return a related number people for this <p>Person<p/>.
     *
     * @param person related person to be returned for
     * @return the number of related people friends or family
     */
    int countRelationship(Person person);

    /**
     * Return the number of extended family members for a given person.
     *
     * @param person given person for which extended family member should be returned
     * @return the number of extended family members for a given person.
     */
    int countExtendedFamily(Person person);

    /**
     * Return the number of members loaded in memory.
     *
     * @return the number of members
     */
    int countNumberOfPeople();

    /**
     * Read CSV files and import data into objects
     * @param peopleCsvName person list file name
     * @param relationshipCsvName relationship list file name
     */
    void readData(String peopleCsvName,
                  String relationshipCsvName);

}
