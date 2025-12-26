package by.awesome.sup.config.security.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EntityPermissionEvaluator implements PermissionEvaluator {

    private final Map<String, EntityPermissionHandler> handlers;
    private final EntityLoader loader;

    public EntityPermissionEvaluator(List<EntityPermissionHandler> handlerList, EntityLoader loader) {
        this.handlers = handlerList.stream().collect(
                Collectors.toMap(EntityPermissionHandler::getEntityName, h -> h));
        this.loader = loader;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        User user = (User) authentication.getPrincipal();
        EntityPermissionHandler handler = handlers.get(targetDomainObject.getClass().getSimpleName());
        return handler != null && handler.hasPermission(user, targetDomainObject, permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        User user = (User) authentication.getPrincipal();
        EntityPermissionHandler handler = handlers.get(targetType);
        Long id = targetId instanceof Long ? ((Long) targetId) : null;
        if (id == null) {
            return false;
        }
        Object obj = loader.loadEntity(id, targetType);
        return handler.hasPermission(user, obj, permission.toString());
    }
}
