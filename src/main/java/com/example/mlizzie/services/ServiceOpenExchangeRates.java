package com.example.mlizzie.services;

import com.example.mlizzie.client.FeignOpenExchangeRatesClient;
import com.example.mlizzie.model.ExchangeRatesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@Service
public class ServiceOpenExchangeRates implements ServiceExchangeRates{

    private ExchangeRatesModel prevRates;
    private ExchangeRatesModel currentRates;
    private FeignOpenExchangeRatesClient openExchangeRatesClient;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Value("${openexchangerates.app.id}")
    private String appId;
    @Value("${openexchangerates.base}")
    private String base;

    @Autowired
    public ServiceOpenExchangeRates(FeignOpenExchangeRatesClient openExchangeRatesClient){
        this.openExchangeRatesClient = openExchangeRatesClient;
    }

    @Override
    public Integer compareExchangeRates(String code) {

        Calendar prevCalendar = Calendar.getInstance();
        prevCalendar.add(Calendar.DAY_OF_YEAR, -1);
        String prevDate = dateFormat.format(prevCalendar.getTime());

        currentRates = openExchangeRatesClient.getLatestRates(this.appId, base);
        prevRates = openExchangeRatesClient.getHistoricalRates(prevDate,this.appId,base);

        if (currentRates == null || prevRates == null || currentRates.getRates() == null || prevRates.getRates() == null) return null;
        if (!(currentRates.getRates().containsKey(code) || prevRates.getRates().containsKey(code))) return null;
        Double rate = currentRates.getRates().get(code);
        Double prevRate = prevRates.getRates().get(code);
        return rate.compareTo(prevRate);
    }
}
