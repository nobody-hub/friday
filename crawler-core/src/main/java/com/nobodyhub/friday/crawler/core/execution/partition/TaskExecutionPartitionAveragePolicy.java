package com.nobodyhub.friday.crawler.core.execution.partition;

import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.List;

/**
 * split the {@link Task#limit} evenly
 *
 * @author Ryan
 */
@RequiredArgsConstructor
public class TaskExecutionPartitionAveragePolicy extends TaskExecutionPartitionPolity {
    /**
     * number of partitions
     */
    protected final int numPartitons;

    @Override
    public List<BigInteger> partition(BigInteger toBePartition) {
        return partition(toBePartition, numPartitons);
    }
}
