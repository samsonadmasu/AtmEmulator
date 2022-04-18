package com.Bank.Branch.Security;

public class JwtConstants {
    public static final String CLAIM_NAME_ROLES = "roles";
    public static final String CLAIM_NAME_REFRESH_COUNT = "refresh_count";
    public static final String CLAIM_NAME_REFRESH_LIMIT = "refresh_limit";
    public static final String JWT_TIME_FORMAT_HOUR = "h";
    public static final String JWT_TIME_FORMAT_MINUTE = "m";
    public static final String JWT_TIME_FORMAT_SECOND ="s";
    public static final String JWT_TIME_FORMAT_MILLI_SECOND = "S";
    public static final String JWT_VALID_TIME_FORMAT = "/^[1-9][0-9]*[m|s|S|h]{1}$/";


}
