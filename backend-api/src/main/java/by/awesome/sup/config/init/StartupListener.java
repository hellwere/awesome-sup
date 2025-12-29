package by.awesome.sup.config.init;

import by.awesome.sup.entity.authorization.Grants;
import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.entity.authorization.Role;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.repository.RoleRepository;
import by.awesome.sup.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public static final String DEFAULT_USER_ROLE_NAME = "DEFAULT_USER_ROLE";

    public StartupListener(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.findByLogin("admin").isEmpty()) {
            Permission permission = new Permission();
            permission.setName("PERMISSION");
            permission.setGrants(Grants.CREATE);

            Role role = new Role();
            role.setName("ADMIN");
            role.setPermissions(Collections.singletonList(permission));

            User user = new User();
            user.setName("admin_user");
            user.setLogin("admin");
            user.setPassword("$2a$10$yElZRFGDHmJGkbzYk2/nju9g/.krMW2Bc92fB91f3g1ZaDq.zbkTG");
            user.setEmail("admin@awesomesup.by");
            user.setOwner("admin");
            roleRepository.save(role);
            user.setRoles(Collections.singletonList(role));
            userRepository.save(user);
        }

        // Для пользователя прошедшего через регистрацию
        if (roleRepository.findByName(DEFAULT_USER_ROLE_NAME).isEmpty()) {
            Permission defaultUserPermission = new Permission();
            defaultUserPermission.setName("DASHBOARD");
            defaultUserPermission.setGrants(Grants.READ);

            Role defaultUserRole = new Role();
            defaultUserRole.setName(DEFAULT_USER_ROLE_NAME);
            defaultUserRole.setPermissions(Collections.singletonList(defaultUserPermission));
            roleRepository.save(defaultUserRole);
        }
    }
}
