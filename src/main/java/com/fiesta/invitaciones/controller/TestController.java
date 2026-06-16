package com.fiesta.invitaciones.controller;


import com.fiesta.invitaciones.mongo.Photo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestController {


    @GetMapping("/hola")
    public String like() {
        log.info("entre a revisar**********************************");
        return "hola perro";
    }


        @GetMapping("/mongo")
        public Map<String,Object> testMongo() {

            Map<String,Object> response =
                    new HashMap<>();

            try {

                MongoClient mongoClient =
                        MongoClients.create(
                                System.getenv("MONGODB_URI"));

                mongoClient.listDatabaseNames()
                        .forEach(System.out::println);

                response.put("status", "OK");

            } catch (Exception e) {

                response.put(
                        "error",
                        e.getClass().getName());

                response.put(
                        "message",
                        e.getMessage());

            }

            return response;
        }
    }

