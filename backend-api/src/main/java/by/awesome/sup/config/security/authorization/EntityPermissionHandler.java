package by.awesome.sup.config.security.authorization;

import org.springframework.security.core.userdetails.User;

public interface EntityPermissionHandler {
    boolean hasPermission(User user, Object object, String action);
    String getEntityName();
}
