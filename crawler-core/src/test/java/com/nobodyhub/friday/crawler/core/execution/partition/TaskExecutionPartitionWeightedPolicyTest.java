package com.nobodyhub.friday.crawler.core.execution.partition;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class TaskExecutionPartitionWeightedPolicyTest {
    @Test
    public void testPartiton() {
        List<BigInteger> weights = Lists.newArrayList(
                BigInteger.ZERO,
                BigInteger.ONE,
                BigInteger.TEN,
                BigInteger.valueOf(22),
                BigInteger.valueOf(11)
        );
        TaskExecutionPartitionPolity policy = new TaskExecutionPartitionWeightedPolicy(weights);
        BigInteger toBePartiton = BigInteger.valueOf(100);
        List<BigInteger> executions = policy.partition(toBePartiton);
        assertEquals(weights.size(), executions.size());
        assertEquals(BigInteger.valueOf(0), executions.get(0));
        assertEquals(BigInteger.valueOf(2), executions.get(1));
        assertEquals(BigInteger.valueOf(22), executions.get(2));
        assertEquals(BigInteger.valueOf(50), executions.get(3));
        assertEquals(BigInteger.valueOf(26), executions.get(4));
        assertEquals(toBePartiton, executions.stream().reduce(BigInteger.ZERO, BigInteger::add));
    }
}