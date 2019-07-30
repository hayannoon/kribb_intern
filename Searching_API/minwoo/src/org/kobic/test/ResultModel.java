package org.kobic.test;

import java.io.Serializable;

public class ResultModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String projectNumber; // required
	public String projectTitle; // required
	public String manager; // required
	public String orderAgency; // required
	public String leadAgency; // required
	public String manageAgency; // required
	public String ministry; // required
	public String projectYear; // required
	public String startDate; // required
	public String endDate; // required
	public String organizationPNumber; // required

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOrderAgency() {
		return orderAgency;
	}

	public void setOrderAgency(String orderAgency) {
		this.orderAgency = orderAgency;
	}

	public String getLeadAgency() {
		return leadAgency;
	}

	public void setLeadAgency(String leadAgency) {
		this.leadAgency = leadAgency;
	}

	public String getManageAgency() {
		return manageAgency;
	}

	public void setManageAgency(String manageAgency) {
		this.manageAgency = manageAgency;
	}

	public String getMinistry() {
		return ministry;
	}

	public void setMinistry(String ministry) {
		this.ministry = ministry;
	}

	public String getProjectYear() {
		return projectYear;
	}

	public void setProjectYear(String projectYear) {
		this.projectYear = projectYear;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrganizationPNumber() {
		return organizationPNumber;
	}

	public void setOrganizationPNumber(String organizationPNumber) {
		this.organizationPNumber = organizationPNumber;
	}

}
