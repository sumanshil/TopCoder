package com.vmware;

import javax.net.ssl.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.security.cert.X509Certificate;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeleteVirtualWire {
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
        for ( int i = 106; i <= 205 ; i++) {
            executorService.submit(new MyCallable(i));
        }
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
                url = new URL("https://10.161.29.37/api/2.0/vdn/virtualwires/virtualwire-"+index);
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
                con.setRequestMethod("DELETE");
                Reader reader = new InputStreamReader(con.getInputStream());
                while (true) {
                    int ch = reader.read();
                    if (ch == -1) {
                        break;
                    }
                    System.out.print((char) ch);
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
}