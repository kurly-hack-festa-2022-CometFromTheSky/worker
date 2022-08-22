package io.cometkey.worker.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeyIdResponse {

    private Long keyId;

    @Builder
    public KeyIdResponse(Long keyId) {
        this.keyId = keyId;
    }
}
