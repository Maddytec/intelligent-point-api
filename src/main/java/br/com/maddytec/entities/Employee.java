package br.com.maddytec.entities;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.maddytec.enums.ProfileEnum;
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
@Table(name = "employee")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 6755691086224180372L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String email;
	
	@NonNull
	private String password;
	
	@NonNull
	@Column(name = "number_document_employee")
	private String numberDocumentEmployee;
	
	@NonNull
	@Column(name = "value_hour")
	private BigDecimal valueHour;
	
	@NonNull
	@Column(name = "count_hours_work_day")
	private Float countHoursWorkDay;
	
	@Column(name = "count_hours_lunch")
	private Float countHoursLunch;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "profile")
	private ProfileEnum profileEnum;
	
	@NonNull
	@Column(name = "date_create")
	private Date dateCreate;
	
	@NonNull
	@Column(name = "date_update")
	private Date dateUpdate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Employer employer;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Register> registers;
	
	@Transient
	public Optional<BigDecimal> getValueHour(){
		return Optional.ofNullable(valueHour);
	}
	
	@Transient
	public Optional<Float> getCountHoursWorkDayOpt(){
		return Optional.ofNullable(countHoursWorkDay);
	}
	
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
