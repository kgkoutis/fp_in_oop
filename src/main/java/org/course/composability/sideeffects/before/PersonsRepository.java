package org.course.composability.sideeffects.before;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonsRepository {
    private final SqliteConnector database;

    public PersonsRepository() {
        database = new SqliteConnector();
        database.createPersonsTableIfAbsent();
    }

    public void insertPersons(final List<Person> persons) {
        database.truncatePersonsTable();
        database.insertPersons(persons);
    }

    public Optional<Person> getPerson(final UUID id) {
        if (database.personByIdExists(id)) {
            return Optional.of(database.findPersonById(id));
        }
        return Optional.empty();
    }
}