package com.nobodyhub.friday.crawler.core.execution.partition;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class TaskExecutionPartitionAveragePolicyTest {
    @Test
    public void testPartiton() {
        TaskExecutionPartitionPolity policy = new TaskExecutionPartitionAveragePolicy(3);
        BigInteger toBePartiton = BigInteger.valueOf(100);
        List<BigInteger> executions = policy.partition(toBePartiton);
        assertEquals(3, executions.size());
        assertEquals(BigInteger.valueOf(33L), executions.get(0));
        assertEquals(BigInteger.valueOf(33L), executions.get(1));
        assertEquals(BigInteger.valueOf(34L), executions.get(2));
        assertEquals(toBePartiton, executions.stream().reduce(BigInteger.ZERO, BigInteger::add));
    }
}