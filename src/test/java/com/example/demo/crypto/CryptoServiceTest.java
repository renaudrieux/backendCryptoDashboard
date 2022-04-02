package com.example.demo.crypto;

import com.example.demo.coinGeckoApi.CoinGeckoApiClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    @Mock private CryptoRepository repository;
    @Autowired private CoinGeckoApiClient apiClient;
    private CryptoService service;

    @BeforeEach
    void setUp() {
        service = new CryptoService(repository, apiClient);
    }

    @Test
    void getAllSavedCryptos() {
        //when
        service.getAllSavedCryptos();
        //then
        verify(repository).findAll();
    }
}