package be.kdg.processor.persistence;

import be.kdg.processor.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mathijs Constantin
 * @version 1.0 17/10/2018 20:28
 */
public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByAmount(int amount);
    List<Fine> findByApproved(Boolean approved);
    @Query("select a from Fine a where a.fineData.timestamp > :timestampStart and a.fineData.timestamp < :timestampEnd")
    List<Fine> findAllByTimestampBetween(@Param("timestampStart") LocalDateTime timestampStart, @Param("timestampEnd") LocalDateTime timestampEnd);
}
