/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * A starter example for writing Beam programs.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 *   --project=<YOUR_PROJECT_ID>
 *   --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 *   --runner=DataflowRunner
 */
public class StarterPipeline {
	
  private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);
  
  public interface StarterPipelineOptions extends PipelineOptions {

	    /**
	     * By default, this example reads from a public dataset containing the text of
	     * King Lear. Set this option to choose a different input file or glob.
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
