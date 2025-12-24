package by.awesome.sup.config.security.authorization;

import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.service.common.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectEntityPermissionHandler implements EntityPermissionHandler {

    @Override
    public boolean hasPermission(User user, Object object, String action) {
        /*if (object instanceof Project) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) return true;
            if ("update".equals(action) && object.getOwner().equals(user)) return true;
            return user.hasPermission("PROJECT_" + action.toUpperCase());
        }*/
        return false;
    }

    @Override
    public String getEntityName() {
        return "PROJECT";
    }
}
