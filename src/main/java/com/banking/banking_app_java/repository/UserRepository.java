package com.banking.banking_app_java.repository;

import com.banking.banking_app_java.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
