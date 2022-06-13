package com.example.mlizzie.client;

import com.example.mlizzie.model.ExchangeRatesModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "OERClient", url = "${openexchangerates.url.general}")
public interface FeignOpenExchangeRatesClient {

    @GetMapping("/latest.json")
    ExchangeRatesModel getLatestRates(@RequestParam("app_id") String appId, @RequestParam("base") String base);


    @GetMapping("/historical/{date}.json")
    ExchangeRatesModel getHistoricalRates(@PathVariable String date, @RequestParam("app_id") String appId, @RequestParam("base") String base);
}
