package study;

import java.io.IOException;
import java.util.Scanner;

public class study1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ApacheHTTPCall apacheHTTPCall = new ApacheHTTPCall();

        while (true){
            System.out.println("\n어떤 요청을 보내시겠습니까?");
            String select = scanner.next();
            switch (select){

                case "get" :
                    System.out.println("get 요청을 불러옵니다.");
                    apacheHTTPCall.setURL("http://localhost:8090/api/pom/v1/stat/history/task?toDate=2022-10-17T23:59:59&fromDate=2022-10-17T00:00:00");
                    apacheHTTPCall.getRequest(); // stat/history/task
                    break;

                case "post" :
                    System.out.println("grpType 을 입력하세요.");
                    String grpType = scanner.next();
                    System.out.println("grpName 을 입력하세요.");
                    String grpName = scanner.next();
                    System.out.println("grpParentId 을 입력하세요.");
                    String grpParentId = scanner.next();
                    System.out.println("grpPlaceAddr 을 입력하세요.");
                    String grpPlaceAddr = scanner.next();
                    System.out.println("grpPlaceControlAreaCode 을 입력하세요.");
                    String grpPlaceControlAreaCode = scanner.next();
                    System.out.println("grpIndustrialSafetySetting 을 입력하세요.");
                    String grpIndustrialSafetySetting = scanner.next();
                    System.out.println("grpIndustrialSafetySettingImage 을 입력하세요.");
                    String grpIndustrialSafetySettingImage = scanner.next();

                    String postJson =
                            "{\r\n" +
                                    "  \"grpType\": \""+grpType+"\",\r\n" +
                                    "  \"grpName\": \""+grpName+"\",\r\n" +
                                    "  \"grpParentId\": \""+grpParentId+"\",\r\n" +
                                    "  \"grpPlaceAddr\": \""+grpPlaceAddr+"\",\r\n" +
                                    "  \"grpPlaceControlAreaCode\": \""+grpPlaceControlAreaCode+"\",\r\n" +
                                    "  \"grpIndustrialSafetySetting\": \""+grpIndustrialSafetySetting+"\",\r\n" +
                                    "  \"grpIndustrialSafetySettingImage\": \""+grpIndustrialSafetySettingImage+"\"\r\n" +
                                    "}";
                    System.out.println(postJson);
                    apacheHTTPCall.postRequest(postJson); // data/group/add
                    break;

                case "put" :
                    System.out.println("grpType 을 입력하세요.");
                    String grpType2 = scanner.next();
                    System.out.println("grpName 을 입력하세요.");
                    String grpName2 = scanner.next();
                    System.out.println("grpPk 을 입력하세요.");
                    String grpPk = scanner.next();
                    System.out.println("grpPlaceAddr 을 입력하세요.");
                    String grpPlaceAddr2 = scanner.next();
                    System.out.println("grpPlaceControlAreaCode 을 입력하세요.");
                    String grpPlaceControlAreaCode2 = scanner.next();
                    System.out.println("grpServiceFk 을 입력하세요.");
                    String grpServiceFk = scanner.next();
                    System.out.println("grpIndustrialSafetySetting 을 입력하세요.");
                    String grpIndustrialSafetySetting2 = scanner.next();
                    System.out.println("grpIndustrialSafetySettingImage 을 입력하세요.");
                    String grpIndustrialSafetySettingImage2 = scanner.next();

                    String putJson =
                            "{\r\n" +
                                    "  \"grpType\": \""+grpType2+"\",\r\n" +
                                    "  \"grpName\": \""+grpName2+"\",\r\n" +
                                    "  \"grpPk\": \""+grpPk+"\",\r\n" +
                                    "  \"grpPlaceAddr\": \""+grpPlaceAddr2+"\",\r\n" +
                                    "  \"grpPlaceControlAreaCode\": \""+grpPlaceControlAreaCode2+"\",\r\n" +
                                    "  \"grpServiceFk\": \""+grpServiceFk+"\",\r\n" +
                                    "  \"grpIndustrialSafetySetting\": \""+grpIndustrialSafetySetting2+"\",\r\n" +
                                    "  \"grpIndustrialSafetySettingImage\": \""+grpIndustrialSafetySettingImage2+"\"\r\n" +
                                    "}";
                    System.out.println(putJson);
                    apacheHTTPCall.putRequest(putJson); // data/group/update
                    break;
            }
            System.out.println("finish?");
            String wanna = scanner.next();
            if(wanna.equals("yes")) break;
        }
    }
}
