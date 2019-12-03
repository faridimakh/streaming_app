spark structured streaming example:
------------------------------------------------------------------------------------------
for lunching this application ,the following steps must be followed:
1-package the application:
  sbt clean package

2-create two empty folders in hdfs (or locally):
  a-input (for path_reciver_streams value)
  b-output (for path_storage_process_recived_streams  value)

3-send tu jar result with spark-submit:
  spark-submit --class packfar.mainClass --master yarn streaming_app_2.11-0.1.jar /user/aarid/input/ /user/aarid/output/
4-now u push your data with drag and drop (or with command line) in the input folder
-------------------------------------------------------------------------------------------

**result: your data is separated on real time(as well as yoiu push data on input repository ) in to parts:
negative folder: contain data which have negative values for columns
positive folder: contain data which have positive values for columns
