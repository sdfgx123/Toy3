package com.fastcampus.toy3.domain.post.report.repository;

import com.fastcampus.toy3.domain.post.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class ReportJpaRepository implements ReportRepository{

    private final EntityManager em;

    @Transactional
    public Report save(Report report){
        em.persist(report);
        return report;
    }
}