package com.cafe24.mysite5.domain;

import com.cafe24.mysite5.enumeration.Gender;
import com.cafe24.mysite5.enumeration.Role;
import com.cafe24.mysite5.enumeration.Withdraw;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserBak{


	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long no;

	@NotEmpty
	@Length( min = 2, max = 10 )
	@Column( name = "name", nullable = false )
	private String name;

	@NotEmpty
	@Email
	@Pattern( regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,6}$" )
	@Column( name = "email", nullable = false, length = 120 )
	private String email;

	@NotEmpty
	@Column( name = "password", nullable = false, length = 128 )
	private String password;

	@Enumerated( value = EnumType.STRING )
	@Column( name = "gender", nullable = false, columnDefinition = "ENUM('FEMALE', 'MALE')" )
	private Gender gender;

	@Column( name = "joinDate", nullable = false )
	private Date joinDate;

	@Enumerated( value = EnumType.STRING )
	@Column( name = "role", nullable = false, columnDefinition = "ENUM('ADMIN', 'USER')" )
	private Role role;


	@Enumerated( value = EnumType.STRING )
	@Column( name = "withdraw", nullable = false, columnDefinition = "ENUM('Y', 'N')" )
	private Withdraw withdraw;

	@OneToMany( mappedBy = "user" )
	private List< Board > boards = new ArrayList<>();


	public UserBak(){
	}

	public UserBak( String name, String email, String password, Gender gender, Date joinDate, Role role, Withdraw withdraw ){
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.joinDate = joinDate;
		this.role = role;
		this.withdraw = withdraw;
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

	public String getEmail(){
		return email;
	}

	public void setEmail( String email ){
		this.email = email;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword( String password ){
		this.password = password;
	}

	public Gender getGender(){
		return gender;
	}

	public void setGender( Gender gender ){
		this.gender = gender;
	}

	public Date getJoinDate(){
		return joinDate;
	}

	public void setJoinDate( Date joinDate ){
		this.joinDate = joinDate;
	}

	public Role getRole(){
		return role;
	}

	public void setRole( Role role ){
		this.role = role;
	}

	public Withdraw getWithdraw(){
		return withdraw;
	}

	public void setWithdraw( Withdraw withdraw ){
		this.withdraw = withdraw;
	}

	public List< Board > getBoards(){
		return boards;
	}

	public void setBoards( List< Board > boards ){
		this.boards = boards;
	}

	@Override
	public String toString(){
		return "User{" +
				"no=" + no +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", gender=" + gender +
				", joinDate=" + joinDate +
				", role=" + role +
				", withdraw=" + withdraw +
				'}';
	}
}
