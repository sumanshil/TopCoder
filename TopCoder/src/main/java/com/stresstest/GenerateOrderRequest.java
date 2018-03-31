package com.stresstest;

/**
 * Created by sshil on 9/8/2016.
 */
public class GenerateOrderRequest {

    private static String str = "INSERT INTO bedrockapp.wbr_batch_scheduler (jobgroup,jobtype,blocking) VALUES ('DSV_OrderReleaseGen_Group_2','job-dsv-order-request-$',false);\n" +
        "INSERT INTO bedrockapp.wbr_jobtype (jobtype,headers,message_domain,message_template,message_type,partner_org_id,partner_org_name,properties,tags) VALUES ('job-dsv-order-request-$',null,'DEFAULTDOMAIN','DSV_TEMPLATE','DSV_GENERATION_ORDERREQUEST','$','Test',null,null);\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-ediQualifier',null,'ZZ');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-ediId',null,'PFSGISPROD'); \n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-outStageDir',null,'v2RPFGqD');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-DSV-ORDER-REQUEST-xmlVersion',null,'4.0.0');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-timeZone',null,'America/New_York');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-ediSegmentDelimiter',null,'&#10;');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-ediElementDelimiter',null,'^');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-ediFieldDelimiter',null,'*');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-DSV-ORDER-REQUEST-showBillingInfoForPaypalAndShoppingCard',null,'true');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-CREATE_EDI_AS_MESSAGE-transport',{'proxy':'@transport-proxy-destination-external@','transport.sftp.fileChmodCode':'777'},'sftp://mp2_bridge:bridgePass@bedrockapp-ibob.qa1.bedrock-nextgen.cp.qa.walmart.com:8022/12345/outbound/'); \n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-DSV-ORDER-REQUEST-transport',{'proxy':'@transport-proxy-destination-external@','transport.sftp.fileChmodCode':'777'},'sftp://mp2_bridge:bridgePass@bedrockapp-ibob.qa1.bedrock-nextgen.cp.qa.walmart.com:8022/12345/outbound/');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-DSV-ORDER-REQUEST-ediGenerationNeeded',null,'true');\n";
    public static void main(String[] args) {
        for (int i = 1111110 ; i <=1111120 ; i++){
            String out = str.replaceAll("\\$",i+"");
            System.out.println(out);
        }
    }
}
