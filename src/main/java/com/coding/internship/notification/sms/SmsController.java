package com.coding.internship.notification.sms;

import com.coding.internship.notification.email.EmailService;
import com.coding.internship.notification.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;
    private final EmailService emailService;

    @PostMapping("/send/{to}")
    public String sendSms(@PathVariable String to, @RequestBody String message) {
        smsService.sendSms(to, message);
        return "SMS Sent!";
    }
    @PostMapping
    public void sendEmail(){
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Chedli");
        variables.put("currentPlan", "Silver");
        variables.put("suggestedPlan", "Gold");

        emailService.sendHtmlEmail("chedli.masmoudi97@gmail.com", "Plan Recommendation", variables);

    }
}
