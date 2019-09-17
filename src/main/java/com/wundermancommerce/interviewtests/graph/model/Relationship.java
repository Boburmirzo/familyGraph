package com.wundermancommerce.interviewtests.graph.model;

/**
 * Relationship keeps information about each entry from relationships csv file
 *
 * @author Bobur Umurzokov
 * @version 1.0
 * @since 2019-09-17
 */
public class Relationship {
	/**
	 * personFrom a person name who has relationship
	 */
	private String personFrom;
	/**
	 * personTo a person name who is related to personFrom
	 */
	private String personTo;
	/**
	 * relatedBy a String type of relationShip
	 */
	private String relatedBy;

	public Relationship(String personFrom,
	                    String personTo,
	                    String relatedBy) {
		this.personFrom = personFrom;
		this.personTo = personTo;
		this.relatedBy = relatedBy;
	}

	public String getPersonFrom() {
		return personFrom;
	}

	public void setPersonFrom(String personFrom) {
		this.personFrom = personFrom;
	}

	public String getPersonTo() {
		return personTo;
	}

	public void setPersonTo(String personTo) {
		this.personTo = personTo;
	}

	public String getRelatedBy() {
		return relatedBy;
	}

	public void setRelatedBy(String relatedBy) {
		this.relatedBy = relatedBy;
	}
}


