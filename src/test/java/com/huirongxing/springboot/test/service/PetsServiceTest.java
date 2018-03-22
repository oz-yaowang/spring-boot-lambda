package com.huirongxing.springboot.test.service;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.huirongxing.springboot.model.Pet;
import com.huirongxing.springboot.remoteservice.RemoteService;
import com.huirongxing.springboot.service.PetsService;
import com.huirongxing.springboot.test.TestApplication;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/14/2018 14:27
 */
@ActiveProfiles("petsService")
@ExtendWith({ SpringExtension.class })
@SpringBootTest(classes = TestApplication.class)
public class PetsServiceTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @MockBean private RemoteService remoteService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * test_getPet.
   *
   * @param  petsService  PetsService
   */
  @DisplayName("get pet test")
  @Test public void testGetPet(@Autowired PetsService petsService) {
    Pet pet = petsService.findById("test_id");
    Assertions.assertNotNull(pet);
    Assertions.assertEquals(pet.getName(), "arvin");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testRemoteService.
   *
   * @param  petsService  PetsService
   */
  @Test public void testRemoteService(@Autowired PetsService petsService) {
    given(this.remoteService.call()).willReturn("mock the response");

    String resp = petsService.callRemoteService();
    Assertions.assertEquals(resp, "mock the response");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * test.
   *
   * @param  petsService  PetsService
   */
  @DisplayName("save pet test")
  @Test public void testSavePet(@Autowired PetsService petsService) {
    Pet pet = new Pet();
    pet.setBreed("test");
    pet.setName("arvin");
    pet.setDateOfBirth(new Date());
    petsService.save(pet);

    Assertions.assertNotNull(pet.getId());

  }
} // end class PetsServiceTest
