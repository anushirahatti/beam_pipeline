# Serverless ETL Pipeline Using Apache Beam & Cloud Dataflow

![Architecture](https://github.com/anushirahatti/beam_pipeline/blob/master/apache-beam-architecture-distributed-processing-xenonstack.jpg)

![Architecture](https://github.com/anushirahatti/beam_pipeline/blob/master/simple-data-pipeline.jpg)

##### A Simple ETL Pipeline Implementation using Apache Beam
- Extract data from Google Cloud Storage (GCS)
- Transform the input data
- Load transformed data into Google Cloud Storage (GCS)

##### Deploying a Pipeline on Cloud Dataflow
mvn compile exec:java -Dexec.mainClass=org.anir.beam_pipeline.StarterPipeline -Dexec.args="--runner=DataflowRunner --project=$PROJECT --region=us-west1 --tempLocation=$BUCKET/temp --output=$BUCKET/beam_pipeline_output"
