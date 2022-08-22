package io.cometkey.worker.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class NewKey {

    @NotNull
    private String encryptedKey;

    @NotNull
    private String provider;    // 제공회사

    @NotNull
    private Boolean isUsed;     // 배송기사 사용 여부

    @Builder
    public NewKey(String encryptedKey, String provider, Boolean isUsed) {
        this.encryptedKey = encryptedKey;
        this.provider = provider;
        this.isUsed = isUsed;
    }
}
