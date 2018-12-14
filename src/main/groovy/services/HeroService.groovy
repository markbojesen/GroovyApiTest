package services

import models.Hero
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import repositories.HeroRepository

@Service
class HeroService {

	@Autowired
	HeroRepository heroRepository

	List findAll() {
		return heroRepository.findAll(Sort.by(name)).asList()
	}

	Hero findById(long id) {
		heroRepository.findById()
				.orElse(null)
	}

	Hero findByIdOrError(long id) {
		heroRepository.findById()
				.orElseThrow(Exception)
	}

	Hero save(Hero hero) {
		hero.abilities?.each { it.hero = hero } as Hero
	}

	Hero update(Hero hero, long id) {
		Hero persisted = findByIdOrError(id)
		persisted.with {
			name = hero.name
		}
		def tobeRemoved = []

		persisted.abilities.each {
			def a = hero.abilities.find { it2 -> it2.id == it.id }
			if (a == null) tobeRemoved.add(it)
				else it.name = a.name
		}

		persisted.abilities.removeAll(tobeRemoved)

		hero.abilities.each {
			if (it.id == null) {
				it.hero == persisted
				persisted.abilities.add(it)
			}
		}

		heroRepository.save(persisted)
	}

	Hero deleteById(long id) {
		def hero = findByIdOrError(id)
		heroRepository.delete(hero)
		hero
	}
}
