package com.cafe24.mysite5.domain;

import javax.persistence.*;

@Entity
public class Role{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long no;

	@Column(nullable = false, unique = true)
	private String name;

	public Long getNo(){
		return no;
	}

	public void setNo( Long no ){
		this.no = no;
	}

	public String getName(){
		return name;
	}

	public void setName( String name ){
		this.name = name;
	}

	@Override
	public String toString(){
		return "Role{" +
				"no=" + no +
				", name='" + name + '\'' +
				'}';
	}
}
