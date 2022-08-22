package io.cometkey.worker.repository;

import io.cometkey.worker.domain.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Long> {
}
