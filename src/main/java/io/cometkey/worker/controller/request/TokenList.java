package io.cometkey.worker.controller.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class TokenList {

    @NotNull
    private List<String> tokenList;

    @Builder
    public TokenList(List<String> tokenList) {
        this.tokenList = tokenList;
    }
}
