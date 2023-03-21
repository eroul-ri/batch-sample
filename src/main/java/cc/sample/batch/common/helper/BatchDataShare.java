package cc.sample.batch.common.helper;

import com.google.common.collect.Maps;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BatchDataShare<T> {
    private Map<String, T> batchDataMap;

    public BatchDataShare() {
        this.batchDataMap = Maps.newConcurrentMap();
    }

    public void putData(String key, T data) {
        if (batchDataMap == null) {
            return;
        }
        batchDataMap.put(key, data);
    }

    public T getData(String key) {
        if (batchDataMap == null) {
            return null;
        }
        return batchDataMap.get(key);
    }

    public int getSize() {
        if (this.batchDataMap == null) {
            return 0;
        }

        return batchDataMap.size();
    }
}
