package com.example.mlizzie.services;

import com.example.mlizzie.client.FeignGiphyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServiceGetGifFromGiphy implements ServiceGetGif {

    private FeignGiphyClient gifClient;
    @Value("${giphy.api.key}")
    private String apiKey;

    @Autowired
    public ServiceGetGifFromGiphy(FeignGiphyClient gifClient) {
        this.gifClient = gifClient;
    }
    @Override
    public ResponseEntity<Map> getGif(String tag) {
        ResponseEntity<Map> result = gifClient.getRandomGif(this.apiKey, tag);
        result.getBody().put("compareResult", tag);
        return result;
    }
}
