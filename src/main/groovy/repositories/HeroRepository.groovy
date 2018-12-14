package repositories

import models.Hero
import org.springframework.data.repository.PagingAndSortingRepository

interface HeroRepository extends PagingAndSortingRepository<Hero, Long>{

}