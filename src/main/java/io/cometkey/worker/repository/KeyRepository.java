package io.cometkey.worker.repository;

import io.cometkey.worker.domain.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeyRepository extends JpaRepository<Key, Long> {

    List<Key> findAllByToken(List<String> tokenList);
    Optional<Key> findByToken(String token);
}
