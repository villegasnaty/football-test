package com.santex.demo.repository;

import com.santex.demo.dto.TeamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamDto, Long> {
}
