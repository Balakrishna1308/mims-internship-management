package com.mfa.mims.repository;

import com.mfa.mims.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long>{

    List<Progress> findByTraineeId(String traineeId);

}