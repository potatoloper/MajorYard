package com.KAU.majorYard.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String newValue) {
        this.value = newValue;
        return this;
    }
}
