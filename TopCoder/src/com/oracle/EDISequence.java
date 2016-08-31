package com.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.oracle.FacilityMapping.DSV1;

/**
 * Created by sshil on 8/24/2016.
 */
public class EDISequence {

    private static Map<Integer, String> sequence_types =  new HashMap();
    static {
        sequence_types.put(1,"ISA");
        sequence_types.put(2, "824");
        sequence_types.put(3, "850");
        sequence_types.put(4, "860");
        sequence_types.put(5, "997");
        sequence_types.put(6, "855");
        sequence_types.put(7, "856");
        sequence_types.put(8, "NON EDI");
        sequence_types.put(9, "852");
    }
    public static void main(String[] args) {
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

            String readRecordSQL = "select * from wfu_sequence";
            ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
            while (myResultSet.next()) {
                //System.out.println("Record values: " + myResultSet.getString("distributor_id"));
                String distributorId = myResultSet.getString("DISTRIBUTOR_ID");
                int ediId = Integer.parseInt(myResultSet.getString("EDI_SEQUENCE_ID"));
                int currentVal = Integer.parseInt(myResultSet.getString("CURRENT_VALUE"));
                String ediIdSeq = sequence_types.get(ediId);
                String str1 = "INSERT INTO wbr_edi_sequence (distributor_id,edi_sequence_id,current_value,modified_dtm) VALUES ('"+distributorId+"','"+ediIdSeq+"',"+currentVal+",null);";
                System.out.println(str1);
            }
            myResultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
