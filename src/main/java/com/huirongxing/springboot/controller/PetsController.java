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
package com.huirongxing.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huirongxing.springboot.model.Pet;
import com.huirongxing.springboot.response.TestEnvResponse;
import com.huirongxing.springboot.service.PetsService;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/13/2018 16:07
 */
@RestController public class PetsController {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Value("${com.huirongxing.data:noData}")
  private String envData;

  @Autowired private PetsService petsService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * envData.
   *
   * @return  String
   */
  @RequestMapping(
    path   = "/controller/test",
    method = RequestMethod.GET
  )
  public String controllerTest() {
    return petsService.callRemoteService();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * createPet.
   *
   * @param   newPet  Pet
   *
   * @return  Pet
   */
  @RequestMapping(
    path   = "/pets",
    method = RequestMethod.POST
  )
  public Pet createPet(@RequestBody Pet newPet) {
    return petsService.save(newPet);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * envData.
   *
   * @return  TestEnvResponse
   */
  @RequestMapping(
    path   = "/envdata",
    method = RequestMethod.GET
  )
  public ResponseEntity<TestEnvResponse> envData() {
    TestEnvResponse res = new TestEnvResponse();

    return ResponseEntity.ok(new TestEnvResponse(envData));
  }


} // end class PetsController
