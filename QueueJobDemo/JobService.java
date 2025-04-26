import java.util.*;
import java.util.concurrent.*;

public class JobService {
    private final Map<UUID, JobResult> jobMap = new ConcurrentHashMap<>();
    public final BlockingQueue<UUID> queue = new LinkedBlockingQueue<>();

    // submitJob
    // getJob
    // updateJob
}
