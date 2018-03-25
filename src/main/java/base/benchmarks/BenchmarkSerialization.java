package base.benchmarks;

import base.comparing.TestClass;
import base.objects.ComplexObject;
import base.objects.SimpleObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 4)
@Measurement(iterations = 4)
@Fork(value = 2, jvmArgsAppend = "-server")
@BenchmarkMode(Mode.SingleShotTime)
@State(Scope.Benchmark)
public class BenchmarkSerialization {
    private ComplexObject complexObject1;

    @Setup
    public void setUp() {
        SimpleObject simpleObject1 = new SimpleObject(1, "simpleObject1", (byte) 1);
        SimpleObject simpleObject2 = new SimpleObject(2, "simpleObject2", (byte) 2);
        SimpleObject simpleObject3 = new SimpleObject(3, "simpleObject3", (byte) 3);
        List<SimpleObject> simpleObjects = Arrays.asList(simpleObject1, simpleObject2, simpleObject3);

        complexObject1 = new ComplexObject(1, simpleObjects);
    }

    @Benchmark
    public void testSmile() throws JsonProcessingException {
        TestClass.usingSmile(complexObject1);
    }

    @Benchmark
    public void testKryo() throws FileNotFoundException {
        TestClass.usingKryo(complexObject1);
    }

    @Benchmark
    public void testFST() throws FileNotFoundException {
        TestClass.usingFST(complexObject1);
    }

    @Benchmark
    public void testJavaSerialization() throws IOException {
        TestClass.usingJavaSerialization(complexObject1);
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*")
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .resultFormat(ResultFormatType.TEXT)
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opts).run();
    }
}
