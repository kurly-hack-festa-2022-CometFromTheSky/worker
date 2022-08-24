package io.cometkey.worker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cometkey.worker.domain.Key;
import io.cometkey.worker.repository.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final ObjectMapper objectMapper;

    private final KeyRepository keyRepository;

    public List<Key> getKey(List<String> tokenList) { return this.keyRepository.findAllByToken(tokenList); }
    public String getEncryptedKey(String token) {
        //TODO: .orElseThrow(NoSuchTokenException.class)
        Key key = this.keyRepository.findByToken(token).orElseThrow(null);
        if (key.getIsUsed()) {
            return "[Used Key Error] Key is expired.";
        } else {
            return key.getEncryptedKey();
        }
    }

    public void addNewKey(Key key) { this.keyRepository.save(key).getId(); }
    public void expireUsedKey(String token) {
        Key key = this.keyRepository.findByToken(token).get();
        key.setIsUsed(true);
        this.keyRepository.save(key);
    }

    @KafkaListener(topics="expired", groupId = "worker")
    public void consumeExpiredKey(String message) {
        expireUsedKey(message);
    }

    @KafkaListener(topics="newKey", groupId = "worker")
    public void consumeNewKey(String message) throws JsonProcessingException {
        addNewKey(this.objectMapper.readValue(message, Key.class));
    }
}
