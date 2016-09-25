package com.oracle;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.oracle.FacilityMapping.DSV1;
import static com.oracle.FacilityMapping.PARENTS;

/**
 * Created by sshil on 8/31/2016.
 */
public class SoftLaunch {

    public static void main(String[] args) {
        Set<String> set1 = Arrays.stream(DSV1.split(",")).collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(PARENTS.split(",")).collect(Collectors.toSet());
        set1.addAll(set2);
        set1.stream().forEach(e -> {
           // System.out.println("mkdir "+e);
            System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+e+"-RUN_MODE',null,'false');");
            System.out.println("INSERT INTO wbr_name_value_pair(environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+e+"-transport-destination-uri',{'proxy':'@transport-proxy-destination-external@'},'sftp://bedrock@ndc-vibob.walmart.com:22//ftpusers/staging/B2BSOFTLUANCH/"+e+"/');");
        });
    }
}
