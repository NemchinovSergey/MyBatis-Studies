package com.nsergey;

import com.nsergey.dao.UserXmlDao;
import com.nsergey.model.UserModel;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        SessionFactory sessionFactory = SessionFactory.getInstance();

        SqlSession session = sessionFactory.openSession();
        try {
            // Получаем маппер
            UserXmlDao mapper = session.getMapper(UserXmlDao.class);

            // Сколько всего пользователей
            int count = mapper.getCount();
            System.out.println("Users count: " + count);

            // Получаем отдельный объект
            UserModel userModel = mapper.getById(61L);
            System.out.println(userModel);

            System.out.println("Получаем список проверяющих");
            List<UserModel> users = mapper.getAll();
            for (UserModel u : users) {
                System.out.println(u);
            }

            // Ищем пользователя по логину
            UserModel user = mapper.getByLogin("SuperPuperLogin");
            if (user != null) {
                System.out.println("User with SuperPuperLogin login is present! Delete it...");
                mapper.deleteById(user.getId());
                session.commit();
            }
            else {
                // Добавляем новый объект
                System.out.println("User with SuperPuperLogin login is absent! Add it...");
                user = new UserModel();
                user.setAgencyType(1L);
                user.setAgencyName("Отделение 513");
                user.setUnitName("Название подразделения");
                user.setPosition("Должность");
                user.setLastName("LastName");
                user.setFirstName("FirstName");
                user.setMiddleName("MiddleName");
                user.setLogin("SuperPuperLogin");
                user.setDisabled(true);

                mapper.insert(user);
                session.commit();

                // А теперь сразу же обновим данные
                user = mapper.getByLogin("SuperPuperLogin");
                if (user != null) {
                    user.setLastName("Фамилия");
                    user.setFirstName("Имя");
                    user.setMiddleName("Отчество");

                    mapper.update(user.getId(), user);
                    session.commit();
                }
            }

        } finally {
            session.close();
        }
    }
}
