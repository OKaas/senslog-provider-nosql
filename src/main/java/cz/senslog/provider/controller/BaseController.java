package cz.senslog.provider.controller;

import cz.senslog.model.db.UserPrivilege;
import cz.senslog.model.util.PrivilegeBitSet;
import cz.senslog.provider.repository.UserPrivilegeRepository;
import cz.senslog.provider.security.UserToken;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;

    protected boolean isApproved(ObjectId unitGroupId, PrivilegeBitSet requestedPrivilege){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserToken) {
            ObjectId userId = ((UserToken) principal).getUid();

            UserPrivilege privilege = userPrivilegeRepository.findByUnitGroupIdAndUserId(unitGroupId, userId);
            if(privilege == null){
                LOGGER.warn("User \'{}\' not privilege.", userId);
                return false;
            }

            privilege.getPrivileges().and(requestedPrivilege);

            return privilege.getPrivileges().equals(requestedPrivilege);
        }
        return false;
    }
}
