# beam_pipeline

##### Deploying a Pipeline on Cloud Dataflow
mvn compile exec:java -Dexec.mainClass=org.anir.beam_pipeline.StarterPipeline -Dexec.args="--runner=DataflowRunner --project=$PROJECT --region=us-west1 --tempLocation=$BUCKET/temp --output=$BUCKET/beam_pipeline_output"
