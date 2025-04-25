import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JobService service = new JobService();
        JobWorker worker = new JobWorker(service);
        new Thread(worker).start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n選擇操作：");
            System.out.println("1. 提交任務");
            System.out.println("2. 查詢任務狀態");
            System.out.println("3. 結束");
            System.out.print("> ");
            String input = scanner.nextLine();

            if ("1".equals(input)) {
                UUID jobId = service.submitJob();
                System.out.println("提交成功，Job ID: " + jobId);
            } else if ("2".equals(input)) {
                System.out.print("請輸入 Job ID: ");
                String idStr = scanner.nextLine();
                try {
                    UUID id = UUID.fromString(idStr);
                    JobResult result = service.getJob(id);
                    if (result == null) {
                        System.out.println("找不到這個任務");
                    } else {
                        System.out.println("狀態: " + result.status + ", 結果: " + result.result);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("ID 格式錯誤");
                }
            } else if ("3".equals(input)) {
                System.out.println("Bye!");
                System.exit(0);
            }
        }
    }
}