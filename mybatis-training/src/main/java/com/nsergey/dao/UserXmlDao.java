package com.nsergey.dao;


import com.nsergey.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserXmlDao {
    /**
     * Получить пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Модель данных пользователя.
     */
    UserModel getById(@Param("userId") Long id);

    /**
     * Получить список всех пользователей
     *
     * @return Список пользователей
     */
    List<UserModel> getAll();

    /**
     * Добавляет нового пользователя
     * @param userModel Модель данных пользователя
     */
    void insert(@Param("user") UserModel userModel);

    /**
     * Обновляет данные пользователя
     * @param id Идентификатор пользователя
     * @param userModel Модель данных пользователя
     */
    void update(@Param("userId") Long id,
                @Param("user") UserModel userModel);

    /**
     * Удаляет пользователя по идентификатору
     *
     * @param id Идентификатор пользователя
     */
    void deleteById(@Param("userId") Long id);

    /**
     * Получить пользователя по логину
     * @param login Логин пользователя
     * @return Модель данных пользователя
     */
    UserModel getByLogin(@Param("login") String login);

    /**
     * Количество пользователей в БД
     * @return Количество пользователей
     */
    int getCount();
}
