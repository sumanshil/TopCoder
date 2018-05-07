package com.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by sshil on 9/22/2016.
 */
public class DistributorInfo {

    public static void main(String[] args) {
        try {
            String distributorList = args[0];
            String[] arr = distributorList.split(",");
            String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=ndc-vedip1.walmart.com)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=edip1)))";
            String strUserID = "integration_user";
            String strPassword = "intgrt10n1";
            Connection myConnection= DriverManager.getConnection(dbURL, strUserID, strPassword);

            for (String str : arr) {
                Statement sqlStatement = myConnection.createStatement();
                String readRecordSQL = "select * from wfu_distributor where distributor_id = "+str;
                ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
                while (myResultSet.next()) {
                    String ediId = myResultSet.getString("EDI_ID");
                    String ediQualifier = myResultSet.getString("EDI_ID_QUALIFIER");
                    String outStageDir = myResultSet.getString("OUT_STAGE_DIR");
                    String ediDelimitSegment = myResultSet.getString("EDI_DELIMIT_SEGMENT");
                    String ediDelimitElement = myResultSet.getString("EDI_DELIMIT_ELEMENT");
                    String ediDelimitField = myResultSet.getString("EDI_DELIMIT_FIELD");
                    String timeZone = myResultSet.getString("TIME_ZONE");
                    if (ediId != null)
                    System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+str+"-ediId',null,'"+ediId+"');");
                    if (ediQualifier != null)
                    System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+str+"-ediQualifier',null,'"+ediQualifier+"');");
                    if (outStageDir != null) {
                        System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+str+"-outStageDir',null,'"+outStageDir+"');");
                    }
                    if (ediDelimitSegment != null) {
                        System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+str+"-ediSegmentDelimiter',null,'"+ediDelimitSegment+"');");
                    }
                    if (ediDelimitElement != null){
                        System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+str+"-ediElementDelimiter',null,'"+ediDelimitElement+"');");
                    }
                    if (ediDelimitField != null) {
                        System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+str+"-ediFieldDelimiter',null,'"+ediDelimitField+"');");
                    }
                    if (timeZone != null) {
                        System.out.println("INSERT INTO wbr_name_value_pair (environment,name,other_name_value_pairs,value) VALUES ('DEFAULT','"+str+"-timeZone',null,'"+timeZone+"');");
                    }

                }
                myResultSet.close();
            }
            myConnection.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
