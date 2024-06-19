package com.deposit.model;

import com.deposit.controller.main.Main;
import com.deposit.model.enums.AppsStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Apps implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int sum;
    private int term;
    private String date = Main.getDate();

    @Enumerated(EnumType.STRING)
    private AppsStatus status = AppsStatus.WAITING;
    @Column(length = 5000)
    private String reason = "";

    @ManyToOne
    private Credits credit;
    @ManyToOne
    private Users owner;

    public Apps(Credits credit, Users owner, int sum) {
        this.credit = credit;
        this.owner = owner;
        this.sum = sum;
        this.term = credit.getTerm();
    }

    public int getShould() {
        double res = (double) credit.getRate() / 12;
        res = res * term;
        res = res * sum;
        res = res / 100;
        return (int) res;
    }
}
