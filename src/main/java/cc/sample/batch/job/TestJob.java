package cc.sample.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import cc.sample.batch.common.helper.BatchChunkListener;
import cc.sample.batch.common.reader.QueueItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Configuration
public class TestJob {
    private final JobBuilderFactory  jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchChunkListener batchChunkListener;

    private final int CHUNK_SIZE = 1000;
    
    @Bean
    public Job sampleJob() {
        return jobBuilderFactory.get("sampleJob")
                                .preventRestart()
                                .listener(jobExecutionListener())
                                .start(sampleStep())
                                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionListener() {
        JobExecutionListener jobExecutionListener = new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("---- before");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info("---- after");
            }
        };
        return jobExecutionListener;
    }


    @Bean
    public Step sampleStep() {
        return stepBuilderFactory.get("sampleStep")
                                 .<Integer, Integer>chunk(1000)
                                 .reader(itemReader())
                                 .processor(itemProcessor())
                                 .writer(itemWriter())
                                 .listener(batchChunkListener)
                                 .build();
    }

    @Bean
    @StepScope
    public QueueItemReader<Integer> itemReader() {
        return new QueueItemReader<>(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Bean
    @StepScope
    public ItemProcessor<Integer, Integer> itemProcessor() {
        return item -> item.intValue();
    }

    @Bean
    @StepScope
    public ItemWriter<Integer> itemWriter() {
        return items -> items.stream()
                             .collect(Collectors.toList());
    }
}
