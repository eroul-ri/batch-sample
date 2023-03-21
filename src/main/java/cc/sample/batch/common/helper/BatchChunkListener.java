package cc.sample.batch.common.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.stereotype.Component;

@Component
public class BatchChunkListener implements ChunkListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void beforeChunk(ChunkContext context) {
        StepContext   stepContext   = context.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        // 읽은 횟수
        logger.info("##### CHUNK running job : [{}], CHUNK running step : [{}], read : {}", stepContext.getJobName(), stepContext.getStepName(),
                    stepExecution.getReadCount());
    }

    @Override
    public void afterChunk(ChunkContext context) {
        StepContext   stepContext   = context.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        // commit
        logger.info("##### CHUNK after job : [{}], CHUNK after step : [{}], commit : {}", stepContext.getJobName(), stepContext.getStepName(),
                    stepExecution.getCommitCount());
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        StepContext   stepContext   = context.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        // 롤백횟수
        logger.error("##### CHUNK error job : [{}], CHUNK error step : [{}], rollBack : {}", stepContext.getJobName(), stepContext.getStepName(),
                     stepExecution.getRollbackCount());
    }
}
