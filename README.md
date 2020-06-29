# beam_pipeline

![Architecture](https://github.com/anushirahatti/beam_pipeline/blob/master/simple-data-pipeline.jpg)

##### Deploying a Pipeline on Cloud Dataflow
mvn compile exec:java -Dexec.mainClass=org.anir.beam_pipeline.StarterPipeline -Dexec.args="--runner=DataflowRunner --project=$PROJECT --region=us-west1 --tempLocation=$BUCKET/temp --output=$BUCKET/beam_pipeline_output"
