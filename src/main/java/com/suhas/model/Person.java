package com.suhas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String age;

    @NotBlank
    private String favouriteColor;

    @NotBlank
    private String hobbies;

    @Column(nullable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonIgnore
    private Date createdAt;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonIgnore
    private Date updatedAt;

    @Column
    @JsonIgnore
    private Boolean active;

    public Person() {
    }

    private Person(PersonDTOBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.favouriteColor = builder.favouriteColor;
        this.hobbies = builder.hobbies;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }


    public static class PersonDTOBuilder {
        private String firstName;
        private String lastName;
        private String age;
        private String favouriteColor;
        private String hobbies;
        private Date createdAt;
        private Date updatedAt;

        public PersonDTOBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PersonDTOBuilder withAge(String age) {
            this.age = age;
            return this;
        }

        public PersonDTOBuilder withFavColor(String favouriteColor) {
            this.favouriteColor = favouriteColor;
            return this;
        }

        public PersonDTOBuilder withHobby(String hobbies) {
            this.hobbies = hobbies;
            return this;
        }

        public PersonDTOBuilder withCreatedDate(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PersonDTOBuilder withUpdatedDate(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavouriteColor() {
        return favouriteColor;
    }

    public void setFavouriteColor(String favouriteColor) {
        this.favouriteColor = favouriteColor;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}

