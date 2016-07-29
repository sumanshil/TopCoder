package com.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static com.oracle.FacilityMapping.DSVS;

/**
 * Created by sshil on 7/27/2016.
 */
public class XML4_0_0 {
    public static void main(String[] args) {
        try {
            String[] arr = DSVS.split(",");
            Set<String> dsvs = new HashSet<>();
            for (String str : arr) {
                dsvs.add(str);
            }
            String str = String.join(",", dsvs);
            String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=ndc-vedip1.walmart.com)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=edip1)))";
            String strUserID = "integration_user";
            String strPassword = "intgrt10n1";
            Connection myConnection = DriverManager.getConnection(dbURL, strUserID, strPassword);

            Statement sqlStatement = myConnection.createStatement();
            String readRecordSQL = "select * from wfu_message_types where file_type_id = 8 and distributor_id in (" + str + ")";
            ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<ROWDATA>\n");

            while (myResultSet.next()) {
                //System.out.println("Record values: " + myResultSet.getString("distributor_id"));
                String child = myResultSet.getString("DISTRIBUTOR_ID");
                stringBuilder.append("<ROW>"+child+"</ROW>\n");
            }
            myResultSet.close();
            stringBuilder.append("</ROWDATA>\n");

            System.out.println(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
