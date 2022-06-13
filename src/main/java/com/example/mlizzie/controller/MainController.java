package com.example.mlizzie.controller;

import com.example.mlizzie.services.ServiceExchangeRates;
import com.example.mlizzie.services.ServiceGetGif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    private ServiceGetGif gifService;
    private ServiceExchangeRates exchangeRates;
    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.error}")
    private String errorTag;

    @Autowired
    public MainController(ServiceGetGif gifService, ServiceExchangeRates exchangeRates) {
        this.gifService = gifService;
        this.exchangeRates = exchangeRates;
    }

    @GetMapping("/getgif/{code}")
    public ResponseEntity<Map> getGif(@PathVariable String code) {
        Integer res;
        String gifTag = this.errorTag;

        res = exchangeRates.compareExchangeRates(code);
        if (code == null || res == null) return gifService.getGif(gifTag);
        if (res > 0){
            gifTag = this.richTag;
        }
        else {
            gifTag = this.brokeTag;
        }
        return gifService.getGif(gifTag);
    }
}
