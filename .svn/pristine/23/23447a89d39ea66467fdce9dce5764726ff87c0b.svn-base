package com.dms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.dms.service.FolderService;

@Entity
@Table(name = "PERMISSION")
public class Permission {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="permission_seq")
	@SequenceGenerator(name="permission_seq", sequenceName="permission_seq", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TYPE")
	private Integer type;
	
	@Column(name = "USERID")
	private Long userId;
	
	@Column(name = "VALUE")
	private Long value;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Transient
	private String name;
	
	@Transient
	private String folderPath;
	
	@Transient
	private Integer isParent;
	
	@Transient
	private Boolean assignAll;
	
	public Long getId() {
		return id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAssignAll() {
		return assignAll;
	}

	public void setAssignAll(Boolean assignAll) {
		this.assignAll = assignAll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	
	
}
