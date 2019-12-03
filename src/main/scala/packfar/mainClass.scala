package packfar

import org.apache.spark.sql.{DataFrame, Dataset, Row}

object mainClass {
  def main(args: Array[String]): Unit = {
    /*
    local mode
    val path_reciver_streams = "/home/farid/Bureau/structure_Streaming/input/"
    val path_storage_process_recived_streams = "/home/farid/Bureau/structure_Streaming/output/"
     */
    //------------------------------------------------------------------------------------------------------------------
    /*
    cluster mode (spark submit):
    you have to give 2 paths as explecitely named args(0) and args(1) when lunch  the application:
     */
    //------------------------------------------------------------------------------------------------------------------
    val path_reciver_streams = args(0)
    val path_storage_process_recived_streams = args(1)

    spark.sparkContext.setLogLevel("warn")
    val df_capture_stream: DataFrame = spark.readStream.schema(shemas_data)
      .option("maxFilesPerTrigger", 1)
      .csv(path_reciver_streams)

    val ds_process_capture_stream1: Dataset[Row] = df_capture_stream.where("Quantity < 0")
    val ds_process_capture_stream2: Dataset[Row] = df_capture_stream.where("Quantity > 0")f

    ds_process_capture_stream1.writeStream.format("csv")
      .option("checkpointLocation", path_storage_process_recived_streams + "checkpoint_Location1")
      .option("path", path_storage_process_recived_streams + "negative")
      .start()

    ds_process_capture_stream2.writeStream.format("csv")
      .option("checkpointLocation", path_storage_process_recived_streams + "checkpoint_Location2")
      .option("path", path_storage_process_recived_streams + "positive")
      .start().awaitTermination()
    spark.close()
    /*
    lunch app witn comand:
    spark-submit --class packfar.mainClass --master yarn streaming_app_2.11-0.1.jar /user/aarid/input/ /user/aarid/output/
     */

  }

}

