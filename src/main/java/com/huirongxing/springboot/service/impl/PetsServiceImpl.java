package com.huirongxing.springboot.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.huirongxing.springboot.mapper.PetMapper;
import com.huirongxing.springboot.model.Pet;
import com.huirongxing.springboot.remoteservice.RemoteService;
import com.huirongxing.springboot.service.PetsService;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/13/2018 13:14
 */
@Service public class PetsServiceImpl implements PetsService {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private PetMapper petMapper;

  /** TODO: DOCUMENT ME! */
  @Autowired private RemoteService remoteService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  com.huirongxing.springboot.service.PetsService#callRemoteService()
   */
  @Override public String callRemoteService() {
    return remoteService.call();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.huirongxing.springboot.service.PetsService#findById(String)
   */
  @Override public Pet findById(String id) {
    return petMapper.findById(id);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  PetsService#save(Pet)
   */
  @Override public Pet save(Pet pet) {
    pet.setId(UUID.randomUUID().toString());

    petMapper.save(pet);

    return pet;
  }
} // end class PetsServiceImpl
