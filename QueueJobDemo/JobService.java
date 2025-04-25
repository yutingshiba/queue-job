import java.util.*;
import java.util.concurrent.*;

public class JobService {
    private final Map<UUID, JobResult> jobMap = new ConcurrentHashMap<>();
    public final BlockingQueue<UUID> queue = new LinkedBlockingQueue<>();

    public UUID submitJob() {
        UUID id = UUID.randomUUID();
        jobMap.put(id, new JobResult(JobStatus.PENDING, null));
        queue.offer(id);
        return id;
    }

    public JobResult getJob(UUID id) {
        return jobMap.get(id);
    }

    public void updateJob(UUID id, JobStatus status, String result) {
        JobResult job = jobMap.get(id);
        if (job != null) {
            job.status = status;
            job.result = result;
        }
    }
}