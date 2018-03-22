/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.huirongxing.springboot.model;

import java.io.Serializable;

import java.util.Date;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/13/2018 14:35
 */
public class Pet implements Serializable {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static final long serialVersionUID = 6636463289041461819L;

  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String breed;
  private Date   dateOfBirth;
  private String id;
  private String name;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for breed.
   *
   * @return  String
   */
  public String getBreed() {
    return breed;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for date of birth.
   *
   * @return  Date
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for id.
   *
   * @return  String
   */
  public String getId() {
    return id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for name.
   *
   * @return  String
   */
  public String getName() {
    return name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for breed.
   *
   * @param  breed  String
   */
  public void setBreed(String breed) {
    this.breed = breed;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for date of birth.
   *
   * @param  dateOfBirth  Date
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for id.
   *
   * @param  id  String
   */
  public void setId(String id) {
    this.id = id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for name.
   *
   * @param  name  String
   */
  public void setName(String name) {
    this.name = name;
  }
} // end class Pet
