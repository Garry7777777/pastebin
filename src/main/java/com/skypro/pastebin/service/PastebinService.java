package com.skypro.pastebin.service;

import com.skypro.pastebin.dto.CreatePastebinDTO;
import com.skypro.pastebin.dto.PastebinDTO;
import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.exception.PastebinNotFoundException;
import com.skypro.pastebin.repository.PastebinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import static com.skypro.pastebin.repository.specification.PastebinSpecifications.*;

@Service
public class PastebinService {

    @Autowired
    private PastebinRepository pastebinRepository;

    public String createPastebin(CreatePastebinDTO createPastebinDTO) {
        var pastebin = createPastebinDTO.toPastebin();
        pastebinRepository.save(pastebin);
        return pastebin.getId();
    }

    public PastebinDTO getById(String id)  {
        return pastebinRepository.findByIdAndExpiredIsAfter(id, Instant.now())
                        .orElseThrow(PastebinNotFoundException::new).toDTO();
    }

    public List<PastebinDTO> findInPastebin(String title,String body) {
        return pastebinRepository.findAll(Specification.where(
                      byExposure(Exposure.Public))
                .and( byExpired(Instant.now()))
                .and( byBody(body).and( byTitle(title))))
                .stream().map(PastebinDTO::toDTO).toList();
//        return  pastebinRepository.findTextInBodyAndTitle(body,title)
//                .stream().map(PastebinDTO::toDTO).toList();
    }

    public List<PastebinDTO> getLastPublic() {
        return pastebinRepository.findTop10ByExposureAndExpiredIsAfterOrderByCreatedDesc
                        (Exposure.Public, Instant.now()).stream().map(PastebinDTO::toDTO).toList();
    }
}
