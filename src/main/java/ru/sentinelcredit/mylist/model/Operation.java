package ru.sentinelcredit.mylist.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@IdClass(OperationPK.class)
@Table(name = "MY_LIST_ACCESS_VIEW")
public class Operation {
    @Id
    private String type;
    @Id
    private String creatorLogin;

    //@OneToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "OPERATION_NAME", referencedColumnName = "TYPE", nullable = false)
//    @OneToMany
//    @JoinColumn
//    private List<Column> column;
}
