package com.example.mlizzie.services;

import com.example.mlizzie.client.FeignGiphyClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceGetGifFromGiphyTest {
    @Autowired
    private ServiceGetGifFromGiphy gifService;
    @MockBean
    private FeignGiphyClient gifClient;

    @Test
    public void getGif() {
        ResponseEntity<Map> testEntity = new ResponseEntity<>(new HashMap(), HttpStatus.OK);
        Mockito.when(gifClient.getRandomGif(anyString(), anyString()))
                .thenReturn(testEntity);
        ResponseEntity<Map> result = gifService.getGif("test");
        assertEquals("test", result.getBody().get("compareResult"));
    }
}