package com.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static com.oracle.FacilityMapping.DSVS;

/**
 * Created by sshil on 7/27/2016.
 */
public class EDIGeneration {

    public static void main(String[] args) throws SQLException {
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
            String readRecordSQL = "select distributor_id from wfu_message_types where message_type in (254,766,685,879,1194,1270,1365,1707,1738,1842,1854,2046,2254,2445,2326,2521,2408,2589,2544,2622,2575,2382,2812,2557,2458,3140,2371,3216,3161,3329,2753,3350,2504,3406,3194,3836,3743,3696,4008,4325,4147,4518,3709,3858,4171,4239,4111,3795,2825,2727,4293,4193,3773,4484,4635,4633,5105,4568,4,5020,5382,5257,5146,4754,4981,4959,5280,4718,5101,4837,5210,5632,107,5530,5805,5656,5443,5585,5735,5042,5335,5930,6003,2756,6239,6423,6310,6217,6539,6627,4019,6464,6606,6563,314,6773,6749,6981,6926,7411,7204,7528,7099,7370,7487,2637,7025,8351,8442,8109,8372,2013,8733,8528,8400,8709,8755,432,11235,11192,11134,11438,11516,1177,11350,14396,11611,14705,15001,14617,8632,2770,17865,18062,18102,18616,18578,20574,20644,20745,30702,20793,30559,31746,32171,31634,31212,31721,32196,31559,32300,32375,32899,32270,31921,33051,33101,34559,34141,32424,31584,32929,32350,32773,34116,31871,32850,31971,31696,34444,32600,34355,34610,31534,31946,32954,32066,33076,34091,34264,32687,34331,32325,32748,34806,34192,82092,15749,82168,82219,82294,82344,82369,82480,82505,82571,82721,82608,82770,82696,82746,83020,82827,82970,82925,83146,82995,33803,32900,32525,10812,84551,83530,83641,32016,87088,83455,21059,83819,32500,88232,85092,21253,21192,2481,83363,90624,87004,89578,91802,91540,40001,91827,50321,92420,90114,60064,83831,91393,50424,50549,90954,50659,50714,50828,50840,50853,51098,90127,93083,55559,55621,51196,55807,51260,51285,55861,51883,51460,51310,51568,52494,56162,70131,70112,53514,54056,54178,53984,54667,21621,56641,56742,53961,53923,21602,69556,69531,69585,72215,71114,54865,73696,55286,69651,69621,20927,54783,71096)";
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
