package cz.senslog.provider.controller;

import cz.senslog.model.db.UserPrivilege;
import cz.senslog.model.util.PrivilegeBitSet;
import cz.senslog.provider.repository.UserPrivilegeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;

    protected boolean isApproved(ObjectId unitGroupId, ObjectId userId, PrivilegeBitSet requestedPrivilege){

        UserPrivilege privilege = userPrivilegeRepository.findByUnitGroupIdAndUserId(unitGroupId, userId);
        if(privilege == null){
            return false;
        }

        privilege.getPrivileges().and(requestedPrivilege);

        return privilege.getPrivileges().equals(requestedPrivilege);
    }
}
