package com.skypro.pastebin.repository;

import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.model.Pastebin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface PastebinRepository extends JpaRepository<Pastebin, String> {

    void deleteAllByExpiredIsBefore(Instant instant);
    List<Pastebin> findAllByBodyContainingOrTitleContaining(String body,String title);
    List<Pastebin> findTop10ByExposureOrderByCreatedDesc(Exposure exposure);

}
