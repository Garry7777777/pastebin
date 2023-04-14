package com.skypro.pastebin.service;

import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Expiration;
import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.exception.PastebinNotFoundException;
import com.skypro.pastebin.repository.PastebinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class PastebinService {

    @Autowired
    private PastebinRepository pastebinRepository;

    public String createPastebin(Expiration expiration, Exposure exposure, PastebinDTO pastebinDTO) {
        var pastebin = pastebinDTO.toPastebin();
        pastebin.setExposure(exposure);
        pastebin.setCreated(Instant.now());
        pastebin.setExpired(Instant.now().plus(expiration.timeUnit, expiration.chronoUnit));
        pastebinRepository.save(pastebin);
        return pastebin.getId();
    }

    public PastebinDTO getByID(String id)  {
            return pastebinRepository.findById(id).orElseThrow(PastebinNotFoundException::new).toDTO();
    }

    public List<PastebinDTO> findInPastebin(String title,String body) {
        return  pastebinRepository.findAllByBodyContainingOrTitleContaining(body,title)
                        .stream().map(PastebinDTO::toDTO).toList();
    }

    public List<PastebinDTO> getLastPublic() {
        return pastebinRepository.findTop10ByExposureOrderByCreatedDesc(Exposure.Public)
                .stream().map(PastebinDTO::toDTO).toList();
    }
}
