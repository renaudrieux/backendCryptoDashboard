package com.example.demo.coinGeckoApi;

import com.example.demo.crypto.Crypto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.springframework.context.annotation.Bean;
import org.json.JSONObject;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class CoinGeckoApiClient {

    public CoinGeckoApiClient() {
    }

    public Crypto getCrypto(String cryptoId) throws NotFoundException, IOException, InterruptedException {
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
        HttpClient client = buildClient();
        HttpRequest request = buildGetRequest(url);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new NotFoundException();
        }
        JSONArray jsonarray = new JSONArray(response.body());
        Crypto crypto = null;
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            if (jsonobject.getString("id").equals(cryptoId)) {
                String name = jsonobject.getString("name");
                float current_price = jsonobject.getFloat("current_price");
                crypto = new Crypto(cryptoId, name, current_price);
            }
        }
        if (crypto == null) throw new NotFoundException();
        else return crypto;
    }

    public List<Crypto> getCryptos() throws NotFoundException, IOException, InterruptedException {
        List<Crypto> cryptos = new ArrayList<>();
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
        HttpClient client = buildClient();
        HttpRequest request = buildGetRequest(url);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new NotFoundException();
        }
        JSONArray jsonarray = new JSONArray(response.body());
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String cryptoId = jsonobject.getString("id");
            String name = jsonobject.getString("name");
            float current_price = jsonobject.getFloat("current_price");
            cryptos.add(new Crypto(cryptoId, name, current_price));
            }
        return cryptos;
        }



    private HttpRequest buildGetRequest(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();
    }

    private HttpClient buildClient() {
        return HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }
}
