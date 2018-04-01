package com.nobodyhub.friday.crawler.core.execution.partition;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class TaskExecutionPartitionRandomPolicyTest {
    @Test
    public void testPartiton() {
        TaskExecutionPartitionPolity policy = new TaskExecutionPartitionRandomPolicy(11);
        BigInteger toBePartiton = BigInteger.valueOf(100);
        List<BigInteger> executions = policy.partition(toBePartiton);
        assertEquals(11, executions.size());
        assertEquals(toBePartiton, executions.stream().reduce(BigInteger.ZERO, BigInteger::add));
    }
}