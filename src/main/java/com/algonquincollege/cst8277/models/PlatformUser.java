package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.utils.Assignment4Constants.ID_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.PLATFORM_USER_INVERSEJOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.PLATFORM_USER_JOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.PLATFORM_USER_JOIN_TABLE_NAME;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.algonquincollege.cst8277.utils.Assignment4Constants;

/**
 * User class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name=Assignment4Constants.PLATFORM_USER_TABLE_NAME)
@NamedQueries(
    @NamedQuery(
        name=Assignment4Constants.FIND_PLATFORM_USER_BY_NAME_QUERYNAME,
        query=Assignment4Constants.FIND_PLATFORM_USER_BY_NAME_JPQL
    )
)
public class PlatformUser extends ModelBase implements Serializable, Principal {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    public PlatformUser() {
        super();
    }
    
    private Customer customer;
    private String password;
    
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
    
    @Transient
    @JsonbTransient
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true)
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @Override
    public String getName() {
        return username;
    }
}