package cc.sample.batch.common.writer;

import org.springframework.batch.item.database.JdbcBatchItemWriter;

import java.util.ArrayList;
import java.util.List;

public class JdbcItemListWriter<T> extends JdbcBatchItemWriter<List<T>> {
    private JdbcBatchItemWriter<T> jdbcBatchItemWriter;

    public JdbcItemListWriter(JdbcBatchItemWriter<T> jdbcBatchItemWriter) {
        this.jdbcBatchItemWriter = jdbcBatchItemWriter;
    }

    @Override
    public void write(List<? extends List<T>> items) throws Exception {
        List<T> mergeList = new ArrayList<>();

        for (List<T> t : items) {
            mergeList.addAll(t);
        }

        this.jdbcBatchItemWriter.write(mergeList);
    }
}
