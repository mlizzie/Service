package com.example.mlizzie.services;

import com.example.mlizzie.client.FeignOpenExchangeRatesClient;
import com.example.mlizzie.model.ExchangeRatesModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceOpenExchangeRatesTest {

    @Autowired
    private ServiceOpenExchangeRates exchangeRatesService;
    @MockBean
    private FeignOpenExchangeRatesClient openExchangeRatesClient;
    private ExchangeRatesModel currentRates;
    private ExchangeRatesModel prevRates;

    @Before
    public void init() {
        this.currentRates = new ExchangeRatesModel("disclaimer","license",111, "base",null);
        this.prevRates = new ExchangeRatesModel("disclaimer","license",110, "base",null);
    }

    @Test
    public void ExchangeRatesReturnNull() {

        Mockito.when(openExchangeRatesClient.getLatestRates(anyString(), anyString()))
                .thenReturn(null);
        Mockito.when(openExchangeRatesClient.getHistoricalRates(anyString(), anyString(), anyString()))
                .thenReturn(null);
        Integer result = exchangeRatesService.compareExchangeRates("TEST");
        assertNull(null, result);
    }
    @Test
    public void MapRatesReturnNull() {

        Mockito.when(openExchangeRatesClient.getLatestRates(anyString(), anyString()))
                .thenReturn(currentRates);
        Mockito.when(openExchangeRatesClient.getHistoricalRates(anyString(), anyString(), anyString()))
                .thenReturn(prevRates);
        Integer result = exchangeRatesService.compareExchangeRates("TEST");
        assertNull(null, result);
    }
    @Test
    public void codeNotFound() {

        currentRates.setRates(new HashMap<>());
        prevRates.setRates(new HashMap<>());
        currentRates.getRates().put("TEST1",12d);
        prevRates.getRates().put("TEST1",11d);

        Mockito.when(openExchangeRatesClient.getLatestRates(anyString(), anyString()))
                .thenReturn(currentRates);
        Mockito.when(openExchangeRatesClient.getHistoricalRates(anyString(), anyString(), anyString()))
                .thenReturn(prevRates);
        Integer result = exchangeRatesService.compareExchangeRates("TEST");
        assertNull(null, result);
    }
    @Test
    public void compareExchangeRates() {

        currentRates.setRates(new HashMap<>());
        prevRates.setRates(new HashMap<>());
        currentRates.getRates().put("TEST",12d);
        prevRates.getRates().put("TEST",11d);

        Mockito.when(openExchangeRatesClient.getLatestRates(anyString(), anyString()))
                .thenReturn(currentRates);
        Mockito.when(openExchangeRatesClient.getHistoricalRates(anyString(), anyString(), anyString()))
                .thenReturn(prevRates);
        int result = exchangeRatesService.compareExchangeRates("TEST");
        assertEquals(1, result);
    }
}
