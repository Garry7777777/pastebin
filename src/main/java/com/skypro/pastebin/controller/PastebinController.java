package com.skypro.pastebin.controller;

import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Expiration;
import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.service.PastebinService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class PastebinController {

    @Value("${starting.pastebin.uri}")
    private  String uri;

    @Autowired
    private PastebinService pastebinService;

    @GetMapping("/{id}")
    public PastebinDTO getByHash(@PathVariable String id)   {
        return pastebinService.getByHash(id);
    }

    @PostMapping
    public ResponseEntity<?> createPastebin(@RequestParam(name = "Expiration") Expiration expiration,
                                                 @RequestParam(name = "Exposure") Exposure exposure,
                                                 @RequestBody PastebinDTO pastebinDTO) throws JSONException {

        return ResponseEntity.ok().body(new JSONObject()
                .put("uri",uri + pastebinService.createPastebin(expiration, exposure, pastebinDTO)).toString());
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
