package com.huirongxing.springboot.test.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.context.ConfigurableWebApplicationContext;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/19/2018 16:46
 */
@ComponentScan("com.huirongxing.springboot")
@Configuration public class IntegrationTestConfig {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private ConfigurableWebApplicationContext applicationContext;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * lambdaContext.
   *
   * @return  MockLambdaContext
   */
  @Bean public MockLambdaContext lambdaContext() {
    return new MockLambdaContext();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * springLambdaContainerHandler.
   *
   * @return  SpringLambdaContainerHandler
   *
   * @throws  ContainerInitializationException  exception
   */
  @Bean public SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> springLambdaContainerHandler()
    throws ContainerInitializationException {
// applicationContext.getEnvironment().getProperty("");
// applicationContext.addProtocolResolver();
// applicationContext.getConfigLocations();
// applicationContext.setConfigLocation("classpath:application.yml");

    SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler = SpringLambdaContainerHandler
      .getAwsProxyHandler(applicationContext);
    handler.setRefreshContext(false);
// handler.activateSpringProfiles("dev");

    return handler;
  }
} // end class IntegrationTestConfig
