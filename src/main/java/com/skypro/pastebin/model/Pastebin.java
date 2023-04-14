package com.skypro.pastebin.model;

import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Expiration;
import com.skypro.pastebin.enums.Exposure;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Pastebin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash = UUID.randomUUID().toString().split("-")[0];
    private String title;
    private String body;
    private Exposure exposure;
    private Instant created = Instant.now();
    private Instant expired = Instant.now();

    public Pastebin(PastebinDTO pastebinDTO, Exposure exposure, Expiration expiration) {
        this.title = pastebinDTO.getTitle();
        this.body = pastebinDTO.getBody();
        this.exposure = exposure;
        this.expired = this.expired.plus(expiration.timeUnit, expiration.chronoUnit);
    }

    public PastebinDTO toDTO() {
        PastebinDTO pastebinDTO = new PastebinDTO();
        pastebinDTO.setTitle(this.getTitle());
        pastebinDTO.setBody(this.getBody());
        return pastebinDTO;
    }
}
