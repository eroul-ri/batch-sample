package cc.sample.batch.common.writer;

import org.springframework.batch.item.database.JpaItemWriter;

import java.util.ArrayList;
import java.util.List;

public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {
    private JpaItemWriter<T> jpaItemWriter;

    public JpaItemListWriter(JpaItemWriter<T> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(List<? extends List<T>> items) {
        List<T> mergeList = new ArrayList<>();

        for (List<T> list : items) {
            mergeList.addAll(list);
        }

        jpaItemWriter.write(mergeList);
    }
}
