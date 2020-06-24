
package org.anir.beam_pipeline;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.Validation.Required;
import org.apache.beam.sdk.transforms.Regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StarterPipeline {
	
  private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);
  
  public interface StarterPipelineOptions extends PipelineOptions {

	    /**
	     * Set this required option to specify where to read the input.
	     */
	    @Description("Path of the file to read from")
	    @Default.String("gs://dataflow-cloud-academy-281113/beam_info.txt")
	    String getInputFile();
	    void setInputFile(String value);

	    /**
	     * Set this required option to specify where to write the output.
	     */
	    @Description("Path of the file to write to")
	    @Required
	    String getOutput();
	    void setOutput(String value);
  }

  public static void main(String[] args) {
	  
	// Create the pipeline.
	StarterPipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(StarterPipelineOptions.class);
    Pipeline p = Pipeline.create(options);
    
    
    // Reading from an External Source.
    // Create the PCollection 'lines' by applying a 'Read' transform.
    p.apply("ReadMyFile", TextIO.read().from(options.getInputFile()))
     .apply("ExtractWords", Regex.split("\\s+"))
     .apply("WriteMyFile", TextIO.write().to(options.getOutput()));

    p.run().waitUntilFinish();
  }
}
