package ru.sentinelcredit.mylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ru.sentinelcredit.mylist.model.MyList;

import java.util.List;

@Repository
public interface MyListRepository extends JpaRepository<MyList, String> {
    List<MyList> findByJobId(String jobId);

    @Procedure("ml_procedures.ml_explorer")
    void mlExplorer(String sBatchId, String xTYPE, String xCREATOR_ID, String xCAMPAIGN, String xMARKER, String sNewBatchId);

    @Query(value = "select s_sequence_pkg.get_next_sbllike_id from dual", nativeQuery = true)
    String getNextSblLikeId();
}
