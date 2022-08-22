package io.cometkey.worker.controller;

import io.cometkey.worker.domain.Key;
import io.cometkey.worker.request.KeyList;
import io.cometkey.worker.request.NewKey;
import io.cometkey.worker.response.DeliveryResponse;
import io.cometkey.worker.response.KeyIdResponse;
import io.cometkey.worker.response.KeyResponse;
import io.cometkey.worker.response.UsageResponse;
import io.cometkey.worker.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/v1/worker/key")
    public KeyResponse getKeyInfo(@Valid @RequestBody KeyList keyList) {

        List<UsageResponse> keyResponse = new ArrayList<>();
        List<Key> keys = this.workerService.getKey(keyList.getIdList());

        for (Key key : keys) {
            keyResponse.add(UsageResponse.builder()
                    .provider(key.getProvider())
                    .isUsed(key.getIsUsed())
                    .build()
            );
        }

        return KeyResponse.builder()
                .usageResponseList(keyResponse)
                .build();
    }

    @GetMapping("/v1/worker/delivery/{key_id}")
    public DeliveryResponse getDeliveryInfo(@Valid @PathVariable String key_id) {

        return DeliveryResponse.builder()
                .encryptedKey(this.workerService.getEncryptedKey(Long.valueOf(key_id)))
                .build();
    }

    //TODO: Kafka consume
    @PostMapping("/v1/worker/key")
    public KeyIdResponse putKeyInfo(@Valid @RequestBody NewKey newKey) {

        return KeyIdResponse.builder()
                .keyId(this.workerService.addNewKey(Key.builder()
                                .encryptedKey(newKey.getEncryptedKey())
                                .provider(newKey.getProvider())
                                .isUsed(newKey.getIsUsed())
                                .build()
                        )
                )
                .build();
    }

    //TODO: Kafka consume
    @PostMapping("/v1/worker/delivery/{key_id}")
    public void PutDeliveryInfo(@Valid @PathVariable String key_id) {

        this.workerService.expireUsedKey(Long.valueOf(key_id));
    }
}
