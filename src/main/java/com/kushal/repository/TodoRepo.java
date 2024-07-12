package com.kushal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kushal.model.App;

@Repository
public interface TodoRepo extends JpaRepository<App, Long> {
}
