package ru.sentinelcredit.mylist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "S_USER")
public class User {
    @Id
    private String rowId;
    private String login;
}
