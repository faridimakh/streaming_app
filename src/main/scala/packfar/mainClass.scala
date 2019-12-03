package packfar

import org.apache.spark.sql.{DataFrame, Dataset, Row}

object mainClass {
  def main(args: Array[String]): Unit = {

    val path_receiver_streams = args(0)
    val path_storage_process_received_streams = args(1)

    spark.sparkContext.setLogLevel("warn")
    val df_capture_stream: DataFrame = spark.readStream.schema(shemas_data_stream)
      .option("maxFilesPerTrigger", 1)
      .csv(path_receiver_streams)

    val ds_process_capture_stream1: Dataset[Row] = df_capture_stream.where("Quantity < 0")
    val ds_process_capture_stream2: Dataset[Row] = df_capture_stream.where("Quantity > 0")

    ds_process_capture_stream1.writeStream.format("csv")
      .option("checkpointLocation", path_storage_process_received_streams + "checkpoint_Location1")
      .option("path", path_storage_process_received_streams + "negative")
      .start()

    ds_process_capture_stream2.writeStream.format("csv")
      .option("checkpointLocation", path_storage_process_received_streams + "checkpoint_Location2")
      .option("path", path_storage_process_received_streams + "positive")
      .start().awaitTermination()
    spark.close()


  }

}

