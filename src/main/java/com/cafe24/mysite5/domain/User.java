package com.cafe24.mysite5.domain;

import com.cafe24.mysite5.enumeration.Enabled;
import com.cafe24.mysite5.enumeration.Gender;
import com.cafe24.mysite5.enumeration.Withdraw;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
public class User{


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

//	@Enumerated( value = EnumType.STRING )
//	@Column( name = "role", nullable = false, columnDefinition = "ENUM('ADMIN', 'USER')" )
//	private Role role;
	/* 한 사용자가 여러 역할, 한 역할에 여러 사용자 맵핑 가능 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
		joinColumns =
		@JoinColumn(name = "user_id", referencedColumnName = "no"),
		inverseJoinColumns =
		@JoinColumn(name = "role_id", referencedColumnName = "no"))
	private Set<Role> roles;

	@Enumerated( value = EnumType.STRING )
	@Column( name = "enabled", nullable = false, columnDefinition = "ENUM('True', 'False')" )
	private Enabled enabled;

	@OneToMany( mappedBy = "user" )
	private List< Board > boards = new ArrayList<>();


	public User(){
	}

	public User( String name, String email, String password, Gender gender, Date joinDate, Role role, Withdraw withdraw ){
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.joinDate = joinDate;
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

	public Set< Role > getRoles(){
		return roles;
	}

	public void setRoles( Set< Role > roles ){
		this.roles = roles;
	}

	public Enabled getEnabled(){
		return enabled;
	}

	public void setEnabled( Enabled enabled ){
		this.enabled = enabled;
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
				'}';
	}
}
