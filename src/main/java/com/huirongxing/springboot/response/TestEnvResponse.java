package com.huirongxing.springboot.response;

/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/07/2018 13:28
 */
public class TestEnvResponse {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String data;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new TestEnvResponse object.
   */
  public TestEnvResponse() { }

  /**
   * Creates a new TestEnvResponse object.
   *
   * @param  data  String
   */
  public TestEnvResponse(String data) {
    this.data = data;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for data.
   *
   * @return  String
   */
  public String getData() {
    return data;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for data.
   *
   * @param  data  String
   */
  public void setData(String data) {
    this.data = data;
  }
} // end class TestEnvResponse
