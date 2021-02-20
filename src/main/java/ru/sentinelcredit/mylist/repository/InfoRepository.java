package ru.sentinelcredit.mylist.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sentinelcredit.mylist.model.Info;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, String> {

}
