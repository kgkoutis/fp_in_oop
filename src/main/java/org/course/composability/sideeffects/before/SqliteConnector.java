package org.course.composability.sideeffects.before;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SqliteConnector {

    private Connection connect() {
        String url = "jdbc:sqlite:./company.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertPersons(List<Person> persons) {
        String sql = "INSERT INTO Persons(id,age) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Person person : persons) {
                pstmt.setString(1, person.getId().toString());
                pstmt.setInt(2, person.getAge());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> selectAllPersons() {
        String sql = "SELECT id, age FROM Persons";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("id");
                int s = rs.getInt("age");
                Person person = new Person(UUID.fromString(id), s);
                persons.add(person);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return persons;
    }

    public Person findPersonById(UUID id) {
        String sql = "SELECT id, age FROM Persons WHERE id = ?";
        Person person = null;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String idString = rs.getString("id");
                int s = rs.getInt("age");
                person = new Person(UUID.fromString(idString), s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return person;
    }

    public boolean personByIdExists(UUID id) {
        String sql = "SELECT id, age FROM Persons WHERE id = ?";
        boolean found = false;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                found = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return found;
    }

    public void truncatePersonsTable() {
        String sql = "DELETE FROM Persons";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPersonsTableIfAbsent() {
        String sql = "CREATE TABLE IF NOT EXISTS  Persons\n" +
                "(\n" +
                "    id  VARCHAR(500)\n" +
                "        constraint rid_pkey\n" +
                "            primary key,\n" +
                "    age INTEGER not null\n" +
                ");\n";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SqliteConnector sqliteConnector = new SqliteConnector();
        sqliteConnector.createPersonsTableIfAbsent();
        sqliteConnector.truncatePersonsTable();

//        Person p1 = new Person(UUID.randomUUID(), 40);
//        Person p2 = new Person(UUID.randomUUID(), 10);
//        Person p3 = new Person(UUID.randomUUID(), 30);
//        Person p4 = new Person(UUID.randomUUID(), 25);
//
//        List<Person> persons = List.of(p1, p2, p3, p4);
//
//        sqliteConnector.insertPersons(persons);
//        List<Person> personsRetrieved = sqliteConnector.selectAllPersons();
//        personsRetrieved.forEach(System.out::println);
    }
}
