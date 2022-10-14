package study;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ApiCall {

    public static String svrIP = "http://localhost:8090/api/pom/v1";
    public static int connTimeout = 3000;

    public static int connReqTimeout = 3000;
    public static int socketConnTimeout = 3000;

    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";

    public void setPomSvrIp(String serverIp){ svrIP = serverIp;}

    public void setConnTimeout(int connectionTimeout){ connTimeout = connectionTimeout;}
    public void setConnReqTimeout(int connectionRequestTimeout){ connReqTimeout = connectionRequestTimeout;}
    public void setSocketConnTimeout(int socketconnectionTimeout){ socketConnTimeout = socketconnectionTimeout;}

    public static void main(String[] args) throws IOException {
        getStudents();
        postStudents();
        putStudents();
    }

    //생성자1 : pomSvr를 기본으로 만들어진다
    public ApiCall(){
        setPomSvrIp(svrIP);
        setConnTimeout(connTimeout);
        setConnReqTimeout(connReqTimeout);
        setSocketConnTimeout(socketConnTimeout);
    }

    //생성자2 : 다른 서버에 연결할 경우 (인자를 받아서 초기화)
    public ApiCall(String newSvrIP, int newConnTimeout, int newConnReqTimeout, int newSocketConnTimeout){
        ApiCall.svrIP = newSvrIP;
        ApiCall.connTimeout = newConnTimeout;
        ApiCall.connReqTimeout = newConnReqTimeout;
        ApiCall.socketConnTimeout = newSocketConnTimeout;
    }

    public JSONObject callApi (String apiURL, String reqMethod, HttpServletRequest request, JSONObject sendContent) throws Exception {
        return callApi(apiURL, reqMethod, request, sendContent);
    }

    // get 요청 시작
    private static void getStudents() throws ClientProtocolException, IOException {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(30000)
                .build();

        try (CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet("http://localhost:8888/home");
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            HttpResponse httpResponse = httpclient.execute(httpget);
            httpResponse.getStatusLine();
            httpResponse.getEntity();

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        }
    }
    // get 요청 종료

    // post 요청 시작
    public static void postStudents() throws IOException {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(30000)
                .build();

        try (CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

            HttpPost httpPost = new HttpPost("http://localhost:8090/api/pom/v1/data/group/gps/add");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("CLIENT-TYPE", "CLIENT");
            httpPost.setHeader("DIVA-AUTH", "IVX-1234567890");
            String json = "{\r\n" +
                    "  \"firstName\": \"Ram\",\r\n" +
                    "  \"lastName\": \"Jadhav\",\r\n" +
                    "  \"emailId\": \"ramesh1234@gmail.com\",\r\n" +
                    "  \"createdAt\": \"2018-09-11T11:19:56.000+0000\",\r\n" +
                    "  \"createdBy\": \"Ramesh\",\r\n" +
                    "  \"updatedAt\": \"2018-09-11T11:26:31.000+0000\",\r\n" +
                    "  \"updatedby\": \"Ramesh\"\r\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(json);
            httpPost.setEntity(stringEntity);

            System.out.println("Executing request " + httpPost.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        }
    }
    // post 요청 종료

    // put 요청 시작
    public static void putStudents() throws IOException {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(30000)
                .build();

        try (CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {
            HttpPut httpPut = new HttpPut("http://localhost:8090/api/pom/v1/data/group/update");
            httpPut.setHeader("Accept", "*/*");
            httpPut.setHeader("Content-type", "application/json");
            httpPut.setHeader("CLIENT-TYPE", "CLIENT");
            httpPut.setHeader("DIVA-AUTH", "IVX-1234567890");

            String json = "{\r\n"
                    + "  \"firstName\": \"Ram\",\r\n"
                    + "  \"lastName\": \"Jadhav\",\r\n"
                    + "  \"emailId\": \"ramesh1234@gmail.com\",\r\n"
                    + "  \"createdAt\": \"2018-09-11T11:19:56.000+0000\",\r\n"
                    + "  \"createdBy\": \"Ramesh\",\r\n"
                    + "  \"updatedAt\": \"2018-09-11T11:26:31.000+0000\",\r\n"
                    + "  \"updatedby\": \"Ramesh\"\r\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(json);
            httpPut.setEntity(stringEntity);

            System.out.println("Executing request " + httpPut.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpPut, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        }
    }
    // put 요청 종료

}
