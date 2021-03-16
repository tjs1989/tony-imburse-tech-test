package com.common;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Constants {
    //Endpoints
    public static final String IMBURSE_SANDBOX_BASE_URL = "https://sandbox-api.imbursepayments.com/";
    public static final String HMAC_ENDPOINT = "v1/identity/hmac";
    public static final String ORDER_MANAGEMENT_ENDPOINT = "v1/order-management/";
    public static final String INSTRUCTION_ENDPOINT = "/instruction";


    //Keys
    public static final String PUBLIC_AUTH_KEY = "7934d5e6-260c-46d5-9309-e72a59cb90cd";
    public static final String PRIVATE_AUTH_KEY = "aWRpTN9tRsf2EyM8rcvz7bohO/fAg6IF+daZ1JYE9AM=";

    //IDs
    public static final String TENANT_ID = "6423ae63-59b6-4986-a949-c910ac622471";
    public static final String ACCOUNT_ID = "782f1b71-7ca4-4465-917f-68d58ffbec8b";
    public static final String SCHEME_ID = "654EB81FF7F07F7CF5A1EE3FF6972E90";

    //API Headers
    public static final String JSON_CONTENT_HEADER_TYPE = "application/json";
    public static final String AUTHORIZATION_HEADER_TYPE = "Authorization";
    public static final String X_ACCOUNT_ID_HEADER =  "x-account-id";
    public static final String X_TENANT_ID_HEADER = "x-tenant-id";

    //Error Codes
    public static final String ORDER_ALREADY_EXISTS_ERROR_CODE = "ORDER_ALREADY_EXISTS";

    //Directions
    public static final String DEBIT_DIRECTION = "DEBIT";

}
