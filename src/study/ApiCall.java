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
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ApiCall {

    public static String svrIP;
    public static int connTimeout = 3000;
    public static int connReqTimeout = 3000;
    public static int socketConnTimeout = 3000;

    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";

    public void setSvrIp(String serverIp){ svrIP = serverIp;}

    public void setConnTimeout(int connectionTimeout){ connTimeout = connectionTimeout;}
    public void setConnReqTimeout(int connectionRequestTimeout){ connReqTimeout = connectionRequestTimeout;}
    public void setSocketConnTimeout(int socketconnectionTimeout){ socketConnTimeout = socketconnectionTimeout;}

    /*public static void main(String[] args) throws IOException {
        getStudents();
        System.out.println("GET DONE\n\n");
//        postStudents();
//        System.out.println("POST DONE\n\n");
        putStudents();
        System.out.println("PUT DONE\n\n");
    }*/

    public void getStudents() throws ClientProtocolException, IOException {
        // timeout config 설정
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(3000) // the time to establish the connection with the remote host :: 호스트 서버에 소켓 연결 시 타임아웃
                .setConnectionRequestTimeout(3000) // the time to wait for a connection from the connection manager/pool :: 커넥션풀과의 연결 시 타임아웃
                .setSocketTimeout(3000) // the time waiting for data – after establishing the connection; maximum time of inactivity between two data packets :: 요청/응답 사이의 타임아웃
                .build();

        // 설정한 config를 적용한 HttpClient 생성
        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        String responseBody = null;
        try {
            // HTTP GET method
            // 1) 헤더 세팅
            HttpGet httpget = new HttpGet(svrIP);
            httpget.setHeader("Accept", "*/*");
            httpget.setHeader("Content-type", "application/json");
            httpget.setHeader("CLIENT-TYPE", "CLIENT");
            httpget.setHeader("DIVA-AUTH", "IVX-1234567890");

            System.out.println("========================================= Executing request =========================================");
            System.out.println("Executing request " + httpget.getRequestLine());

            // 3) Response Handler 사용해서 결과 처리
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode(); // response 에서 결과 Code값 가져오기
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity(); // 200대면 결과 내용 가져오기
                        return entity != null ? EntityUtils.toString(entity) : null; // 내용이 있으면 string 으로 변환해서 보내고 아니면 null return
                    } else {
                        throw new ClientProtocolException("Errored Response Status: " + status);
                    }
                }
            };

            // 4) responseBody 만들어서 콘솔에 출력
            responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("\n==================================== Response Body ====================================");
            System.out.println(responseBody);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        } finally {
            httpclient.close();
        }
    }
    // get 요청 종료

    public void postStudents(String json) throws IOException{
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(300000)
                .setConnectionRequestTimeout(300000)
                .setSocketTimeout(300000)
                .build();

        try (CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()){
            HttpPost httpPost = new HttpPost(svrIP); // POST 요청 + 요청주소 설정
            httpPost.setHeader("Accept", "*/*"); // 헤더 세팅
            httpPost.setHeader("Content-type", "application/json; charset=UTF-8");
            httpPost.setHeader("CLIENT-TYPE", "CLIENT");
            httpPost.setHeader("DIVA-AUTH", "IVX-1234567890");

            // RequestBody 세팅
            /*String json = "{\r\n" +
                    "  \"grpType\": \"C-GRP\",\r\n" +
                    "  \"grpName\": \"Apache-POSTTest09\",\r\n" +
                    "  \"grpParentId\": \"25\",\r\n" +
                    "  \"grpPlaceAddr\": \"POST_TEST\",\r\n" +
                    "  \"grpPlaceControlAreaCode\": \"POST_TEST\",\r\n" +
                    "  \"grpIndustrialSafetySetting\": \"1\",\r\n" +
                    "  \"grpIndustrialSafetySettingImage\": \"TestImage0010101010(GRP)\"\r\n" +
                    "}";*/
            StringEntity stringEntity = new StringEntity(json, "UTF-8");
            httpPost.setEntity(stringEntity);

            System.out.println("========================================= Executing request =========================================");
            System.out.println("Executing request " + httpPost.getRequestLine());

            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println("========================================= Response Body ====================================");
                System.out.println(body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }
        }
    }

    public void putStudents(String json) throws IOException {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .setSocketTimeout(300000)
                .build();

        try (CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {
            HttpPut httpPut = new HttpPut(svrIP);
            httpPut.setHeader("Accept", "*/*");
            httpPut.setHeader("Content-type", "application/json");
            httpPut.setHeader("CLIENT-TYPE", "CLIENT");
            httpPut.setHeader("DIVA-AUTH", "IVX-1234567890");

            /*String json = "{\r\n" +
                    "  \"studentName\": \"정충낭\",\r\n" +
                    "  \"changeName\": \"김한별\",\r\n" +
                    "  \"korean\": \"100\",\r\n" +
                    "  \"english\": \"100\",\r\n" +
                    "  \"math\": \"100\",\r\n" +
                    "  \"history\": \"100\",\r\n" +
                    "  \"science\": \"100\",\r\n" +
                    "}";*/
            StringEntity stringEntity = new StringEntity(json,"UTF-8");
            httpPut.setEntity(stringEntity);

            System.out.println("========================================= Executing request =========================================");
            System.out.println("Executing request " + httpPut.getRequestLine());

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Errored Response Status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpPut, responseHandler);
            System.out.println("==================================== Response Body ====================================");
            System.out.println(responseBody);
        }
    }

}
