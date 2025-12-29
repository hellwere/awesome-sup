package by.awesome.sup.config.security.authorization;

import by.awesome.sup.entity.common.Timesheet;
import by.awesome.sup.entity.common.task.Task;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class TimesheetEntityPermissionHandler implements EntityPermissionHandler {

    @Override
    public boolean hasPermission(User user, Object object, String action) {
        if (object instanceof Timesheet timesheet) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority("PERMISSION_CREATE"))) return true;
            if (timesheet.getOwner().equals(user.getUsername())) return true;
            return user.getAuthorities().contains(new SimpleGrantedAuthority("TIMESHEET_" + action.toUpperCase()));
        }
        return false;
    }

    @Override
    public String getEntityName() {
        return "TIMESHEET";
    }
}
