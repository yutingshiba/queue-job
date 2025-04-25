import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JobService service = new JobService();
        JobWorker worker = new JobWorker(service);
        new Thread(worker).start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option：");
            System.out.println("1. Submit job");
            System.out.println("2. Check job's status");
            System.out.println("3. End");
            System.out.print("> ");
            String input = scanner.nextLine();

            if ("1".equals(input)) {
                UUID jobId = service.submitJob();
                System.out.println("Submit succeeded，Job ID: " + jobId);
            } else if ("2".equals(input)) {
                System.out.print("Please enter Job ID: ");
                String idStr = scanner.nextLine();
                try {
                    UUID id = UUID.fromString(idStr);
                    JobResult result = service.getJob(id);
                    if (result == null) {
                        System.out.println("Cannot find this job");
                    } else {
                        System.out.println("Status: " + result.status + ", Result: " + result.result);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid ID format");
                }
            } else if ("3".equals(input)) {
                System.out.println("Bye!");
                System.exit(0);
            }
        }
    }
}
