package com.skypro.pastebin.repository.specification;

import com.skypro.pastebin.enums.Exposure;
import com.skypro.pastebin.model.*;
import org.springframework.data.jpa.domain.Specification;
import java.time.Instant;

public class PastebinSpecifications {

    public static Specification<Pastebin> byExposure(Exposure exposure) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal( root.get(Pastebin_.exposure), exposure);
    }
    public static Specification<Pastebin> byExpired(Instant instant) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan( root.get(Pastebin_.expired), instant);
    }
    public static Specification<Pastebin> byBody(String text){
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isBlank()) return criteriaBuilder.conjunction();
            return  criteriaBuilder.like(root.get(Pastebin_.body), "%"+text+"%");
        };
    }
    public static Specification<Pastebin> byTitle(String text){
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isBlank()) return criteriaBuilder.conjunction();
            return  criteriaBuilder.like(root.get(Pastebin_.title), "%"+text+"%");
        };
    }
}
