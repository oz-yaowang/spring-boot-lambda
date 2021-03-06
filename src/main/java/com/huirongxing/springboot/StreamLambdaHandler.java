package com.huirongxing.springboot;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.Timer;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import com.huirongxing.springboot.filter.CognitoIdentityFilter;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/13/2018 11:29
 */
public class StreamLambdaHandler implements RequestStreamHandler {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------


  @Override public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
      throws IOException {
    handler.proxyStream(inputStream, outputStream, context);

    // just in case it wasn't closed by the mapper
    outputStream.close();
  }
  private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

  static {
    try {
      handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
      // we use the onStartup method of the handler to register our custom filter
      handler.onStartup(servletContext -> {
        FilterRegistration.Dynamic registration = servletContext.addFilter("CognitoIdentityFilter",
            CognitoIdentityFilter.class);
        registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
      });
    } catch (ContainerInitializationException e) {
      // if we fail here. We re-throw the exception to force another cold start
      e.printStackTrace();
      throw new RuntimeException("Could not initialize Spring Boot application", e);
    }
  }

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new StreamLambdaHandler object.
   */
  public StreamLambdaHandler() {
    // we enable the timer for debugging. This SHOULD NOT be enabled in production.
    Timer.enable();
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------


} // end class StreamLambdaHandler
