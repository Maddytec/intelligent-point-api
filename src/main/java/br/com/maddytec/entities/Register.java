package br.com.maddytec.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.maddytec.enums.TypeEnum;
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
@Table(name = "register")
public class Register implements Serializable {
	
	private static final long serialVersionUID = 235309241036738386L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.PROTECTED)
	private Long id;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private String description;
	
	private String location;
	
	@NonNull
	@Column(name = "date_create")
	private Date dateCreate;
	
	@NonNull
	@Column(name = "date_update")
	private Date dateUpdate;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;
	
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
