package com.algonquincollege.cst8277.models;

import com.algonquincollege.cst8277.models.PlatformUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-10T15:36:16")
@StaticMetamodel(PlatformRole.class)
public class PlatformRole_ extends ModelBase_ {

    public static volatile ListAttribute<PlatformRole, PlatformUser> platformUsers;
    public static volatile SingularAttribute<PlatformRole, String> roleName;

}