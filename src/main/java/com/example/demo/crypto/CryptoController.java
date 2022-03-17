package com.example.demo.crypto;

import com.example.demo.coinGeckoApi.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CryptoController {

    private CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @RequestMapping(value = "/{cryptoId}", method = RequestMethod.GET)
    public Crypto getCrypto(@PathVariable String cryptoId) throws NotFoundException, IOException, InterruptedException {
        return cryptoService.getCrypto(cryptoId);
    }

    @RequestMapping(value = "save/{cryptoId}", method = RequestMethod.GET)
    public void saveCrypto(@PathVariable String cryptoId) throws NotFoundException, IOException, InterruptedException {
        cryptoService.saveCrypto(cryptoId);
    }

    @RequestMapping(value = "deleteSaved/{cryptoId}", method = RequestMethod.GET)
    public void deleteSavedCrypto(@PathVariable String cryptoId) throws NotFoundException, IOException, InterruptedException {
        cryptoService.deleteSavedCrypto(cryptoId);
    }


    @RequestMapping(value = "getSaved", method = RequestMethod.GET)
    public List<Crypto> getSavedCryptos() throws NotFoundException, IOException, InterruptedException {
        return cryptoService.getAllSavedCryptos();
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Crypto> getCryptos() throws NotFoundException, IOException, InterruptedException {
        return cryptoService.getCryptos();
    }


}
