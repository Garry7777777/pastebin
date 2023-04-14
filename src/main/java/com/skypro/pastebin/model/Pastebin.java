package com.skypro.pastebin.model;

import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Exposure;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class Pastebin {
    @Id
    private String id = UUID.randomUUID().toString().split("-")[0];
    private String title;
    private String body;
    private Exposure exposure;
    private Instant created;
    private Instant expired;

    public PastebinDTO toDTO() {
        PastebinDTO pastebinDTO = new PastebinDTO();
        pastebinDTO.setTitle(this.getTitle());
        pastebinDTO.setBody(this.getBody());
        return pastebinDTO;
    }
}
