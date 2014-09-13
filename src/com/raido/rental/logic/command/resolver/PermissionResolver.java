package com.raido.rental.logic.command.resolver;

import com.raido.rental.logic.command.xmlparser.XmlPermissionInitializer;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;

import java.util.Map;
import java.util.Set;

public class PermissionResolver {

    private Map<String, Set<String>> permissionMap;

    public PermissionResolver(String configFileName)
            throws TechnicalException, LogicalException {

        XmlPermissionInitializer initializer = new XmlPermissionInitializer();
        initializer.buildPermissionMap(configFileName);
        permissionMap = initializer.getPermissionMap();
    }

    public boolean isActionAllowed(String commandName, String userRole) {
        Set<String> eligibleRoles = permissionMap.get(commandName);
        return eligibleRoles.contains(userRole);
    }
}
