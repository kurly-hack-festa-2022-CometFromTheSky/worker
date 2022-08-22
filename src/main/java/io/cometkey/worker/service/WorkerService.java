package io.cometkey.worker.service;

import io.cometkey.worker.domain.Key;
import io.cometkey.worker.repository.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final KeyRepository keyRepository;

    public List<Key> getKey(List<Long> idList) { return this.keyRepository.findAllById(idList); }
    public String getEncryptedKey(Long keyId) {
        Key key = this.keyRepository.findById(keyId).get();
        if (key.getIsUsed()) {
            return "[Used Key Error] Key is expired.";
        } else {
            return key.getEncryptedKey();
        }
    }

    public Long addNewKey(Key key) { return this.keyRepository.save(key).getId(); }
    public void expireUsedKey(Long keyId) {
        Key key = this.keyRepository.findById(keyId).get();
        key.setIsUsed(true);
        this.keyRepository.save(key);
    }

}
