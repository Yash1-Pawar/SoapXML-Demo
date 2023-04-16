package com.soap.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import com.soap.service.CDataAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@XmlRootElement(name = "User")
public class User {

	private int id;

	private String name;

	private String value;

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlValue
//	@XmlCDATA
	@XmlJavaTypeAdapter(CDataAdapter.class)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
