package tech.astrocode.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.astrocode.poi.model.PointOfInterest;

import java.util.List;

@Repository
public interface PoiRepository extends JpaRepository<PointOfInterest, Long> {
    @Query("""
            SELECT p FROM PointOfInterest p
            WHERE (p.x >= :xMin AND p.x <= :xMax AND p.y >= :yMin AND p.y <= :yMax)
            """)
    List<PointOfInterest> findByNearMe(@Param("xMin") long xMin,
                                       @Param("xMax") long xMax,
                                       @Param("yMin") long yMin,
                                       @Param("yMan") long yMax);
}
