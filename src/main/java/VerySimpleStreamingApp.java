import org.apache.log4j.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.*;

public class VerySimpleStreamingApp {
    private static final String HOST = "localhost";
    private static final int PORT = 9999;

    public static void main(String[] args) throws InterruptedException {


        // Configurer et initialiser le SparkStreamingContext
        SparkConf conf = new SparkConf()
                .setMaster("local[*]")
                .setAppName("VerySimpleStreamingApp");

        JavaStreamingContext streamingContext =
                new JavaStreamingContext(conf, Durations.seconds(5));



        // reception des données en temps réel de la source
        JavaReceiverInputDStream<String> lines = streamingContext.socketTextStream(HOST, PORT);


        // impression des lignes en sortie
        lines.print();

        // Execute le job spark
        streamingContext.start();
        streamingContext.awaitTermination();
    }
}
