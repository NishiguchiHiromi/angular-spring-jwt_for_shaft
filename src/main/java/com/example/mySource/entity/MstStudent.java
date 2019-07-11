package com.example.mySource.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mst_student database table.
 * 
 */
@Entity
@Table(name="mst_student")
@NamedQuery(name="MstStudent.findAll", query="SELECT m FROM MstStudent m")
public class MstStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="department_no")
	private int departmentNo;

	@Column(name="first_name", length=255)
	private String firstName;

	@Id
	@Column(nullable=false)
	private int id;

	@Column(name="last_name", length=255)
	private String lastName;

	@Column(nullable=false, length=1000)
	private String mailAddress;

	@Column(nullable=false, length=1000)
	private String password;

	public MstStudent() {
	}

	public int getDepartmentNo() {
		return this.departmentNo;
	}

	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}