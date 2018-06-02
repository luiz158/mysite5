package com.cafe24.mysite5.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "guestbook" )
public class Guestbook{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long no;

	@Column( name = "name", nullable = false, length = 100 )
	private String name;

	@Column( name = "message", nullable = false )
	@Lob //CLOB
	private String message;

	@Column( name = "password", nullable = false, length = 64 )
	private String password;

	@Column( name = "reg_date", nullable = false )
	@Temporal( value = TemporalType.TIMESTAMP)
	private Date regDate;

	public Guestbook(){
	}

	public Guestbook( String name, String message, String password, Date regDate ){
		this.name = name;
		this.message = message;
		this.password = password;
		this.regDate = regDate;
	}

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

	public String getMessage(){
		return message;
	}

	public void setMessage( String message ){
		this.message = message;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword( String password ){
		this.password = password;
	}

	public Date getRegDate(){
		return regDate;
	}

	public void setRegDate( Date regDate ){
		this.regDate = regDate;
	}
}
