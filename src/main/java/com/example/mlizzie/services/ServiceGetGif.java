package com.example.mlizzie.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ServiceGetGif {

    ResponseEntity<Map> getGif(String tag);
}
