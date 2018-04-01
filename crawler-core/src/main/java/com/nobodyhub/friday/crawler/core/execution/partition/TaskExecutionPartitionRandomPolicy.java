package com.nobodyhub.friday.crawler.core.execution.partition;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

/**
 * split the {@link Task#limit} based on random weights
 *
 * @author Ryan
 */
@RequiredArgsConstructor
public class TaskExecutionPartitionRandomPolicy extends TaskExecutionPartitionPolity {
    /**
     * number of partitions
     */
    protected final int numPartitons;

    @Override
    public List<BigInteger> partition(BigInteger toBePartition) {
        //generate random weights
        List<BigInteger> weights = Lists.newArrayList();
        Random random = new Random();
        for (int idx = 0; idx < numPartitons; idx++) {
            weights.add(idx, BigInteger.valueOf(random.nextInt(10) + 1L));
        }
        return partition(toBePartition, weights);
    }
}
