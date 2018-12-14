package br.com.maddytec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "employer")
public class Employer implements Serializable {

	private static final long serialVersionUID = -6138091138317234397L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.PROTECTED) 
	private Long id;

	@NonNull
	@Column(name = "employer_name")
	private String employerName;

	@NonNull
	@Column(name = "number_document_employer")
	private String numberDocumentEmployer;

	@NonNull
	@Column(name = "date_update")
	private Date dateUpdate;
	
	@NonNull
	@Column(name = "date_create")
	private Date dateCreate;

	@OneToMany(mappedBy = "employer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Employee> employees;

	@PreUpdate
	public void preUpdate() {
		dateUpdate = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dateCreate = atual;
		dateUpdate = atual;
	}

}