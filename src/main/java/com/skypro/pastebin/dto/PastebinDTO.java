package com.skypro.pastebin.dto;

import com.skypro.pastebin.model.Pastebin;
import lombok.Data;

@Data
public class PastebinDTO {
    private String title;
    private String body;

    public Pastebin toPastebin() {
            Pastebin pastebin = new Pastebin();
            pastebin.setTitle(this.getTitle());
            pastebin.setBody(this.getBody());
            return pastebin;
    }
    public static PastebinDTO toDTO(Pastebin pastebin) {
        PastebinDTO pastebinDTO = new PastebinDTO();
        pastebinDTO.setTitle(pastebin.getTitle());
        pastebinDTO.setBody(pastebin.getBody());
        return pastebinDTO;
    }
}
