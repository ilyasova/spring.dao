package com.study.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "phrases")
public class Phrases implements Serializable {

	private static final long serialVersionUID = 4579456575441152660L;

	public Phrases() {
		super();
	}

	public Phrases(LANGUAGE language, Integer order) {
		super();
		this.language = language;
		this.order = order;
	}

	public Phrases(Integer id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue(generator = "phrasesId")
	@SequenceGenerator(name = "phrasesId", sequenceName = "phrases_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	public enum LANGUAGE {
		ENGLISH, SPANISH;
	}

	@Column(name = "language")
	@Enumerated(EnumType.ORDINAL)
	private LANGUAGE language;

	@Column(name = "text_from")
	private String textFrom;

	@Column(name = "text_to")
	private String textTo;

	@Column(name = "order_by_language")
	private Integer order;

	@ManyToMany(mappedBy = "phrases")
	private Collection<User> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTextFrom() {
		return textFrom;
	}

	public void setTextFrom(String textFrom) {
		this.textFrom = textFrom;
	}

	public String getTextTo() {
		return textTo;
	}

	public void setTextTo(String textTo) {
		this.textTo = textTo;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public LANGUAGE getLanguage() {
		return language;
	}

	public void setLanguage(LANGUAGE language) {
		this.language = language;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phrases other = (Phrases) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Phrases [id=" + id + "]";
	}
}