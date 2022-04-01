package com.edcast.interwiev.frauddetection.repository;

import com.edcast.interwiev.frauddetection.model.FraudDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FraudDetailRepository extends JpaRepository<FraudDetail,Long> {
    List<FraudDetail> findByDateBetweenAndCompany(String start, String end, String company);
}
