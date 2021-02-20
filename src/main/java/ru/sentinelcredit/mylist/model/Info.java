package ru.sentinelcredit.mylist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CX_MY_LIST_INFO")
public class Info {
    @Id
    private String operationName;
    @Lob
    private String info;
}
