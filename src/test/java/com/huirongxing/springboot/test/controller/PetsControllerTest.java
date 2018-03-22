package com.huirongxing.springboot.test.controller;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.huirongxing.springboot.config.MvcConfig;
import com.huirongxing.springboot.controller.PetsController;
import com.huirongxing.springboot.model.Pet;
import com.huirongxing.springboot.service.PetsService;
import com.huirongxing.springboot.test.config.UnitTestConfig;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/18/2018 13:24
 */
@ContextConfiguration(classes = { MvcConfig.class, UnitTestConfig.class })
@ExtendWith({ SpringExtension.class })
@WebMvcTest(PetsController.class)
@ActiveProfiles("dev")
public class PetsControllerTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private MockMvc mvc;

  @MockBean private PetsService petsService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * testEnvData.
   *
   * @throws  Exception  exception
   */
  @Test public void testEnvData() throws Exception {
// this.mvc.perform(MockMvcRequestBuilders.get("/envdata").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());

    MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/envdata")).andReturn()
      .getResponse();
    Assertions.assertEquals(response.getStatus(), 200);
    System.out.print(response.getContentAsString());
// Assert.assertNotNull(response.getContentAsString());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testExample.
   *
   * @throws  Exception  exception
   */
  @Test public void testExample() throws Exception {
    given(this.petsService.callRemoteService()).willReturn("controller unit test");
    this.mvc.perform(MockMvcRequestBuilders.get("/controller/test").accept(MediaType.TEXT_PLAIN)).andExpect(status()
      .isOk()).andExpect(content().string("controller unit test"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testEnvData.
   *
   * @throws  Exception  exception
   */
  @Test public void testSavePets() throws Exception {
    Pet pet = new Pet();
    pet.setName("test");
    pet.setDateOfBirth(new Date());
    pet.setBreed("breed");
    pet.setId("test_id");

    ObjectMapper mapper = new ObjectMapper();


    given(this.petsService.save(pet)).willReturn(pet);
    this.mvc.perform(MockMvcRequestBuilders.post("/pets").content(mapper.writeValueAsString(pet)).contentType(
        MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON)).andExpect(
      status().isOk());
  }
} // end class PetsControllerTest
