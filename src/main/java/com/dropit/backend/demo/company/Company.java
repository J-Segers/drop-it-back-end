package com.dropit.backend.demo.company;

import javax.persistence.*;

@Entity
@Table
public class Company {

    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    private Long companyId;
    private String companyName;
    private String signedArtists;
    private String owner;


    public Company() {
    }

    public Company(Long companyId, String companyName, String signedArtists, String owner) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.signedArtists = signedArtists;
        this.owner = owner;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSignedArtists() {
        return signedArtists;
    }

    public void setSignedArtists(String signedArtists) {
        this.signedArtists = signedArtists;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", signedArtists='" + signedArtists + '\'' +
                '}';
    }


}
