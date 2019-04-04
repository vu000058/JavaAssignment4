package com.algonquincollege.cst8277.security;

import static com.algonquincollege.cst8277.security.Assignment4Constants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.security.Assignment4Constants.DEFAULT_KEY_SIZE;
import static com.algonquincollege.cst8277.security.Assignment4Constants.DEFAULT_PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.security.Assignment4Constants.DEFAULT_PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.security.Assignment4Constants.DEFAULT_SALT_SIZE;
import static com.algonquincollege.cst8277.security.Assignment4Constants.PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.security.Assignment4Constants.PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.security.Assignment4Constants.PROPERTY_KEYSIZE;
import static com.algonquincollege.cst8277.security.Assignment4Constants.PROPERTY_SALTSIZE;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollge.cst8277.model.PlatformRole;
import com.algonquincollge.cst8277.model.PlatformUser;

@Startup
@Singleton
public class BuildDefaultAdminUser {

    @Inject
    protected CustomIdentityStoreJPAHelper jpaHelper;

    private String defaultAdminUsername = "admin";

    private String defaultAdminUserPassword = "admin";

    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @PostConstruct
    public void init() {
        // build default admin user (if needed)
        PlatformUser defaultAdminUser = jpaHelper.findUserByName(defaultAdminUsername);
        if (defaultAdminUser == null) {
            defaultAdminUser = new PlatformUser();
            defaultAdminUser.setUsername(defaultAdminUsername);
            Map<String, String> pbAndjProperties = new HashMap<>();
            pbAndjProperties.put(PROPERTY_ALGORITHM, DEFAULT_PROPERTY_ALGORITHM);
            pbAndjProperties.put(PROPERTY_ITERATIONS, DEFAULT_PROPERTY_ITERATIONS);
            pbAndjProperties.put(PROPERTY_SALTSIZE, DEFAULT_SALT_SIZE);
            pbAndjProperties.put(PROPERTY_KEYSIZE, DEFAULT_KEY_SIZE);
            pbAndjPasswordHash.initialize(pbAndjProperties);
            String pwHash = pbAndjPasswordHash.generate(defaultAdminUserPassword.toCharArray());
            defaultAdminUser.setPwHash(pwHash);

            PlatformRole platformRole = new PlatformRole();
            platformRole.setRoleName(ADMIN_ROLENAME);
            Set<PlatformRole> platformRoles = defaultAdminUser.getPlatformRoles();
            platformRoles.add(platformRole);
            jpaHelper.savePlatformUser(defaultAdminUser);
        }
    }
}