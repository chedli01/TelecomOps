package com.coding.internship.drools.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataVerificationResult {
    private boolean isVerified;
    private boolean sendSmsAlert;
    private boolean sendEmailUpgradeRecommendation;
    private boolean upgradeClientToVip;
}
