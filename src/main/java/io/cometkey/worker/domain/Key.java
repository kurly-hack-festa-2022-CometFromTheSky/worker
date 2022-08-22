package io.cometkey.worker.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "KEY")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Key {

    @Id
    @GeneratedValue
    @Column(name = "key_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String encryptedKey;

    @NotNull
    private String provider;    // 제공회사

    @NotNull
    private Boolean isUsed;     // 배송기사 사용 여부

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Key(String encryptedKey, String provider, Boolean isUsed) {
        this.encryptedKey = encryptedKey;
        this.provider = provider;
        this.isUsed = isUsed;
    }

    @PrePersist
    public void PrePersist() {
        this.isUsed = this.isUsed != null && this.isUsed;
    }
}
