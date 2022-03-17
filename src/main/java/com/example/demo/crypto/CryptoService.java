package com.example.demo.crypto;

import com.example.demo.coinGeckoApi.CoinGeckoApiClient;
import com.example.demo.coinGeckoApi.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoService {

    

    private final CryptoRepository cryptoRepository;
    private final CoinGeckoApiClient coinGeckoApiClient;

    @Autowired
    public CryptoService(CryptoRepository cryptoRepository, CoinGeckoApiClient coinGeckoApiClient) {
        this.cryptoRepository = cryptoRepository;
        this.coinGeckoApiClient = coinGeckoApiClient;
    }

    public Crypto getCrypto(String cryptoId) throws NotFoundException, IOException, InterruptedException {
        return coinGeckoApiClient.getCrypto(cryptoId);
    }

    public List<Crypto> getCryptos() throws NotFoundException, IOException, InterruptedException {
        return coinGeckoApiClient.getCryptos();
    }

    public void saveCrypto(String cryptoId) throws NotFoundException, IOException, InterruptedException {
        Crypto crypto = coinGeckoApiClient.getCrypto(cryptoId);
        Optional<Crypto> CryptoById =cryptoRepository.findById(crypto.getId());
        if (!CryptoById.isPresent()){this.cryptoRepository.save(coinGeckoApiClient.getCrypto(cryptoId));}
    }

    public void deleteSavedCrypto(String cryptoId) {
        Optional<Crypto> CryptoById =cryptoRepository.findById(cryptoId);
        if (CryptoById.isPresent()){cryptoRepository.deleteById(cryptoId);}
    }

    public List<Crypto> getAllSavedCryptos() {
        return this.cryptoRepository.findAll();
    }

}
