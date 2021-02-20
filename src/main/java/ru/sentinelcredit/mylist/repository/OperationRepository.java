package ru.sentinelcredit.mylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sentinelcredit.mylist.model.Operation;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, String> {
    List<Operation> findByCreatorLogin(String creatorLogin);
}
