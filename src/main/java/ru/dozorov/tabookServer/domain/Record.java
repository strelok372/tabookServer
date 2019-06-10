package ru.dozorov.tabookServer.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "records")
public class Record {
    @Id
    private Long id;

    @ManyToOne
    private User user;

    private String text;
    private String username;
    private String tags;
    private Boolean published;
    private LocalDateTime lastUpdated;

//    public Record(Long id, User userId) {
//        this.id = id;
//        this.user = userId;
//    }
//
//    public Record(User userId) {
//        this.user = userId;
//    }

    public Record() {
    }
}
