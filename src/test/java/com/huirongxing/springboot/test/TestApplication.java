package com.huirongxing.springboot.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/07/2018 09:42
 */
@ComponentScan(basePackages = "com.huirongxing.springboot")
@SpringBootApplication public class TestApplication extends SpringBootServletInitializer {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * main.
   *
   * @param  args  String[]
   */
  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /*
   * Create required HandlerAdapter, to avoid several default HandlerAdapter instances being created
   */
  /**
   * handlerAdapter.
   *
   * @return  HandlerAdapter
   */
  @Bean public HandlerAdapter handlerAdapter() {
    return new RequestMappingHandlerAdapter();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /*
   * optimization - avoids creating default exception resolvers; not required as the serverless container handles
   * all exceptions
   *
   * By default, an ExceptionHandlerExceptionResolver is created which creates many dependent object, including
   * an expensive ObjectMapper instance.
   */
  /**
   * handlerExceptionResolver.
   *
   * @return  HandlerExceptionResolver
   */
  @Bean public HandlerExceptionResolver handlerExceptionResolver() {
    return new HandlerExceptionResolver() {
      @Override public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) {
        return null;
      }
    };
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /*
   * Create required HandlerMapping, to avoid several default HandlerMapping instances being created
   */
  /**
   * handlerMapping.
   *
   * @return  HandlerMapping
   */
  @Bean public HandlerMapping handlerMapping() {
    return new RequestMappingHandlerMapping();
  }
} // end class TestApplication
