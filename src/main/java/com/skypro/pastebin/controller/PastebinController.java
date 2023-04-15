package com.skypro.pastebin.controller;

import com.skypro.pastebin.dto.CreatePastebinDTO;
import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.service.PastebinService;
import org.springframework.beans.factory.annotation.*;
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
    public ResponseEntity<?> createPastebin(@RequestBody CreatePastebinDTO pastebinDTO) {
        return ResponseEntity.ok().body(uri + pastebinService.createPastebin(pastebinDTO));
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
