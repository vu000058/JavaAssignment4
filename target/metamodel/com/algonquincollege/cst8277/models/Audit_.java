package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-10T15:36:16")
@StaticMetamodel(Audit.class)
public class Audit_ { 

    public static volatile SingularAttribute<Audit, LocalDateTime> createdDate;
    public static volatile SingularAttribute<Audit, LocalDateTime> updatedDate;

}