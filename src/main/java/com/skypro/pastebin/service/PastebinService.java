package com.skypro.pastebin.service;

import com.skypro.pastebin.dto.CreatePastebinDTO;
import com.skypro.pastebin.dto.PastebinDTO;
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

    public String createPastebin(CreatePastebinDTO createPastebinDTO) {
        return pastebinRepository.save(createPastebinDTO.toPastebin()).getHash();
    }

    public PastebinDTO getByHash(String hash)  {
        return pastebinRepository.findByHashAndExpiredIsAfter(hash, Instant.now())
                        .orElseThrow(PastebinNotFoundException::new).toDTO();
    }

    public List<PastebinDTO> findInPastebin(String title,String body) {
        return  pastebinRepository.findAllByExposureAndExpiredIsAfterAndBodyContainingOrExposureAndExpiredIsAfterAndTitleContaining
                        (Exposure.Public,Instant.now(),body,Exposure.Public,Instant.now(),title).stream().map(PastebinDTO::toDTO).toList();
    }

    public List<PastebinDTO> getLastPublic() {
        return pastebinRepository.findTop10ByExposureAndExpiredIsAfterOrderByCreatedDesc
                        (Exposure.Public, Instant.now()).stream().map(PastebinDTO::toDTO).toList();
    }
}
