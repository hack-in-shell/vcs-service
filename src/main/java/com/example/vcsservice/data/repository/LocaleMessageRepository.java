package com.example.vcsservice.data.repository;

import com.example.vcsservice.data.entity.LocaleMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocaleMessageRepository extends JpaRepository<LocaleMessage, Long> {
    Optional<LocaleMessage> findByLocale(String locale);

    Optional<LocaleMessage> findLocaleMessageByLocale(String locale);
}
