import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class JobWorker implements Runnable {
    private final JobService jobService;

    public JobWorker(JobService service) {
        this.jobService = service;
    }

    @Override
    public void run() {
        while (true) {
            try {
                UUID jobId = jobService.queue.take();
                jobService.updateJob(jobId, JobStatus.PROCESSING, null);

                Thread.sleep(2000 + ThreadLocalRandom.current().nextInt(2000));

                jobService.updateJob(jobId, JobStatus.DONE, "處理完成 ✅");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}