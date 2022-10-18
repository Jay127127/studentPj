package study;

import java.io.IOException;
import java.util.Scanner;

public class request01 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ApiCall apiCall = new ApiCall();


        while (true){
            System.out.println("\n어떤 요청을 보내시겠습니까?");
            String select = scanner.next();
            switch (select){

                case "get" :
                    System.out.println("get 요청을 불러옵니다.");
                    apiCall.setSvrIp("http://localhost:8888/student/search");
                    apiCall.getStudents();
                    break;

                case "post" :
                    System.out.println("자동 생성할 학생의 수를 입력하세요");
                    int createStudentNo = scanner.nextInt();

                    String postJson =
                            "{\r\n" +
                                    "  \"createStudentNo\": \""+createStudentNo+"\"\r\n" +
                                    "}";
                    System.out.println(postJson);

                    apiCall.setSvrIp("http://localhost:8888/student/insert");
                    apiCall.postStudents(postJson); // data/group/add
                    break;

                case "put" :
                    System.out.println("업데이트 할 학생을 입력하세요.");
                    String targetStudent = scanner.next();
                    System.out.println("국어 점수를 입력하세요.");
                    int korean = scanner.nextInt();
                    System.out.println("영어 점수를 입력하세요.");
                    int english = scanner.nextInt();
                    System.out.println("수학 점수를 입력하세요.");
                    int math = scanner.nextInt();
                    System.out.println("사회 점수를 입력하세요.");
                    int history = scanner.nextInt();
                    System.out.println("과학 점수를 입력하세요.");
                    int science = scanner.nextInt();
                    int total = korean + english + math + history + science;
                    double average = (double) total/5;

                    String putJson =
                            "{\r\n" +
                                    "  \"targetStudent\": \""+targetStudent+"\",\r\n" +
                                    "  \"korean\": \""+korean+"\",\r\n" +
                                    "  \"english\": \""+english+"\",\r\n" +
                                    "  \"math\": \""+math+"\",\r\n" +
                                    "  \"history\": \""+history+"\",\r\n" +
                                    "  \"science\": \""+science+"\",\r\n" +
                                    "  \"total\": \""+total+"\",\r\n" +
                                    "  \"average\": \""+average+"\"\r\n" +
                                    "}";
                    apiCall.setSvrIp("http://localhost:8888/student/update");
                    apiCall.putStudents(putJson); // data/group/update
                    break;
            }
        }
    }
}
