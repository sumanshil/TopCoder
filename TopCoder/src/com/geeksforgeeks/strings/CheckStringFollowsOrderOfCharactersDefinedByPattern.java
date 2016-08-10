package com.geeksforgeeks.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sshil on 8/8/2016.
 */
//http://www.geeksforgeeks.org/check-string-follows-order-characters-defined-pattern-not/
public class CheckStringFollowsOrderOfCharactersDefinedByPattern {

    public void find(String str, String pattern){
        int patternIndex = 0;
        int stringIndex = 0;
        Set<String> patternSet = new HashSet<>();
        for ( int i = 0 ; i < pattern.length() ; i++){
            char c = pattern.charAt(i);
            patternSet.add(c+"");
        }
        Set<String> alreadyFound = new HashSet<>();
        boolean followsPattern = true;
        char prevPatternChar=' ';
        while (stringIndex < str.length() && patternIndex < pattern.length()) {
            if (str.charAt(stringIndex) == pattern.charAt(patternIndex)){
                alreadyFound.add(pattern.charAt(patternIndex)+"");
                prevPatternChar = pattern.charAt(patternIndex);
                stringIndex++;
                patternIndex++;
            } else if (str.charAt(stringIndex) == prevPatternChar){
                stringIndex++;
                continue;

            } else if (patternSet.contains(str.charAt(stringIndex)+"")){
                followsPattern = false;
                break;
            } else if (alreadyFound.contains(str.charAt(stringIndex)+"")){
                followsPattern = false;
                break;
            } else {
                stringIndex++;
            }
        }
        if (patternIndex < pattern.length()){
            followsPattern = false;
        }
        System.out.println(followsPattern);
    }
    public static void main(String[] args) {
        String str = "engineers rock";
        String pattern = "gsr";
        new CheckStringFollowsOrderOfCharactersDefinedByPattern().find(str, pattern);
    }
    /*
    private static String str = "INSERT INTO bedrockapp.wbr_batch_scheduler (jobgroup,jobtype,blocking) VALUES ('DSV_Inventory301_StressTest_Group_1','job-dsv-inventory301-stress-$',false);\n" +
        "INSERT INTO bedrockapp.wbr_jobtype (jobtype,headers,message_domain,message_template,message_type,partner_org_id,partner_org_name,properties,tags) VALUES ('job-dsv-inventory301-stress-$',null,'DEFAULTDOMAIN','JAVA_STRESS_TEST_TEMPLATE','DSV_INVENTORY_301_STRESS','$','Stress test partner $',null,null);\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-transport-destination-uri',{'proxy': 'transport-proxy-destination-external'},'file:/data/ftpusers/web/mp2_bridge/$/inbound');\n" +
        "INSERT INTO bedrockapp.wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','$-RUN_MODE',null,'false');\n" +
        "INSERT INTO bedrockapp.wbr_scan_source_point (batch_scanner_name,partner_org_id,message_type,dirs,file_age,file_pattern,headers,message_domain,message_template,org_id,partner_org_name,properties,status,tags) VALUES ('DSV_Group','$','XML_INVENTORY_V400',['/data/ftpusers/web/mp2_bridge/$/inbound'],10000,'.*[\\p{Alpha}_]+_$_\\p{Digit}{8}_\\p{Digit}{6}_\\p{Digit}{6}\\.\\p{Alpha}+|.*s8wQtdDR\\.AS2INBOUND\\..+|.*[\\p{Alpha}_]+_$_\\p{Digit}{8}_\\p{Digit}{6}_\\p{Digit}{6}\\.\\p{Alpha}+',null,'DEFAULTDOMAIN','DSV_TEMPLATE',null,'DSV Inventory test',null,'ACTIVE',null);\n";
    public void find(String input, String pattern){

    }
    public static void main(String[] args) {
        for ( int i = 2222205 ; i <= 2222210 ; i++){
            String str1 = str.replaceAll("\\$", i+"");
            System.out.println(str1);
        }
    }
    */
}
