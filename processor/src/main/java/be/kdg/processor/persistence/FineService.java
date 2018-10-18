package be.kdg.processor.persistence;

import be.kdg.processor.model.Fine;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 17/10/2018 20:24
 */
@Service
@Transactional
public class FineService {

    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public Fine load(Long id) {
        Optional<Fine> fine = fineRepository.findById(id);
        return fine.orElseGet(Fine::new);
    }

    public List<Fine> load(Boolean approved) {
        return fineRepository.findByApproved(approved);
    }

    public List<Fine> load() {
        return fineRepository.findAll();
    }

    public List<Fine> load(LocalDateTime timestampStart, LocalDateTime timestampEnd) {
        return fineRepository.findAllByTimestampBetween(timestampStart, timestampEnd);
    }

    public Fine save(Fine fine) {
        return fineRepository.save(fine);
    }
}