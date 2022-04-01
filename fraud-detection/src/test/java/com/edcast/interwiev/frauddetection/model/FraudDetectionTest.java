package com.edcast.interwiev.frauddetection.model;

import com.edcast.interwiev.frauddetection.model.FraudDetail;
import com.edcast.interwiev.frauddetection.repository.FraudDetailRepository;
import com.edcast.interwiev.frauddetection.util.TestHelper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
* To unit test the DAO layer,
*  we first need an in-memory test database.
*  This we can achieve using @AutoConfigureTestDatabase.

Then we need to use @DataJpaTest which disables
*  full auto-configuration and instead apply only configuration
* relevant to JPA tests.*/
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FraudDetectionTest {

    @Autowired
    FraudDetailRepository fraudDetailRepository;

    @Test
    public void shouldCreateFraudDetectionWithProperInput() {
        //GIVEN
        FraudDetail fraudDetail = TestHelper.createFraudDetail();

        //WHEN
        fraudDetailRepository.save(fraudDetail);
        Iterable<FraudDetail> customers = fraudDetailRepository.findAll();

        //THEN
        Assertions.assertThat(customers).
                extracting(FraudDetail::getCountry).containsOnly("tr");
        Assertions.assertThat(customers).
                extracting(FraudDetail::getCard).containsOnly("4234 2344 2344 2434");
        Assertions.assertThat(customers).
                extracting(FraudDetail::getCompany).containsOnly("tuncaycompany");

        fraudDetailRepository.deleteAll();
        Assertions.assertThat(fraudDetailRepository.findAll()).isEmpty();
    }

}


