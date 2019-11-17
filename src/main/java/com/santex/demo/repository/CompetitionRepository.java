package com.santex.demo.repository;

import com.santex.demo.dto.CompetitionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionDto, Long> {
}
