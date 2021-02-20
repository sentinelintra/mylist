package ru.sentinelcredit.mylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sentinelcredit.mylist.model.OperationColumn;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<OperationColumn, String> {
    List<OperationColumn> findByOperationName(String operationName);
}
