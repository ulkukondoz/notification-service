package com.usergems.dao;

import com.usergems.model.EmailNotification;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<EmailNotification, Long> {
}
