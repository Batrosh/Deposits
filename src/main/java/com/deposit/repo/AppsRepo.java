package com.deposit.repo;

import com.deposit.model.Apps;
import com.deposit.model.enums.AppsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppsRepo extends JpaRepository<Apps, Long> {
    List<Apps> findAllByStatus(AppsStatus appsStatus);
}
