package com.udea.concesionario4.com.udea.concesionario4.modelo;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-08T09:42:29")
@StaticMetamodel(AbstractAuditingEntity.class)
public abstract class AbstractAuditingEntity_ { 

    public static volatile SingularAttribute<AbstractAuditingEntity, Instant> createdDate;
    public static volatile SingularAttribute<AbstractAuditingEntity, String> createdBy;
    public static volatile SingularAttribute<AbstractAuditingEntity, Instant> lastModifiedDate;
    public static volatile SingularAttribute<AbstractAuditingEntity, String> lastModifiedBy;

}