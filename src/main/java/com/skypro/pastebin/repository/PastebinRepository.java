package com.skypro.pastebin.repository;

import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.model.Pastebin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PastebinRepository extends JpaRepository<Pastebin, String> {

    void deleteAllByExpiredIsAfter(Instant instant);
    List<Pastebin> findAllByExposureAndExpiredIsAfterAndBodyContainingOrExposureAndExpiredIsAfterAndTitleContaining(Exposure exposure1,Instant instant1,String body,Exposure exposure2,Instant instant2, String title);
    List<Pastebin> findTop10ByExposureAndExpiredIsAfterOrderByCreatedDesc(Exposure exposure,Instant instant);
    Optional<Pastebin> findByHashAndExpiredIsAfter(String hash, Instant instant);
}
