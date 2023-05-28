package com.numble.core.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@EqualsAndHashCode
@Embeddable
public class VoteKey implements Serializable {

    private String ip;

    @JoinColumn(name = "puppy_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = EAGER)
    private Puppy puppy;

    protected VoteKey() {
    }

    public VoteKey(String ip, Puppy puppy) {
        this.ip = ip;
        this.puppy = puppy;
    }

}
