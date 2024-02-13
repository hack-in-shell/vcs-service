package com.example.vcsservice.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "LOCALE_MESSAGE")
public class LocaleMessage implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOCALE")
    private String locale;

    @Column(name = "CONTENT", columnDefinition = "TEXT")
    private String content;

    @Column(name = "VERSION")
    private String version;

}
