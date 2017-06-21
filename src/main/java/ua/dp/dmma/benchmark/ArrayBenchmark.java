package ua.dp.dmma.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import ua.dp.dmma.AssociativeArray;

import java.util.concurrent.TimeUnit;

/**
 * @author dmma
 */
public class ArrayBenchmark
{
    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testAssociativeArray()
    {
        new AssociativeArray().getNMaxValuesFromArray(10, 100);
    }
}
