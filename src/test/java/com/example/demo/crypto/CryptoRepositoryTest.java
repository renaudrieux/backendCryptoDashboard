package com.example.demo.crypto;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class CryptoRepositoryTest {

    @Autowired
    private CryptoRepository repository;

    @Test
    void itShouldSelectIfToeknExistsById(){
        //given
        Crypto crypto = new Crypto("bitcoin", "bitcoin", 40000);
        repository.save(crypto);
        //when
        boolean exists = repository.existsById("bitcoin");
        //then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldNotFindIfDeleted(){
        //given
        Crypto crypto = new Crypto("bitcoin", "bitcoin", 40000);
        repository.save(crypto);
        repository.deleteById("bitcoin");
        //when
        boolean exists = repository.existsById("bitcoin");
        //then
        assertThat(exists).isFalse();
    }

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

}