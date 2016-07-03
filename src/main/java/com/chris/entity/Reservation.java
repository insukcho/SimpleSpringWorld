package com.chris.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.chris.helper.UserConverter;

//@Entity
public class Reservation {

	@Id
	private Long regNo;
	private String confirmationNo;
	private Date fromDate;
	private Date toDate;
	private int adult;
	private int children;
	private int infant;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="regNo")
	@Convert(converter=UserConverter.class)
	private List<User> passenger;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="regNo")
	@Convert(converter=UserConverter.class)
	private User staff;
	
	public Reservation() {}
	
	
}