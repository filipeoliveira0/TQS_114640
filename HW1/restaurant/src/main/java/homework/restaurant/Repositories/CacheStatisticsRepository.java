package homework.restaurant.Repositories;

import homework.restaurant.Models.CacheStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheStatisticsRepository extends JpaRepository<CacheStatistics, Long> {
    // We'll typically have just one record, but could be extended for multiple cache types
}
