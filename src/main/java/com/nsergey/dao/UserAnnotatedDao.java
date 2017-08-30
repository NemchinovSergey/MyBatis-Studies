package com.nsergey.dao;

import com.nsergey.model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserAnnotatedDao  {

    @Results(value = {
            @Result(property = "id",            column = "AUDITOR_ID"),
            @Result(property = "agencyType",    column = "AUDITOR_AGENCY_TYPE_ID"),
            @Result(property = "agencyName",    column = "AUDITOR_AGENCY_NAME"),
            @Result(property = "unitName",      column = "AUDITOR_UNIT_NAME"),
            @Result(property = "position",      column = "AUDITOR_POSITION"),
            @Result(property = "lastName",      column = "AUDITOR_LASTNAME"),
            @Result(property = "firstName",     column = "AUDITOR_FIRSTNAME"),
            @Result(property = "middleName",    column = "AUDITOR_MIDDLENAME"),
            @Result(property = "login",         column = "AUDITOR_LOGIN"),
            @Result(property = "disabled",      column = "AUDITOR_DELETED")
    })
    @Select("select " +
            " AUDITOR_ID, " +
            " AUDITOR_AGENCY_TYPE_ID, " +
            " AUDITOR_AGENCY_NAME, " +
            " AUDITOR_UNIT_NAME, " +
            " AUDITOR_POSITION, " +
            " AUDITOR_LASTNAME, " +
            " AUDITOR_FIRSTNAME," +
            " AUDITOR_MIDDLENAME, " +
            " AUDITOR_LOGIN, " +
            " AUDITOR_DELETED" +
            " from ASV_AUDITOR " +
            " where AUDITOR_ID = #{userId}")
    UserModel getById(@Param("userId") Long userId);

    @Select("select " +
            " AUDITOR_ID, " +
            " AUDITOR_AGENCY_TYPE_ID, " +
            " AUDITOR_AGENCY_NAME, " +
            " AUDITOR_UNIT_NAME, " +
            " AUDITOR_POSITION, " +
            " AUDITOR_LASTNAME, " +
            " AUDITOR_FIRSTNAME," +
            " AUDITOR_MIDDLENAME, " +
            " AUDITOR_LOGIN, " +
            " AUDITOR_DELETED" +
            " from ASV_AUDITOR")
    @ResultMap("getById-Long") // it'a magic string: method "getById" + parameter type "Long" => "getById-Long" (!!!)
    List<UserModel> getAll();

    @Insert("INSERT INTO ASV_AUDITOR (" +
            "   AUDITOR_AGENCY_TYPE_ID, " +
            "   AUDITOR_AGENCY_NAME, " +
            "   AUDITOR_UNIT_NAME, " +
            "   AUDITOR_POSITION, " +
            "   AUDITOR_LASTNAME, " +
            "   AUDITOR_FIRSTNAME, " +
            "   AUDITOR_MIDDLENAME, " +
            "   AUDITOR_LOGIN, " +
            "   AUDITOR_DELETED" +
            ") " +
            " VALUES ( " +
            "   #{user.agencyType}, " +
            "   #{user.agencyName}, " +
            "   #{user.unitName}, " +
            "   #{user.position}, " +
            "   #{user.lastName}, " +
            "   #{user.firstName}, " +
            "   #{user.middleName}, " +
            "   #{user.login}, " +
            "   #{user.disabled} " +
            ")")
    void insert(@Param("user") UserModel userModel);

    @Update("UPDATE ASV_AUDITOR " +
            " SET " +
            "     AUDITOR_AGENCY_TYPE_ID = #{user.agencyType}, " +
            "     AUDITOR_AGENCY_NAME = #{user.agencyName}, " +
            "     AUDITOR_UNIT_NAME = #{user.unitName}, " +
            "     AUDITOR_POSITION = #{user.position}, " +
            "     AUDITOR_LASTNAME = #{user.lastName}, " +
            "     AUDITOR_FIRSTNAME = #{user.firstName}, " +
            "     AUDITOR_MIDDLENAME = #{user.middleName}, " +
            "     AUDITOR_LOGIN = #{user.login}, " +
            "     AUDITOR_DELETED = #{user.disabled} " +
            " where AUDITOR_ID = #{userId}")
    void update(@Param("userId") Long id, @Param("user") UserModel userModel);

    @Delete("DELETE FROM ASV_AUDITOR WHERE AUDITOR_ID = #{userId}")
    void deleteById(@Param("userId") Long id);

    @Select("select " +
            " AUDITOR_ID, " +
            " AUDITOR_AGENCY_TYPE_ID, " +
            " AUDITOR_AGENCY_NAME, " +
            " AUDITOR_UNIT_NAME, " +
            " AUDITOR_POSITION, " +
            " AUDITOR_LASTNAME, " +
            " AUDITOR_FIRSTNAME," +
            " AUDITOR_MIDDLENAME, " +
            " AUDITOR_LOGIN, " +
            " AUDITOR_DELETED" +
            " from ASV_AUDITOR " +
            " where AUDITOR_LOGIN = #{login}")
    @ResultMap("getById-Long")
    UserModel getByLogin(@Param("login") String login);

    @Select("select count(*) from ASV_AUDITOR")
    int getCount();

}
