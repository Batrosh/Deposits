package com.deposit.model;

import com.deposit.model.enums.Goals;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Credits implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int rate;
    private int term;
    private int minLimit;
    private int maxLimit;
    private String file;
    private String payments;

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Goals goal;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Apps> apps = new ArrayList<>();

    public Credits(String name, int rate, int term, int minLimit, int maxLimit, Goals goal, String file, String description, String payments) {
        this.name = name;
        this.rate = rate;
        this.term = term;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.goal = goal;
        this.file = file;
        this.description = description;
        this.payments = payments;
    }

    public void set(String name, int rate, int term, int minLimit, int maxLimit, Goals goal, String description, String payments) {
        this.name = name;
        this.rate = rate;
        this.term = term;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.goal = goal;
        this.description = description;
        this.payments = payments;
    }

    public int getAppsSize() {
        return apps.size();
    }
}
