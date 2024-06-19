package com.deposit.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserDescriptions implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String fio;
    private String tel;
    private String citizenship;
    private String passport;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Apps> appsList;

    public UserDescriptions(String fio, String passport) {
        this.fio = fio;
        this.tel = "";
        this.citizenship = "";
        this.passport = passport;
    }

}
