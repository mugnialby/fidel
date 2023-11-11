package com.alby.emailservice.repository;

import com.alby.emailservice.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
