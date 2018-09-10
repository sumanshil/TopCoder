package com.vmware;

import javax.net.ssl.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.*;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.sun.xml.internal.xsom.impl.Ref;

public class DeleteVirtualWire {
    private static  String staticthumbprint = null;
    public static void main(String[] args) throws Exception {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        Authenticator.setDefault (new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication ("admin", "default".toCharArray());
            }
        });
        // Install the all-trusting host verifier
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
       // for ( int i = 106; i <= 205 ; i++) {
            //new MyCallable(0).run();
            //new AddVcToNsx().run();
            //new AddVcToNsxT(staticthumbprint).run();
              new GetComponentStatus(0).run();

        //}
        executorService.shutdown();
    }


    static class MyCallable implements Runnable {

        private int index;
        public  MyCallable(int i){
            this.index = i;
        }
        @Override
        public void run() {
            URL url = null;
            try {
                url = new URL("https://10.161.148.108/api/2.0/services/auth/token?expiresInMinutes=720");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
                con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                String userPassword = "admin" + ":" + "default";
                String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                con.addRequestProperty("Authorization", "Basic " + encoding);
                con.setRequestMethod("POST");
                System.out.println(con.getResponseCode());
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String str = null;
                while ((str = reader.readLine()) != null) {
                    stringBuilder.append(str);
                }
                System.out.println(stringBuilder.toString());
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (con != null){
                    con.disconnect();
                }
            }
        }
    }


    static class GetComponentStatus implements Runnable {

        private int index;
        public  GetComponentStatus(int i){
            this.index = i;
        }
        @Override
        public void run() {
            URL url = null;
            HttpURLConnection con = null;
            try {
//                con = (HttpURLConnection) url.openConnection();
 //               con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
//                String userPassword = "admin" + ":" + "Admin!23Admin";
//                String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
//                con.addRequestProperty("Authorization", "Basic " + encoding);
//                con.setRequestMethod("POST");

                RestTemplate restTemplate = new RestTemplate();

                AgentErrorHandler agentErrorHandler = new AgentErrorHandler();
                restTemplate.setErrorHandler(agentErrorHandler);
                HttpEntity httpEntity = new HttpEntity<String>(createHeaders("", ""));
                ResponseEntity<String> res = null;
                try {
                    res = restTemplate.exchange
                            ("https://10.160.173.228/api/v1/fabric/compute-managers/36667eb6-001f-48d8-8640-19aedf858034/status",
                             HttpMethod.GET, httpEntity, String.class);

                } catch (HttpStatusCodeException exception) {
                    int statusCode = exception.getStatusCode().value();
                    System.out.println(statusCode);
                    //                String errorpayload = res.getBody();
                    //                System.out.println(errorpayload);
                }
                if (res != null) {
                    System.out.println(res.getBody());
                }
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ComponentStatus componentStatus = objectMapper.readValue(res.getBody(), ComponentStatus.class);
                System.out.println(componentStatus);

                if (agentErrorHandler.getErrorMessage() != null) {
                    System.out.println(agentErrorHandler.getErrorMessage());
                    JsonNode jsonNode = agentErrorHandler.getErrorMessage().get("error_data");
                    JsonNode thumbprint = jsonNode.get("ValidCmThumbPrint");
                    if (thumbprint != null) {
                        System.out.println(thumbprint.asText());
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (con != null){
                    con.disconnect();
                }
            }
        }
    }

    static class AddVcToNsxT implements Runnable {
        private String thumbprint;
        public  AddVcToNsxT(String thumbprint) {
            this.thumbprint = thumbprint;
        }
        @Override
        public void run() {
            String input = "{\n"
                           + "  \"server\": \"10.161.163.78\",\n"
                           + "  \"origin_type\": \"vCenter\",\n"
                           + "  \"credential\" : {\n"
                           + "    \"credential_type\" : \"UsernamePasswordLoginCredential\",\n"
                           + "    \"username\": \"Administrator@vsphere.local\",\n"
                           + "    \"password\": \"Admin!23\",\n"
                           + "    \"thumbprint\" : \""+thumbprint+"\""
                           + "  }\n"
                           + "}";

            RestTemplate restTemplate = new RestTemplate();

            AgentErrorHandler agentErrorHandler = new AgentErrorHandler();
            restTemplate.setErrorHandler(agentErrorHandler);
            HttpEntity httpEntity = new HttpEntity<String>(input, createHeaders("", ""));
            ResponseEntity<Vcenter> res = null;
            try {
                res = restTemplate.exchange
                        ("https://10.160.173.228/api/v1/fabric/compute-managers",
                         HttpMethod.POST, httpEntity, Vcenter.class);

            } catch (HttpStatusCodeException exception) {
                int statusCode = exception.getStatusCode().value();
                System.out.println(statusCode);
//                String errorpayload = res.getBody();
//                System.out.println(errorpayload);
            }
            if (res != null) {
                System.out.println(res.getBody().getId());
            }

            if (agentErrorHandler.getErrorMessage() != null) {
                System.out.println(agentErrorHandler.getErrorMessage());
                JsonNode jsonNode = agentErrorHandler.getErrorMessage().get("error_data");
                JsonNode thumbprint = jsonNode.get("ValidCmThumbPrint");
                if (thumbprint != null) {
                    System.out.println(thumbprint.asText());
                }
            }
        }
    }

    public static class Vcenter {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    static class AddVcToNsx implements Runnable {

        public  AddVcToNsx(){
        }
        @Override
        public void run() {
            String input = "{\n"
                           + "  \"server\": \"10.161.163.78\",\n"
                           + "  \"origin_type\": \"vCenter\",\n"
                           + "  \"credential\" : {\n"
                           + "    \"credential_type\" : \"UsernamePasswordLoginCredential\",\n"
                           + "    \"username\": \"Administrator@vsphere.local\",\n"
                           + "    \"password\": \"Admin!23\"\n"
                           + "  }\n"
                           + "}";

            RestTemplate restTemplate = new RestTemplate();

            AgentErrorHandler agentErrorHandler = new AgentErrorHandler();
            restTemplate.setErrorHandler(agentErrorHandler);
            HttpEntity httpEntity = new HttpEntity<String>(input, createHeaders("", ""));
            ResponseEntity<Void> res = null;
            try {
                 res = restTemplate.exchange
                        ("https://10.160.173.228/api/v1/fabric/compute-managers",
                         HttpMethod.POST, httpEntity, Void.class);

            } catch (HttpStatusCodeException exception) {
                int statusCode = exception.getStatusCode().value();
                System.out.println(statusCode);
                //String errorpayload = res.getBody();
                //System.out.println(errorpayload);
            }

            if (agentErrorHandler.getErrorMessage() != null) {
                System.out.println(agentErrorHandler.getErrorMessage());
                JsonNode jsonNode = agentErrorHandler.getErrorMessage().get("error_data");
                JsonNode thumbprint = jsonNode.get("ValidCmThumbPrint");
                if (thumbprint != null) {
                    staticthumbprint = thumbprint.asText();
                    System.out.println(thumbprint.asText());
                }
            }
        }
    }

    public static HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = "admin" + ":" + "Admin!23Admin";
//            String encoding = new sun.misc.BASE64Encoder().encode(auth.getBytes());
            //con.addRequestProperty("Authorization", "Basic " + encoding);

            String encodedAuth = Base64.encode(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + encodedAuth;
            set( "Authorization", authHeader );
            set("Content-type", "application/json");
        }};
        /*
       return new HttpHeaders() {
            String userPassword = "admin" + ":" + "Admin!23Admin";
            String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
            //con.addRequestProperty("Authorization", "Basic " + encoding);

//             String auth = username + ":" + password;
//             byte[] encodedAuth = Base64.encodeBase64(
//                                    auth.getBytes(Charset.forName("US-ASCII")) );
//             String authHeader = "Basic " + new String( encodedAuth );
             set( "Authorization", "Basic " + encoding );
          };
        */
    }

    public static class AgentErrorHandler extends DefaultResponseErrorHandler {

        private JsonNode errorMessage;

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            if (response.getBody() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                errorMessage = objectMapper.readValue(response.getBody(), JsonNode.class);
            }
        }

        public JsonNode getErrorMessage() {
            return errorMessage;
        }
    }


    static class GetPlanMyCallable implements Runnable {

        private int index;
        public  GetPlanMyCallable(int i){
            this.index = i;
        }
        @Override
        public void run() {
            URL url = null;
            try {
                url = new URL("https://10.192.134.34/api/v1/migration/migration-unit-groups?component_type=PRECHECK");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
                con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                String userPassword = "admin" + ":" + "Admin!23Admin";
                String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                con.addRequestProperty("Authorization", "Basic " + encoding);
                con.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String str = null;
                while ((str = reader.readLine()) != null) {
                    stringBuilder.append(str);
                }
                System.out.println(stringBuilder.toString());
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (con != null){
                    con.disconnect();
                }
            }
        }
    }

}