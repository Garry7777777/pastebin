package com.skypro.pastebin.scheduler;

import com.skypro.pastebin.repository.PastebinRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Slf4j
@Component
public class clearExpiredPastebinScheduler {

    @Autowired
    private PastebinRepository pastebinRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void clearTokens() {
        log.info("произведена очистка устаревших записей");
        pastebinRepository.deleteAllByExpiredIsBefore(Instant.now());
    }

}
