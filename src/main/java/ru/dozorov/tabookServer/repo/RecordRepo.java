package ru.dozorov.tabookServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dozorov.tabookServer.domain.Record;

import java.util.Collection;

public interface RecordRepo extends JpaRepository<Record, Long> {
    Collection<Record> findRecordsByUser(String user);
}
