package com.skypro.pastebin.dto;

import com.skypro.pastebin.enums.Expiration;
import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.model.Pastebin;
import lombok.Data;
import java.time.Instant;

@Data
public class CreatePastebinDTO {
    private String title;
    private String body;
    private Exposure exposure;
    private Expiration expiration;

    public Pastebin toPastebin() {
        var pastebin = new Pastebin();
        pastebin.setTitle(this.getTitle());
        pastebin.setBody(this.getBody());
        pastebin.setExposure(this.getExposure());
        pastebin.setExpired( Instant.now().plus(this.expiration.timeUnit,this.expiration.chronoUnit));
        return pastebin;
    }

}
