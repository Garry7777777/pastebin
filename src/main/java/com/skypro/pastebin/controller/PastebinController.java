package com.skypro.pastebin.controller;

import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Expiration;
import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.service.PastebinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class PastebinController {

    @Autowired
    private PastebinService pastebinService;

    @GetMapping("/{id}")
    public PastebinDTO getByID(@PathVariable String id)   {
        return pastebinService.getByID(id);
    }

    @PostMapping
    public ResponseEntity<String> createPastebin(@RequestParam(name = "Expiration") Expiration expiration,
                                                 @RequestParam(name = "Exposure") Exposure exposure,
                                                 @RequestBody PastebinDTO pastebinDTO) {
        return ResponseEntity.ok("http://localhost:8080/" + pastebinService.createPastebin(expiration, exposure, pastebinDTO) );
    }

    @GetMapping("/search")
    public List<PastebinDTO> findInPastebin(@RequestParam(name = "title",required = false) String title,
                                          @RequestParam(name = "body",required = false) String body){
        return pastebinService.findInPastebin(title, body);
    }

    @GetMapping("/last")
    public List<PastebinDTO> getLastPublic() {
        return pastebinService.getLastPublic();
    }
}
