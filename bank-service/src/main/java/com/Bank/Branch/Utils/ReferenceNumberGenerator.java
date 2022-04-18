package com.Bank.Branch.Utils;

import java.util.UUID;

public class ReferenceNumberGenerator {
    public String generate(){
        return UUID.randomUUID().toString();
    }
}
