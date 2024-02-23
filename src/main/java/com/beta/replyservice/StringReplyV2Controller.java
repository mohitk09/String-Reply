package com.beta.replyservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v2/reply")
public class StringReplyV2Controller {

    @GetMapping("/{rule}-{input}")
    public ResponseEntity<?> processV2Request(@PathVariable String rule, @PathVariable String input) {
        try {
            String result = RuleProcessor.processRule(rule, input);
            return ResponseEntity.ok(Map.of("data", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid input"));
        }
    }
}