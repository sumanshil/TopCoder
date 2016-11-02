package com.topcoder.geeksforgeeks;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by sshil on 10/28/16.
 */
public class DOM {
    private static String str = "<OR_ORDER  REQUESTNUMBER=\"2576899655638\" ORDERNUMBER=\"5731668425977\">\n" +
            "    <OR_DATEPLACED DAY=\"28\" MONTH=\"10\" YEAR=\"2016\"></OR_DATEPLACED>\n" +
            "    <OR_SHIPPING METHODCODE=\"MV\"\n" +
            "                 CARRIERMETHODCODE=\"20\"\n" +
            "                 TOGETHERCODE=\"SC\">\n" +
            "        <OR_PHONE\n" +
            "                PRIMARY=\"3603815106\"\n" +
            "        >\n" +
            "        </OR_PHONE>\n" +
            "        <OR_POSTAL NAME=\"Jennifer  K Smith\"\n" +
            "                       ADDRESS1=\"406 Redwood St\"\n" +
            "                       CITY=\"Port Townsend\"\n" +
            "                       STATE=\"WA\"\n" +
            "                       POSTALCODE=\"98368\"\n" +
            "                       COUNTRY=\"USA\"\n" +
            "        >\n" +
            "        </OR_POSTAL>\n" +
            "        <OR_DELIVERYDATE\n" +
            "                DAY=\"07\"\n" +
            "                MONTH=\"11\"\n" +
            "                YEAR=\"2016\">\n" +
            "        </OR_DELIVERYDATE>\n" +
            "        <OR_EXPECTEDSHIPDATE\n" +
            "                    DAY=\"01\"\n" +
            "                    MONTH=\"11\"\n" +
            "                    YEAR=\"2016\">\n" +
            "        </OR_EXPECTEDSHIPDATE>\n" +
            "    </OR_SHIPPING>\n" +
            "    <OR_BILLING\n" +
            "            ORDERPRICE=\"413.49\">\n" +
            "        <OR_PAYMENT METHOD=\"Visa-4893\"></OR_PAYMENT>\n" +
            "        <OR_PHONE PRIMARY=\"9738009299\"\n" +
            "                  PRIMARYEXT=\"\"\n" +
            "                  SECOND=\"\"\n" +
            "                       SECONDEXT=\"\">\n" +
            "        </OR_PHONE>\n" +
            "        <OR_POSTAL NAME=\"Agostino Ippolito\"\n" +
            "                   ADDRESS1=\"0\"\n" +
            "                   ADDRESS2=\" \"\n" +
            "                   ADDRESS3=\" \"\n" +
            "                   ADDRESS4=\" \"\n" +
            "                   CITY=\"0\"\n" +
            "                   STATE=\"0\"\n" +
            "                   POSTALCODE=\"0\"\n" +
            "                   COUNTRY=\"0\">\n" +
            "        </OR_POSTAL>\n" +
            "        <OR_EMAIL>pb95678@gmail.com</OR_EMAIL>\n" +
            "    </OR_BILLING>\n" +
            "    <OR_RETURNS\n" +
            "            TCNUMBER=\"39481877195624105148860\"\n" +
            "            METHODCODE=\"RC\">\n" +
            "        <OR_POSTAL\n" +
            "                NAME=\"\"\n" +
            "                ADDRESS1=\"\"\n" +
            "                ADDRESS2=\"\"\n" +
            "                ADDRESS3=\"\"\n" +
            "                ADDRESS4=\"\"\n" +
            "                CITY=\"\"\n" +
            "                STATE=\"\"\n" +
            "                POSTALCODE=\"\"\n" +
            "                COUNTRY=\"\"\n" +
            "            >\n" +
            "        </OR_POSTAL>\n" +
            "        <OR_PERMIT\n" +
            "                NUMBER=\"\"\n" +
            "                CITY=\"\"\n" +
            "                STATE=\"\"\n" +
            "                POSTALCODE=\"\">\n" +
            "        </OR_PERMIT>\n" +
            "    </OR_RETURNS>\n" +
            "    <OR_ORDERLINE LINENUMBER=\"1\"\n" +
            "            LINEPRICE=\"413.49\">\n" +
            "        <OR_ITEM  ITEMNUMBER=\"13224655\"\n" +
            "                  UPC=\"0009538574545\"\n" +
            "                  SKU=\"88-9100-1023\"\n" +
            "                  DESCRIPTION=\"Home Styles Large Kitchen Cart, White / Salt & Pepper Granit\"\n" +
            "                  QUANTITY=\"1\">\n" +
            "        </OR_ITEM>\n" +
            "        <OR_PRICE\n" +
            "                    RETAIL=\"413.49\"\n" +
            "                    TAX=\"0.00\"\n" +
            "                    SHIPPING = \"0.00\">\n" +
            "        </OR_PRICE>\n" +
            "        <OR_COST AMOUNT=\"318.00\"></OR_COST>\n" +
            "    </OR_ORDERLINE>\n" +
            "    <OR_LASTDELIVERYMSG LINE1=\"0\" LINE2=\"0\" LINE3=\"0\" LINE4=\"0\"></OR_LASTDELIVERYMSG>\n" +
            "    <OR_MARKETINGMSG LINE1=\"0\" LINE2=\"0\" LINE3=\"0\" LINE4=\"0\"></OR_MARKETINGMSG>\n" +
            "    <OR_RETURNSMSG LINE1=\"0\" LINE2=\"0\" LINE3=\"0\" LINE4=\"0\"></OR_RETURNSMSG>\n" +
            "</OR_ORDER>";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //parse using builder to get DOM representation of the XML file
        if (str.contains(" & ")){
           str = str.replaceAll("&(?!amp;)", "&amp;");
        }
        System.out.println(str);
        Document document =  db.parse(new ByteArrayInputStream(str.getBytes()));
        str = str.replaceAll("&amp;", "&");
        System.out.println(str);
        System.out.println("success");
    }
}
