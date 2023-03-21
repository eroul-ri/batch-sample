package cc.sample.batch.common.utils;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SampleJobParameter {
    @Value("${sample.batch.version}")
    private String version;
    private String date;

    public JobParameters getParameters() {
        return new JobParametersBuilder().addString("date", setDate())
                                         .addString("version", getVersion())
                                         .toJobParameters();
    }

    public String setDate() {
        this.date = LocalDateTime.now()
                                 .toString();
        return this.date;
    }

    public String getVersion() {
        return this.version;
    }
}
