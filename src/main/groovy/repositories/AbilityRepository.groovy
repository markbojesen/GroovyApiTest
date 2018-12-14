package repositories

import models.Ability
import org.springframework.data.repository.CrudRepository

interface AbilityRepository extends CrudRepository<Ability, Long>{

}