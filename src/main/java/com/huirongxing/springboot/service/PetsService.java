package com.huirongxing.springboot.service;

import com.huirongxing.springboot.model.Pet;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/07/2018 13:06
 */
public interface PetsService {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * callRemoteService.
   *
   * @return  String
   */
  String callRemoteService();

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findById.
   *
   * @param   id  String
   *
   * @return  Pet
   */
  Pet findById(String id);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * save.
   *
   * @param   pet  Pet
   *
   * @return  Pet
   */
  Pet save(Pet pet);


} // end interface PetsService
