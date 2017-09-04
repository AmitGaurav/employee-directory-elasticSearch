package com.cisco.poc.model;

import java.util.Date;
import java.util.List;

public class Member {
	
	private String name;
	private String infyId;
	private String ciscoId;
	private Long infyEmpNumber;
	private Long ciscoEmpNumber;
	private String infyDesignation;
	private String ciscoDesignation;
	private String moduleOwner;
	private String gender;
	private String deploymentLocation;
	private String location;
	private Short age;
	private String maritalStatus;
	private Date dob;
	private Date projectJoiningDate;
	private SkillSet skillSet; 
	private List<String> hobbies;
	
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getProjectJoiningDate() {
		return projectJoiningDate;
	}
	public void setProjectJoiningDate(Date projectJoiningDate) {
		this.projectJoiningDate = projectJoiningDate;
	}
	public SkillSet getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfyId() {
		return infyId;
	}
	public void setInfyId(String infyId) {
		this.infyId = infyId;
	}
	public String getCiscoId() {
		return ciscoId;
	}
	public void setCiscoId(String ciscoId) {
		this.ciscoId = ciscoId;
	}
	public Long getInfyEmpNumber() {
		return infyEmpNumber;
	}
	public void setInfyEmpNumber(Long infyEmpNumber) {
		this.infyEmpNumber = infyEmpNumber;
	}
	public Long getCiscoEmpNumber() {
		return ciscoEmpNumber;
	}
	public void setCiscoEmpNumber(Long ciscoEmpNumber) {
		this.ciscoEmpNumber = ciscoEmpNumber;
	}
	public String getInfyDesignation() {
		return infyDesignation;
	}
	public void setInfyDesignation(String infyDesignation) {
		this.infyDesignation = infyDesignation;
	}
	public String getCiscoDesignation() {
		return ciscoDesignation;
	}
	public void setCiscoDesignation(String ciscoDesignation) {
		this.ciscoDesignation = ciscoDesignation;
	}
	public String getModuleOwner() {
		return moduleOwner;
	}
	public void setModuleOwner(String moduleOwner) {
		this.moduleOwner = moduleOwner;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDeploymentLocation() {
		return deploymentLocation;
	}
	public void setDeploymentLocation(String deploymentLocation) {
		this.deploymentLocation = deploymentLocation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", infyId=" + infyId + ", ciscoId="
				+ ciscoId + ", infyEmpNumber=" + infyEmpNumber
				+ ", ciscoEmpNumber=" + ciscoEmpNumber + ", infyDesignation="
				+ infyDesignation + ", ciscoDesignation=" + ciscoDesignation
				+ ", moduleOwner=" + moduleOwner + ", gender=" + gender
				+ ", deploymentLocation=" + deploymentLocation + ", location="
				+ location + ", age=" + age + ", maritalStatus="
				+ maritalStatus + ", dob=" + dob + ", projectJoiningDate="
				+ projectJoiningDate + ", skillSet=" + skillSet + ", hobbies="
				+ hobbies + "]";
	}
	
	
}
