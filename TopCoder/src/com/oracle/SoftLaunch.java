package com.oracle;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * Created by sshil on 8/31/2016.
 */
public class SoftLaunch {

    public static void main(String[] args) throws IOException {
        //Set<String> set1 = Arrays.stream(DSV1.split(",")).collect(Collectors.toSet());
        //Set<String> set2 = Arrays.stream(PARENTS.split(",")).collect(Collectors.toSet());
        //set1.addAll(set2);
        String str = args[0];
        String[] arr = str.split(",");
        File file = Files.createTempFile("TEMP", "MKDIR").toFile();
        PrintWriter printWriter = new PrintWriter(file);

        Arrays.stream(arr).forEach(e -> {
            printWriter.write("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+e+"-RUN_MODE',null,'live');\n");
            printWriter.write("INSERT INTO wbr_name_value_pair(environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+e+"-transport-destination-uri',{'proxy':'@transport-proxy-destination-external@'},'sftp://bedrock@ndc-vibob.walmart.com:22//ftpusers/staging/B2BSOFTLUANCH/"+e+"');\n");
        });
        printWriter.close();
        System.out.println("Content written to "+file.getAbsolutePath());
    }
}
