package com.coding.internship.notification.sms;

import com.coding.internship.notification.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/send/{to}")
    public String sendSms(@PathVariable String to, @RequestBody String message) {
        smsService.sendSms(to, message);
        return "SMS Sent!";
    }
}
