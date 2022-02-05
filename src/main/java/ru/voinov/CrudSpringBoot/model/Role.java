package ru.voinov.CrudSpringBoot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

//    @Transient
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;

    public Role(){

    }

    public Role(String name){
        this.name = name;
    }
    public Role(long id, String name) {
        this(name);
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNameHtml() {
        return name + " ";
    }


    @Override
    public String getAuthority() {
        return name;
    }
}
