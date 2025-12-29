package by.awesome.sup.config.security.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityPermissionHandler implements EntityPermissionHandler {

    @Override
    public boolean hasPermission(User user, Object object, String action) {
        if (object instanceof by.awesome.sup.entity.authorization.User userEntity) {
            if (userEntity.getAuthorities().contains(new SimpleGrantedAuthority("PERMISSION_CREATE"))) return true;
            if (userEntity.getOwner().equals(userEntity.getUsername())) return true;
            return user.getAuthorities().contains(new SimpleGrantedAuthority("USER_" + action.toUpperCase()));
        }
        return false;
    }

    @Override
    public String getEntityName() {
        return "USER";
    }
}
