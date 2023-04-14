package com.skypro.pastebin.service;

import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Expiration;
import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.exception.PastebinNotFoundException;
import com.skypro.pastebin.model.Pastebin;
import com.skypro.pastebin.repository.PastebinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PastebinService {

    @Autowired
    private PastebinRepository pastebinRepository;

    public String createPastebin(Expiration expiration, Exposure exposure, PastebinDTO pastebinDTO) {
        return pastebinRepository.save(new Pastebin(pastebinDTO, exposure, expiration)).getHash();
    }

    public PastebinDTO getByHash(String hash)  {
            return pastebinRepository.findByHash(hash).orElseThrow(PastebinNotFoundException::new).toDTO();
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
