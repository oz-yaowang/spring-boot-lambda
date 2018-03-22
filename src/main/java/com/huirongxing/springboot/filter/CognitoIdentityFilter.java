package com.huirongxing.springboot.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.serverless.proxy.RequestReader;
import com.amazonaws.serverless.proxy.model.ApiGatewayRequestContext;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/13/2018 11:28
 */
public class CognitoIdentityFilter implements Filter {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  public static final String COGNITO_IDENTITY_ATTRIBUTE = "com.amazonaws.serverless.cognitoId";

  private static Logger log = LoggerFactory.getLogger(CognitoIdentityFilter.class);

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.Filter#destroy()
   */
  @Override public void destroy() {
    // nothing to do in destroy
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
   *       javax.servlet.FilterChain)
   */
  @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
    FilterChain filterChain) throws IOException, ServletException {
    Object apiGwContext = servletRequest.getAttribute(RequestReader.API_GATEWAY_CONTEXT_PROPERTY);

    if (apiGwContext == null) {
      log.warn("API Gateway context is null");
      filterChain.doFilter(servletRequest, servletResponse);
    }

    if (!ApiGatewayRequestContext.class.isAssignableFrom(apiGwContext.getClass())) {
      log.warn("API Gateway context object is not of valid type");
      filterChain.doFilter(servletRequest, servletResponse);
    }

    ApiGatewayRequestContext ctx = (ApiGatewayRequestContext) apiGwContext;

    if (ctx.getIdentity() == null) {
      log.warn("Identity context is null");
      filterChain.doFilter(servletRequest, servletResponse);
    }

    String cognitoIdentityId = ctx.getIdentity().getCognitoIdentityId();

    if ((cognitoIdentityId == null) || "".equals(cognitoIdentityId.trim())) {
      log.warn("Cognito identity id in request is null");
    }

    servletRequest.setAttribute(COGNITO_IDENTITY_ATTRIBUTE, cognitoIdentityId);
    filterChain.doFilter(servletRequest, servletResponse);
  } // end method doFilter

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  @Override public void init(FilterConfig filterConfig) throws ServletException {
    // nothing to do in init
  }
} // end class CognitoIdentityFilter
