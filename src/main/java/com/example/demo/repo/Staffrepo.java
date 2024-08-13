package com.example.demo.repo;

import com.example.demo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Staffrepo extends JpaRepository<Staff,Long> {
}
