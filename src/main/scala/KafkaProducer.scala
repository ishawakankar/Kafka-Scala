import java.util.Properties
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

object KafkaProducer {
  def main(args: Array[String]): Unit = {

    val conf = ConfigFactory.load();
    val envProps = conf.getConfig(args(0))

    val props = new Properties();

    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, envProps.getString("bootstrap.server"));
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducer");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

    val producer = new KafkaProducer[String, String](props)

    val data = new ProducerRecord[String, String]("Kafka-Testing", args(1), args(2));

    producer.send(data);
    producer.close();
  }
}
