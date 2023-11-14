package com.example.dasom.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class SmsService {
    String serviceId = "ncp:sms:kr:316486650918:dasom";
    String accessKey = "72RIMhDJMQT8qVvvx4Ng";
    String secretKey = "ZOTb1BNsQjqLtJwhBNr2KAkA9Y6JqQYtxfT7AQjL";
    String method = "POST";


    String requestUrl  = "/sms/v2/services/" + serviceId + "/messages";
    String apiUrl = "https://sens.apigw.ntruss.com" + requestUrl;

    public Map<String, Object> sendMessage(String phoneNumber){
        String timeStamp = Long.toString(System.currentTimeMillis());
        Map<String, String> message = new HashMap<>();
        message.put("to", phoneNumber);

        String authNumber = makeAuthNumber();

        List<Map> messages = List.of(message);

        Map<String, Object> body = new HashMap<>();
        body.put("content", "인증 번호(6자리) : " + authNumber);
        body.put("type", "SMS");
        body.put("from", "01049432518");
        body.put("messages", messages);

        WebClient webClient = null;


        try {
            webClient = WebClient.builder()
                    .baseUrl(apiUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader("x-ncp-apigw-timestamp", timeStamp)
                    .defaultHeader("x-ncp-iam-access-key", accessKey)
                    .defaultHeader("x-ncp-apigw-signature-v2", makeSignature(timeStamp))
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        Mono<Map> resultBody = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorResume(e -> {
                    // 오류 처리를 여기에 추가하세요.
                    e.printStackTrace(); // 오류 메시지 출력 예제
                    return Mono.empty(); // 또는 다른 적절한 처리
                });

        Map<String, Object> result = new HashMap<>();
        result.put("mono", resultBody);
        result.put("authNumber", authNumber);

        return result;

    }

    private String makeSignature(String timeStamp) throws NoSuchAlgorithmException, InvalidKeyException {
        String message = new StringBuilder()
                .append(method)
                .append(" ")
                .append(requestUrl)
                .append("\n")
                .append(timeStamp)
                .append("\n")
                .append(accessKey)
                .toString();

        SecretKeySpec secretKeySpec = null;
        String encBase64 = null;
        Mac mac = null;

        secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        encBase64 = Base64.getEncoder().encodeToString(rawHmac);

        return encBase64;
    }

    private String makeAuthNumber(){
        Random random = new Random();
        String authNumber = "";

        for(int i = 0; i < 6; i++){
            authNumber += random.nextInt(10);
        }

        return authNumber;

    }

}