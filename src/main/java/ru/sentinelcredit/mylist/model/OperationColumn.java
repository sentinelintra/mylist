package ru.sentinelcredit.mylist.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CX_MY_LIST_CLMN")
public class OperationColumn {

    @Id
    private String rowId;
    private String columnId;
    private String columnName;
    private Integer columnNum;
    private String operationName;
}
