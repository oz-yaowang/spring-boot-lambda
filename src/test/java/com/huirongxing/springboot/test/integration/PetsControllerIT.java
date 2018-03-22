package com.huirongxing.springboot.test.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.BDDMockito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;

import com.huirongxing.springboot.config.MvcConfig;
import com.huirongxing.springboot.remoteservice.RemoteService;
import com.huirongxing.springboot.test.config.IntegrationTestConfig;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/19/2018 16:48
 */

@ContextConfiguration(
  initializers = ConfigFileApplicationContextInitializer.class,
  classes      = { MvcConfig.class, IntegrationTestConfig.class }
)
@ExtendWith(SpringExtension.class)
@TestExecutionListeners(
  inheritListeners = false,
  listeners        = { DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class }
)
@WebAppConfiguration public class PetsControllerIT {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static final Logger LOGGER = LoggerFactory.getLogger(PetsControllerIT.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

  @Autowired private MockLambdaContext lambdaContext;

  @MockBean private RemoteService remoteService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * beforeAll.
   */

  /**
   * testEnvData.
   */
  @Test public void testEnvData() {
    AwsProxyRequest request = new AwsProxyRequestBuilder("/envdata", "GET").build();

    AwsProxyResponse response = handler.proxy(request, lambdaContext);
    Assertions.assertEquals(response.getStatusCode(), 200);
    Assertions.assertNotNull(response.getBody());
    LOGGER.info(response.getBody());
    // then
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testMockRemote.
   */
  @Test public void testMockRemote() {
    BDDMockito.given(this.remoteService.call()).willReturn("controller unit test");

    AwsProxyRequest  request  = new AwsProxyRequestBuilder("/controller/test", "GET").build();
    AwsProxyResponse response = handler.proxy(request, lambdaContext);
    LOGGER.info(response.getBody());
    Assertions.assertEquals("controller unit test", response.getBody());
  }
} // end class PetsControllerIT
