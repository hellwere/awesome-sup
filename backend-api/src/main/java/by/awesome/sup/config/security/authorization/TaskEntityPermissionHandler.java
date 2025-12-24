package by.awesome.sup.config.security.authorization;

import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.service.common.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityPermissionHandler implements EntityPermissionHandler {

    @Override
    public boolean hasPermission(User user, Object object, String action) {
        if (object instanceof Task) {
            return true;
        }
        return false;
    }

    @Override
    public String getEntityName() {
        return "TASK";
    }
}
