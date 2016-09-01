package com.oracle;

import com.migratioscript.FindDiff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by sshil on 8/24/2016.
 */
public class GenerationRequestSchedule {


//    public static void main(String[] args) {
//        for ( int i = 0 ; i < 10; i++ ) {
//            String str = "INSERT INTO wbr_generation_request_schedule (jobgroup,schedule,status) VALUES ('DSV_OrderReleaseGen_Group"+(i+1)+"','*/1 * * * *','COMPLETE');";
//            System.out.println(str);
//        }
//
//    }

    //
    public static void main(String[] args) {
        try {
            String[] arr = FindDiff.CURRENTDSVS.split(",");
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
            /*
            String readRecordSQL = "select count(*) from wfu_distributor where distributor_id in (" + str + ")";
            ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
            while (myResultSet.next()) {
                //System.out.println("Record values: " + myResultSet.getString("distributor_id"));
                String child = myResultSet.getString(1);
                System.out.println(child);
            }
            myResultSet.close();
            */


            String readRecordSQL = "select * from wfu_distributor where distributor_id in (" + str + ")";
            ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
            int groupIndex = 1;
            int count = 0;
            while (myResultSet.next()) {
                //System.out.println("Record values: " + myResultSet.getString("distributor_id"));
                if (count == 75){
                    count = 0;
                    groupIndex++;
                }
                String distributorId = myResultSet.getString("DISTRIBUTOR_ID");
                String distributorName = myResultSet.getString("DISTRIBUTOR_NAME");


                String str1 = "INSERT INTO wbr_batch_scheduler (jobgroup,jobtype,blocking) VALUES ('DSV_OrderReleaseGen_Group"+groupIndex+"','job-dsv-order-request-"+distributorId+"',false);";

                String str2 = "INSERT INTO wbr_jobtype (jobtype,headers,message_domain,message_template,message_type,partner_org_id,partner_org_name,properties,tags) VALUES ('job-dsv-order-request-"+distributorId+"',null,'DEFAULTDOMAIN','DSV_TEMPLATE','DSV_GENERATION_ORDERREQUEST','"+distributorId+"','"+distributorName+"',null,null);";
                System.out.println(str1);
                System.out.println(str2);
                count++;
            }
            myResultSet.close();

            str = FindDiff.DIFF_DSVS;
            readRecordSQL = "select * from wfu_distributor where distributor_id in (" + str + ")";
            myResultSet = sqlStatement.executeQuery(readRecordSQL);
            groupIndex = 1;
            count = 0;
            while (myResultSet.next()) {
                //System.out.println("Record values: " + myResultSet.getString("distributor_id"));
                if (count == 75){
                    count = 0;
                    groupIndex++;
                }
                String distributorId = myResultSet.getString("DISTRIBUTOR_ID");
                String distributorName = myResultSet.getString("DISTRIBUTOR_NAME");


                String str1 = "INSERT INTO wbr_batch_scheduler (jobgroup,jobtype,blocking) VALUES ('DSV_OrderReleaseGen_Group"+groupIndex+"','job-dsv-order-request-"+distributorId+"',false);";

                String str2 = "INSERT INTO wbr_jobtype (jobtype,headers,message_domain,message_template,message_type,partner_org_id,partner_org_name,properties,tags) VALUES ('job-dsv-order-request-"+distributorId+"',null,'DEFAULTDOMAIN','DSV_TEMPLATE','DSV_GENERATION_ORDERREQUEST','"+distributorId+"','"+distributorName+"',null,null);";
                System.out.println(str1);
                System.out.println(str2);
                count++;
            }
            myResultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

