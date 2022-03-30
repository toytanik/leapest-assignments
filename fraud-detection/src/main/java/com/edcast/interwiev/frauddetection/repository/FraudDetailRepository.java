package com.edcast.interwiev.frauddetection.repository;

import com.edcast.interwiev.frauddetection.model.FraudDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudDetailRepository extends JpaRepository<FraudDetail,Long> {


}
