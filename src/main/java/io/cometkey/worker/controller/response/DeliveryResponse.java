package io.cometkey.worker.controller.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryResponse {

    private String encryptedKey;

    @Builder
    public DeliveryResponse(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }
}
