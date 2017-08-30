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
            // �������� ������
            UserXmlDao mapper = session.getMapper(UserXmlDao.class);

            // ������� ����� �������������
            int count = mapper.getCount();
            System.out.println("Users count: " + count);

            // �������� ��������� ������
            UserModel userModel = mapper.getById(61L);
            System.out.println(userModel);

            System.out.println("�������� ������ �����������");
            List<UserModel> users = mapper.getAll();
            for (UserModel u : users) {
                System.out.println(u);
            }

            // ���� ������������ �� ������
            UserModel user = mapper.getByLogin("SuperPuperLogin");
            if (user != null) {
                System.out.println("User with SuperPuperLogin login is present! Delete it...");
                mapper.deleteById(user.getId());
                session.commit();
            }
            else {
                // ��������� ����� ������
                System.out.println("User with SuperPuperLogin login is absent! Add it...");
                user = new UserModel();
                user.setAgencyType(1L);
                user.setAgencyName("��������� 513");
                user.setUnitName("�������� �������������");
                user.setPosition("���������");
                user.setLastName("LastName");
                user.setFirstName("FirstName");
                user.setMiddleName("MiddleName");
                user.setLogin("SuperPuperLogin");
                user.setDisabled(true);

                mapper.insert(user);
                session.commit();

                // � ������ ����� �� ������� ������
                user = mapper.getByLogin("SuperPuperLogin");
                if (user != null) {
                    user.setLastName("�������");
                    user.setFirstName("���");
                    user.setMiddleName("��������");

                    mapper.update(user.getId(), user);
                    session.commit();
                }
            }

        } finally {
            session.close();
        }
    }
}
