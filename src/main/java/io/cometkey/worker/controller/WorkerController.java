package io.cometkey.worker.controller;

import io.cometkey.worker.domain.Key;
import io.cometkey.worker.controller.request.TokenList;
import io.cometkey.worker.controller.response.DeliveryResponse;
import io.cometkey.worker.controller.response.KeyResponse;
import io.cometkey.worker.controller.response.UsageResponse;
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
    public KeyResponse getKeyInfo(@Valid @RequestBody TokenList tokenList) {

        List<UsageResponse> keyResponse = new ArrayList<>();
        List<Key> keys = this.workerService.getKey(tokenList.getTokenList());

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

    @GetMapping("/v1/worker/{token}")
    public DeliveryResponse getDeliveryInfo(@Valid @PathVariable String token) {

        return DeliveryResponse.builder()
                .encryptedKey(this.workerService.getEncryptedKey(token))
                .build();
    }
}
