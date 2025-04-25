public class JobResult {
    public JobStatus status;
    public String result;

    public JobResult(JobStatus status, String result) {
        this.status = status;
        this.result = result;
    }
}