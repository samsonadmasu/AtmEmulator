package com.Bank.Branch.Security;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class RefreshTokenDetails {
    private String id;
    private String username;
    private Date issuedDate;
    private Date expirationDate;
    private int refreshCount;
    private int refreshLimit;
    private String issuer;
}
