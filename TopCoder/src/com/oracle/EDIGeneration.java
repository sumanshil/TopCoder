package com.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static com.oracle.FacilityMapping.DSV1;

/**
 * Created by sshil on 7/27/2016.
 */
public class EDIGeneration {

    public static void main(String[] args) throws SQLException {
        try {
            String[] arr = DSV1.split(",");
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
            String readRecordSQL = "select distributor_id from wfu_message_types where message_type in (CURRENT_DSV)";
            ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<ROWDATA>\n");

            while (myResultSet.next()) {
                //System.out.println("Record values: " + myResultSet.getString("distributor_id"));
                String child = myResultSet.getString("DISTRIBUTOR_ID");
                stringBuilder.append("<ROW>"+child+"</ROW>\n");
            }
            myResultSet.close();

            readRecordSQL = "select distributor_id from wfu_message_types where message_type in (DIFF_DSV)";
            myResultSet = sqlStatement.executeQuery(readRecordSQL);
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
