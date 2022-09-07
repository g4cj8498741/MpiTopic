package com.spring.bean;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContactFormVO {

	private String name;
	
	private Integer gender;
	
	private String ards;
	
	private String tel;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bir;
	
	private String email;
	
	private String issue;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContactFormVO() {
	
	}

	public ContactFormVO(String name, Integer gender, String ards, String tel, Date bir, String email, String issue) {
		super();
		this.name = name;
		this.gender = gender;
		this.ards = ards;
		this.tel = tel;
		this.bir = bir;
		this.email = email;
		this.issue = issue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getArds() {
		return ards;
	}

	public void setArds(String ards) {
		this.ards = ards;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getBir() {
		return bir;
	}

	public void setBir(Date bir) {
		this.bir = bir;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		return "ContactFormVO [name=" + name + ", gender=" + gender + ", ards=" + ards + ", tel=" + tel + ", bir=" + bir
				+ ", email=" + email + ", issue=" + issue + "]";
	}

	


	
	
}
