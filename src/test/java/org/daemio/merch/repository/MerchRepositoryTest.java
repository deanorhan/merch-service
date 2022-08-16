package org.daemio.merch.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties = {"spring.jpa.defer-datasource-initialization=true"})
public class MerchRepositoryTest {

    @Autowired
    private MerchRepository repo;
    
    @Test
    public void checkAllIsWell() {
        assertNotNull(repo, "Repository was not injected");
    }
}
