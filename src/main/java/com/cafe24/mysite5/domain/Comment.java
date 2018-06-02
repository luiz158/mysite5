package com.cafe24.mysite5.domain;

import com.cafe24.mysite5.enumeration.Deletion;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long no;

	@Column( name = "content", nullable = false )
	private String content;

	@Column( name = "reg_time", nullable = false )
	private Date regTime;

	@Column( name = "g_no", nullable = false )
	private Long gNo;

	@Column( name = "o_no", nullable = false )
	private Long oNo;

	@Column( name = "depth", nullable = false )
	private Long depth;

	@Column( name = "deletion", nullable = false, columnDefinition = "ENUM('Y', 'N')" )
	private Deletion deletion;

	@ManyToOne
	@JoinColumn( name = "board_no" )
	private Board board;

	public Comment(){
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment( String content, Date regTime, Long gNo, Long oNo, Long depth, Deletion deletion ){
		this.content = content;
		this.regTime = regTime;
		this.gNo = gNo;
		this.oNo = oNo;
		this.depth = depth;
		this.deletion = deletion;
	}

	public Long getNo(){
		return no;
	}

	public void setNo( Long no ){
		this.no = no;
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

	public Board getBoard(){
		return board;
	}

	public void setBoard( Board board ){
		this.board = board;
	}

	@Override
	public String toString(){
		return "Comment{" +
				"no=" + no +
				", content='" + content + '\'' +
				", regTime=" + regTime +
				", gNo=" + gNo +
				", oNo=" + oNo +
				", depth=" + depth +
				", deletion=" + deletion +
				'}';
	}
}
