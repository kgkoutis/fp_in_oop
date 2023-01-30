package org.course.composability.sideeffects.before;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SqliteConnector {

    private Connection connect() {
        final String url = "jdbc:sqlite:./company.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertPersons(final List<Person> persons) {
        final String sql = "INSERT INTO Persons(id,age) VALUES(?,?)";

        try (final Connection conn = this.connect();
             final PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (final Person person : persons) {
                pstmt.setString(1, person.getId().toString());
                pstmt.setInt(2, person.getAge());
                pstmt.executeUpdate();
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> selectAllPersons() {
        final String sql = "SELECT id, age FROM Persons";
        final List<Person> persons = new ArrayList<>();

        try (final Connection conn = this.connect();
             final Statement stmt = conn.createStatement();
             final ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                final String id = rs.getString("id");
                final int s = rs.getInt("age");
                final Person person = new Person(UUID.fromString(id), s);
                persons.add(person);
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
        return persons;
    }

    public Person findPersonById(final UUID id) {
        final String sql = "SELECT id, age FROM Persons WHERE id = ?";
        Person person = null;

        try (final Connection conn = this.connect();
             final PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id.toString());
            final ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                final String idString = rs.getString("id");
                final int s = rs.getInt("age");
                person = new Person(UUID.fromString(idString), s);
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
        return person;
    }

    public boolean personByIdExists(final UUID id) {
        final String sql = "SELECT id, age FROM Persons WHERE id = ?";
        boolean found = false;

        try (final Connection conn = this.connect();
             final PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id.toString());
            final ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                found = true;
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
        return found;
    }

    public void truncatePersonsTable() {
        final String sql = "DELETE FROM Persons";

        try (final Connection conn = this.connect(); final PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPersonsTableIfAbsent() {
        final String sql = "CREATE TABLE IF NOT EXISTS  Persons\n" +
                "(\n" +
                "    id  VARCHAR(500)\n" +
                "        constraint rid_pkey\n" +
                "            primary key,\n" +
                "    age INTEGER not null\n" +
                ");\n";

        try (final Connection conn = this.connect(); final PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        final SqliteConnector sqliteConnector = new SqliteConnector();
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
