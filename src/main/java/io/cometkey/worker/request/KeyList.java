package io.cometkey.worker.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class KeyList {

    @NotNull
    private List<Long> idList;

    @Builder
    public KeyList(List<Long> idList) {
        this.idList = idList;
    }
}
