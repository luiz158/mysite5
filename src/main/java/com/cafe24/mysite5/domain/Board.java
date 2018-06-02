package com.cafe24.mysite5.domain;

import com.cafe24.mysite5.enumeration.Deletion;
import com.cafe24.mysite5.enumeration.Modify;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Board{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long no;

	@Column( name = "title", nullable = false )
	private String title;

	@Column( name = "content", nullable = false )
	private String content;

	@Column( name = "reg_time", nullable = false )
	private Date regTime;

	@Column( name = "modify", nullable = false, columnDefinition = "ENUM('Y', 'N')" )
	private Modify modify;

	@Column( name = "hits", nullable = false )
	private Long hits;

	@Column( name = "g_no", nullable = false )
	private Long gNo;

	@Column( name = "o_no", nullable = true )
	private Long oNo;

	@Column( name = "depth", nullable = true )
	private Long depth;

	@Column( name = "deletion", nullable = false, columnDefinition = "ENUM('Y', 'N')" )
	private Deletion deletion;

	@ManyToOne
	@JoinColumn( name = "user_no" )
	private User user;

	@OneToMany( mappedBy = "board" )
	private List< Comment > comments;

	public Board(){
	}

	public Board( String title, String content, Date regTime, Modify modify, Long hits, Long gNo, Long oNo, Long depth, Deletion deletion ){
		this.title = title;
		this.content = content;
		this.regTime = regTime;
		this.modify = modify;
		this.hits = hits;
		this.gNo = gNo;
		this.oNo = oNo;
		this.depth = depth;
		this.deletion = deletion;
	}

	public long getNo(){
		return no;
	}

	public void setNo( long no ){
		this.no = no;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle( String title ){
		this.title = title;
	}

	public String getContent(){
		return content;
	}

	public void setContent( String content ){
		this.content = content;
	}

	public Date getRegTime(){
		return regTime;
	}

	public void setRegTime( Date regTime ){
		this.regTime = regTime;
	}

	public Modify getModify(){
		return modify;
	}

	public void setModify( Modify modify ){
		this.modify = modify;
	}

	public Long getHits(){
		return hits;
	}

	public void setHits( Long hits ){
		this.hits = hits;
	}

	public Long getgNo(){
		return gNo;
	}

	public void setgNo( Long gNo ){
		this.gNo = gNo;
	}

	public Long getoNo(){
		return oNo;
	}

	public void setoNo( Long oNo ){
		this.oNo = oNo;
	}

	public Long getDepth(){
		return depth;
	}

	public void setDepth( Long depth ){
		this.depth = depth;
	}

	public Deletion getDeletion(){
		return deletion;
	}

	public void setDeletion( Deletion deletion ){
		this.deletion = deletion;
	}

	public User getUser(){
		return user;
	}

	public void setUser( User user ){
		if ( this.user.getBoards() != null ) {
			this.user.getBoards().remove( this );
		}
		this.user = user;
		if ( this.user.getBoards() != null ) {
			this.user.getBoards().add( this );
		}
	}

	@Override
	public String toString(){
		return "Board{" +
				"no=" + no +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", regTime=" + regTime +
				", modify=" + modify +
				", hits=" + hits +
				", gNo=" + gNo +
				", oNo=" + oNo +
				", depth=" + depth +
				", deletion=" + deletion +
				'}';
	}
}
