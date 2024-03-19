import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class code {
    // 파일에서 지정된 라인을 출력하는 메서드
    public static void printLine(File file, int line) throws IOException {
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            for (int i = 0; i < line; i++) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                } else {
                    break; // 파일이 라인 수보다 작을 경우 처리
                }
            }
            if (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    // 다음 라인을 출력하는 메서드
    public static void printNextLine(File file) throws IOException {
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    // 파일의 총 라인 수를 계산하는 메서드
    public static int linesOfFile(File file) throws IOException {
        int lines = 0;
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
        }
        return lines;
    }

    // 문제의 순서를 섞는 메서드
    public static int[] mixedOrderOfProblem(int num) {
        int[] resultArr = new int[num];
        for (int i = 0; i < num; i++) {
            resultArr[i] = i;
        }
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            int swapNum = rand.nextInt(num);
            int temp = resultArr[i];
            resultArr[i] = resultArr[swapNum];
            resultArr[swapNum] = temp;
        }
        return resultArr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("시험문제를 무작위.\n");
        System.out.println("문제를 제출할 파일 이름을 입력하세요.\n예시문제.txt\n");
        String fileName = scanner.nextLine();

        File file = new File(fileName);
        try {
            int a=0;
            int numberOfProblem = linesOfFile(file) / 3;
            int[] orderOfProblem = mixedOrderOfProblem(numberOfProblem);

            // 문제 출력 및 정답 출력
            for (int i = 0; i < numberOfProblem; i++) {
                printLine(file, orderOfProblem[i] * 3);
                printNextLine(file);
                System.out.println("\n");
                Scanner in = new Scanner(System.in);

                in.nextLine();

                printLine(file, (orderOfProblem[i]*3)+1 );
                
                
                a++;
            }
            System.out.println(a);

            System.out.println("모든 문제를 출력");
            scanner.nextLine();
        } catch (IOException e) {
            System.out.println("파일 안보임.");
        } finally {
            scanner.close();
        }
    }
}

