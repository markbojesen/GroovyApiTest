package repositories

import models.Disaster
import org.springframework.data.repository.PagingAndSortingRepository

interface DisasterRepository extends PagingAndSortingRepository<Disaster, Long> {

}