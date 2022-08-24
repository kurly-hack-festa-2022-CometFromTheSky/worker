package io.cometkey.worker.controller.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsageResponse {

    private String provider;    // 제공회사

    private Boolean isUsed;     // 배송기사 사용 여부

    @Builder
    public UsageResponse(String provider, Boolean isUsed) {
        this.provider = provider;
        this.isUsed = isUsed;
    }
}
