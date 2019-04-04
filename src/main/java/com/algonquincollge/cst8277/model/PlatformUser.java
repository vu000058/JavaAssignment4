package com.algonquincollge.cst8277.model;

import static com.algonquincollege.cst8277.security.Assignment4Constants.ID_COLUMN_NAME;
import static com.algonquincollege.cst8277.security.Assignment4Constants.PLATFORM_USER_INVERSEJOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.security.Assignment4Constants.PLATFORM_USER_JOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.security.Assignment4Constants.PLATFORM_USER_JOIN_TABLE_NAME;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.algonquincollege.cst8277.security.Assignment4Constants;

/**
 * User class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@Table(name=Assignment4Constants.PLATFORM_USER_TABLE_NAME)
@NamedQueries(
    @NamedQuery(
        name=Assignment4Constants.FIND_PLATFORM_USER_BY_NAME_QUERYNAME,
        query=Assignment4Constants.FIND_PLATFORM_USER_BY_NAME_JPQL
    )
)
@EntityListeners({AuditListener.class})
public class PlatformUser extends ModelBase implements Serializable {
    
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    public PlatformUser() {
        super();
    }
    
    private String firstName;
    private String lastName;
    private String email;
    private List<Cart> carts = new ArrayList<Cart>();
    
    protected String username;
    protected String pwHash;
    protected Set<PlatformRole> platformRoles = new HashSet<>();

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonbTransient
    public String getPwHash() {
        return pwHash;
    }
    
    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    @JsonbTransient
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name=PLATFORM_USER_JOIN_TABLE_NAME,
      joinColumns=@JoinColumn(name=PLATFORM_USER_JOIN_COLUMN_NAME, referencedColumnName=ID_COLUMN_NAME),
      inverseJoinColumns=@JoinColumn(name=PLATFORM_USER_INVERSEJOIN_COLUMN_NAME, referencedColumnName=ID_COLUMN_NAME))
    public Set<PlatformRole> getPlatformRoles() {
        return platformRoles;
    }
    
    public void setPlatformRoles(Set<PlatformRole> platformRoles) {
        this.platformRoles = platformRoles;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @JsonbTransient
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}