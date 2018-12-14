package models

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Entity
class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id

	String name

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = 'hero')
	List<Ability> abilities

	@ManyToMany(mappedBy = 'heroes')
	@JsonIgnore
	Set<Disaster> disasters

	boolean equals(o) {
		if (this.is(o))
			return true
		if (!(o instanceof Hero))
			return false

		Hero hero = (Hero) o

		if (id != hero.id)
			return false

		return true
	}

	int hashCode() {
		eturn (id != null ? id.hashCode() : 0)
	}


}
