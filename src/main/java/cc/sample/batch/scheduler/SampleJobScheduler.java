package cc.sample.batch.scheduler;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cc.sample.batch.common.utils.SampleJobParameter;
import cc.sample.batch.job.TestJob;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SampleJobScheduler {
    private final JobLauncher        jobLauncher;
    private final TestJob            testJob;
    private final SampleJobParameter sampleJobParameter;

    @Scheduled(cron = "30 * * * * *")
    public void runGolfClubJob() throws Exception {
        jobLauncher.run(testJob.sampleJob(), sampleJobParameter.getParameters());
    }
}
