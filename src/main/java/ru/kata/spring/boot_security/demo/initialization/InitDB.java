package ru.kata.spring.boot_security.demo.initialization;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitDB {

    private final RoleService roleService;
    private final UserService userService;

    public InitDB(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    @Transactional
    public void fillDb() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        User admin = new User( "Anton", "Шорин", 31, "ant@gmail.com", "1");
        admin.addRole(roleService.add(roleAdmin));
        userService.add(admin);

        User user = new User( "Olga", "Шорина",31, "Olga@gmail.com", "2");
        user.addRole(roleService.add(roleUser));
        userService.add(user);

        User userAdmin = new User( "Kirill", "Шорин",1, "Ki@gmail.com", "3");
        userAdmin.addRole(roleService.add(roleUser));
        userAdmin.addRole(roleService.add(roleAdmin));
        userService.add(userAdmin);
    }
}
