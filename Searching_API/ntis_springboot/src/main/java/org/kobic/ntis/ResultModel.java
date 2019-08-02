package org.kobic.ntis;

import java.io.Serializable;

public class ResultModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public String projectNumber; // required
	public String projectTitle_kor; // required
	public String ProjectTitle_eng;

	public String manager; // required
	public String managerID;
	public String managerResearcherNo;

	public String researchers_name;
	public String researchers_humanID;
	public String researchers_manCount;
	public String researchers_womanCount;
	public String researchers_prStRegistrationNumber;

	public String keyWord_kor;
	public String keyWord_eng;

	public String orderAgency; // required
	public String orderAgency_code;

	public String leadAgency; // required

	public String researchAgency_name;
	public String researchAgency_code;

	public String budgetProject_name;
	public String budgetProject_code;

	public String businessName;

	public String bigProjectTitle;

	public String manageAgency; // required
	public String manageAgency_code;

	public String ministry; // required
	public String ministry_code;

	public String projectYear; // required
	public String secretProject;
	public String startDate; // required
	public String endDate; // required
	public String totalStart;
	public String totalEnd;

	public String organizationPNumber; // required

	public String ministryScienceClass_large;
	public String ministryScienceClass_medium;
	public String ministryScienceClass_small;

	public String getResearchers_name() {
		return researchers_name;
	}

	public void setResearchers_name(String researchers_name) {
		this.researchers_name = researchers_name;
	}

	public String getResearchers_humanID() {
		return researchers_humanID;
	}

	public void setResearchers_humanID(String researchers_humanID) {
		this.researchers_humanID = researchers_humanID;
	}

	public String getResearchers_manCount() {
		return researchers_manCount;
	}

	public void setResearchers_manCount(String researchers_manCount) {
		this.researchers_manCount = researchers_manCount;
	}

	public String getMinistry_code() {
		return ministry_code;
	}

	public void setMinistry_code(String ministry_code) {
		this.ministry_code = ministry_code;
	}

	public String getSecretProject() {
		return secretProject;
	}

	public void setSecretProject(String secretProject) {
		this.secretProject = secretProject;
	}

	public String getTotalStart() {
		return totalStart;
	}

	public void setTotalStart(String totalStart) {
		this.totalStart = totalStart;
	}

	public String getTotalEnd() {
		return totalEnd;
	}

	public void setTotalEnd(String totalEnd) {
		this.totalEnd = totalEnd;
	}

	public String getMinistryScienceClass_large() {
		return ministryScienceClass_large;
	}

	public void setMinistryScienceClass_large(String ministryScienceClass_large) {
		this.ministryScienceClass_large = ministryScienceClass_large;
	}

	public String getMinistryScienceClass_medium() {
		return ministryScienceClass_medium;
	}

	public void setMinistryScienceClass_medium(String ministryScienceClass_medium) {
		this.ministryScienceClass_medium = ministryScienceClass_medium;
	}

	public String getMinistryScienceClass_small() {
		return ministryScienceClass_small;
	}

	public void setMinistryScienceClass_small(String ministryScienceClass_small) {
		this.ministryScienceClass_small = ministryScienceClass_small;
	}

	public String getResearchers_prStRegistrationNumber() {
		return researchers_prStRegistrationNumber;
	}

	public void setResearchers_prStRegistrationNumber(String researchers_prStRegistrationNumber) {
		this.researchers_prStRegistrationNumber = researchers_prStRegistrationNumber;
	}

	public String getKeyWord_kor() {
		return keyWord_kor;
	}

	public void setKeyWord_kor(String keyWord_kor) {
		this.keyWord_kor = keyWord_kor;
	}

	public String getKeyWord_eng() {
		return keyWord_eng;
	}

	public void setKeyWord_eng(String keyWord_eng) {
		this.keyWord_eng = keyWord_eng;
	}

	public String getOrderAgency_code() {
		return orderAgency_code;
	}

	public void setOrderAgency_code(String orderAgency_code) {
		this.orderAgency_code = orderAgency_code;
	}

	public String getResearchAgency_name() {
		return researchAgency_name;
	}

	public void setResearchAgency_name(String researchAgency_name) {
		this.researchAgency_name = researchAgency_name;
	}

	public String getResearchAgency_code() {
		return researchAgency_code;
	}

	public void setResearchAgency_code(String researchAgency_code) {
		this.researchAgency_code = researchAgency_code;
	}

	public String getBudgetProject_name() {
		return budgetProject_name;
	}

	public void setBudgetProject_name(String budgetProject_name) {
		this.budgetProject_name = budgetProject_name;
	}

	public String getBudgetProject_code() {
		return budgetProject_code;
	}

	public void setBudgetProject_code(String budgetProject_code) {
		this.budgetProject_code = budgetProject_code;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBigProjectTitle() {
		return bigProjectTitle;
	}

	public void setBigProjectTitle(String bioProjectTitle) {
		this.bigProjectTitle = bioProjectTitle;
	}

	public String getManageAgency_code() {
		return manageAgency_code;
	}

	public void setManageAgency_code(String manageAgency_code) {
		this.manageAgency_code = manageAgency_code;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectTitle_kor() {
		return projectTitle_kor;
	}

	public void setProjectTitle_kor(String projectTitle) {
		this.projectTitle_kor = projectTitle;
	}

	public String getProjectTitle_eng() {
		return this.ProjectTitle_eng;
	}

	public void setProjectTitle_eng(String projectTitle) {
		this.ProjectTitle_eng = projectTitle;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerID() {
		return this.managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public String getManagerResearcherNo() {
		return this.managerResearcherNo;
	}

	public void setManagerResearcherNo(String managerResearcherNo) {
		this.managerResearcherNo = managerResearcherNo;
	}

	public String getResearchersName() {
		return this.researchers_name;
	}

	public void setResearchersName(String researchersName) {
		this.researchers_name = researchersName;
	}

	public String getResearchersHumanID() {
		return this.researchers_humanID;
	}

	public void setResearchersHumanID(String researchersHumanID) {
		this.researchers_humanID = researchersHumanID;
	}

	public String getResearchersManCount() {
		return this.researchers_manCount;
	}

	public void setResearchersManCount(String researchersManCount) {
		this.researchers_manCount = researchersManCount;
	}

	public String getResearchers_womanCount() {
		return researchers_womanCount;
	}

	public void setResearchers_womanCount(String researchers_womanCount) {
		this.researchers_womanCount = researchers_womanCount;
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
