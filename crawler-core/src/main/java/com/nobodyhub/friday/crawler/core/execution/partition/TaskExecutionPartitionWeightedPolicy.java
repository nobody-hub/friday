package com.nobodyhub.friday.crawler.core.execution.partition;

import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.List;

/**
 * split the {@link Task#limit} according to the {@link this#weights}
 * <p>
 * in order to match the total number of {@code toBePartition}, the last weight will be ignored.
 *
 * @author Ryan
 */
@RequiredArgsConstructor
public class TaskExecutionPartitionWeightedPolicy extends TaskExecutionPartitionPolity {
    /**
     * weights of each partitions, list size is the number of partitions
     */
    protected final List<BigInteger> weights;

    @Override
    public List<BigInteger> partition(BigInteger toBePartition) {
        return partition(toBePartition, weights);
    }
}
