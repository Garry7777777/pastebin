package com.skypro.pastebin.model;

import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Exposure;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.Instant;

//    Berners-Lee, et al.         Standards Track                    [Page 12]
//    RFC 3986                   URI Generic Syntax               January 2005
//
//    2.3.  Unreserved Characters.
//    Characters that are allowed in a URI but do not have a reserved
//    purpose are called unreserved.  These include uppercase and lowercase
//    letters, decimal digits, hyphen, period, underscore, and tilde.
//
//            unreserved  = ALPHA / DIGIT / "-" / "." / "_" / "~"

@Entity
@Data
@NoArgsConstructor
public class Pastebin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash = RandomStringUtils.randomAlphanumeric(8);
    private String title;
    private String body;
    private Exposure exposure;
    private Instant created = Instant.now();
    private Instant expired;

    public PastebinDTO toDTO() {
        PastebinDTO pastebinDTO = new PastebinDTO();
        pastebinDTO.setTitle(this.getTitle());
        pastebinDTO.setBody(this.getBody());
        return pastebinDTO;
    }
}
