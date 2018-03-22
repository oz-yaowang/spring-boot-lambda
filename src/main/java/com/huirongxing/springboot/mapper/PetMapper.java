package com.huirongxing.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.huirongxing.springboot.model.Pet;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:Arvin.Wang@hrx.ai">Arvin Wang</a>
 * @version  03/16/2018 14:33
 */
@Mapper public interface PetMapper {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * findById.
   *
   * @param   id  String
   *
   * @return  Pet
   */
  @Select("SELECT * FROM pet where id=#{id}")
  Pet findById(@Param("id") String id);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * save.
   *
   * @param  pet  Pet
   */
  @Insert("INSERT INTO pet ( id, breed, name, date_of_birth) VALUES ( #{id},#{breed},#{name},#{dateOfBirth})")
  void save(Pet pet);

} // end interface PetMapper
