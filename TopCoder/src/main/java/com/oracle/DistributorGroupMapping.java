package com.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by sshil on 9/22/2016.
 */
// edi generation needed
// transport destination
public class DistributorGroupMapping {

    public static void main(String[] args) {
        try {
            //String distributorList = args[0];
            //String[] arr = distributorList.split(",");
            String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=ndc-vedip1.walmart.com)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=edip1)))";
            String strUserID = "integration_user";
            String strPassword = "intgrt10n1";
            Connection myConnection= DriverManager.getConnection(dbURL, strUserID, strPassword);

            //for (String str : arr) {
                Statement sqlStatement = myConnection.createStatement();
                String readRecordSQL = "select * from wfu_distributor_group_ref , wfu_distributor_group where distributor_id in (2568,53392,260195,347303,444141,477755,510107,534099,534504,629402,722653,26019502,40575302) and wfu_distributor_group.distributor_group = wfu_distributor_group_ref.distributor_group order by wfu_distributor_group_ref.distributor_id asc  " ;
                ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
                while (myResultSet.next()) {
                    String distributorGroup = myResultSet.getString("DISTRIBUTOR_GROUP");
                    String distributorId = myResultSet.getString("DISTRIBUTOR_ID");
                    String name = myResultSet.getString("NAME");
                    System.out.println("insert into wbr_distributor_group_mapping (distributor_group_id, partner_id, distributor_group_name) values ('"+distributorGroup+"', '"+distributorId+"','"+name+"');");
                }
                myResultSet.close();
            //}
            myConnection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
