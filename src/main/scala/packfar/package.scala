import org.apache.spark.sql
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructType}

package object packfar {
  /*
  local mode:
  final val spark = new sql.SparkSession.Builder().appName("stream_app").master("local[*]").getOrCreate()
  cluster mode:
   */
  final val spark = new sql.SparkSession.Builder().appName("stream_app").master("yarn").getOrCreate()
  val shemas_data_stream: StructType = new StructType()
    .add("InvoiceNo", StringType ,nullable = true)
    .add("StockCode", StringType ,nullable = true)
    .add("Description", StringType ,nullable = true)
    .add("Quantity", IntegerType ,nullable = true)
    .add("InvoiceDate", StringType ,nullable = true)
    .add("UnitPrice", DoubleType ,nullable = true)
    .add("CustomerID", IntegerType ,nullable = true)
    .add("Country", StringType ,nullable = true)


}
