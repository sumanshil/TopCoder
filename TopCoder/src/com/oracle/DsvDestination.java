package com.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static com.oracle.FacilityMapping.DSVS;

/**
 * Created by sshil on 8/12/2016.
 */
public class DsvDestination {

    public static void main(String[] args) {
        try {
            String[] arr = DSVS.split(",");
            Set<String> dsvs = new HashSet<>();
            int count = 0;
            for (String str : arr) {
                dsvs.add(str.replaceAll("\\n", ""));
                count++;
            }
            System.out.println(count);
            System.out.println(dsvs.size());
            String str = String.join(",", dsvs);
            String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=ndc-vedip1.walmart.com)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=edip1)))";
            String strUserID = "integration_user";
            String strPassword = "intgrt10n1";
            Connection myConnection = DriverManager.getConnection(dbURL, strUserID, strPassword);

            Statement sqlStatement = myConnection.createStatement();
            String readRecordSQL = "select wfu_message_types.distributor_id, wfu_destination.destination_uri from wfu_message_types,wfu_destination where wfu_message_types.distributor_id in ("+str+") and wfu_message_types.destination_id = wfu_destination.destination_id and wfu_message_types.action_id = 457";
            System.out.println(readRecordSQL);
            ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<ROWDATA>\n");
            int finalCount = 0;
            Set<String> found = new HashSet<>();
            while (myResultSet.next()) {
                //System.out.println("Record values: " + myResultSet.getString("distributor_id"));
                String child = myResultSet.getString("DISTRIBUTOR_ID");
                String destinationURI = myResultSet.getString("DESTINATION_URI");
                stringBuilder.append("<ROW><DISTRIBUTOR>"+child+"</DISTRIBUTOR><DESTINATION>"+destinationURI+"</DESTINATION></ROW>\n");
                finalCount++;
                found.add(child);
            }
            myResultSet.close();
            stringBuilder.append("</ROWDATA>\n");
            System.out.println(stringBuilder.toString());
            System.out.println(finalCount);
            dsvs.removeAll(found);
            System.out.println("======>\n"+dsvs);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
