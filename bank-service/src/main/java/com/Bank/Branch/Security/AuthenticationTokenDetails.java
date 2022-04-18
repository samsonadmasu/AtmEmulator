package com.Bank.Branch.Security;
import lombok.*;


import java.util.Date;
import java.util.List;

@Getter
@Builder
public class AuthenticationTokenDetails {
    private String id;
    private String username;
    private Date issuedDate;
    private Date expirationDate;
    private String issuer;

}
