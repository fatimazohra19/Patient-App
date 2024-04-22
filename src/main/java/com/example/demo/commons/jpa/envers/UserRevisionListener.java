package com.example.demo.commons.jpa.envers;

import org.hibernate.envers.RevisionListener;

public class UserRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        UserRevisionEntity customRevEntity = (UserRevisionEntity) revisionEntity;
        //TOOD récupérer l utilisateur directement depuis spring security
        customRevEntity.setEditor("anonymous");
    }
}