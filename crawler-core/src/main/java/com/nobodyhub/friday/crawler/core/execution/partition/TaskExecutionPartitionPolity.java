package com.nobodyhub.friday.crawler.core.execution.partition;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import com.nobodyhub.friday.crawler.core.execution.TaskExecution;

import java.math.BigInteger;
import java.util.List;

/**
 * Policy to distribute the {@link Task#limit} to each {@link TaskExecution}
 *
 * @author Ryan
 */
public abstract class TaskExecutionPartitionPolity {
    /**
     * split according to different policy
     *
     * @param toBePartition total number of {@link Task#limit} to be partitioned
     * @return number of {@link Task#limit} for each partition
     */
    public abstract List<BigInteger> partition(BigInteger toBePartition);

    /**
     * partition based on number of partitions
     *
     * @param toBePartition
     * @param numPartitions number of partitions
     * @return
     */
    protected List<BigInteger> partition(BigInteger toBePartition, int numPartitions) {
        List<BigInteger> results = Lists.newArrayList();
        BigInteger sumBeforeLast = BigInteger.ZERO;
        for (int i = 0; i < numPartitions - 1; i++) {
            BigInteger part = toBePartition.divide(BigInteger.valueOf(numPartitions));
            sumBeforeLast = sumBeforeLast.add(part);
            results.add(part);
        }
        results.add(toBePartition.subtract(sumBeforeLast));
        return results;
    }

    /**
     * partiton based on give weights
     *
     * @param toBePartition
     * @param weights       weight for each partition
     * @return
     */
    public List<BigInteger> partition(BigInteger toBePartition, List<BigInteger> weights) {
        List<BigInteger> results = Lists.newArrayList();
        BigInteger sumBeforeLast = BigInteger.ZERO;
        BigInteger total = weights.stream().reduce(BigInteger.ZERO, BigInteger::add);
        for (int i = 0, length = weights.size(); i < length - 1; i++) {
            BigInteger partition = weights.get(i);
            BigInteger part = toBePartition.multiply(partition).divide(total);
            sumBeforeLast = sumBeforeLast.add(part);
            results.add(part);
        }
        results.add(toBePartition.subtract(sumBeforeLast));
        return results;
    }
}
