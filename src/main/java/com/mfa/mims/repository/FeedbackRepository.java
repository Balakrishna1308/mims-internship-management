package com.mfa.mims.repository;

import com.mfa.mims.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT DISTINCT f.category FROM Feedback f")
    List<String> findDistinctCategories();
}
