package com.instagram.scaper.repository;

import com.instagram.scaper.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IgScraperRepository extends JpaRepository<Users, String> {

}
