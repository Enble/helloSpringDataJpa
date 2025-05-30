package kr.ac.hansung.cse.hellospringdatajpa.service;

import java.util.List;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyRole;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyUser;
import kr.ac.hansung.cse.hellospringdatajpa.repo.RoleRepository;
import kr.ac.hansung.cse.hellospringdatajpa.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MyUser createUser(MyUser user, List<MyRole> userRoles) {
        for (MyRole role : userRoles) {
            if (roleRepository.findByRoleName(role.getRoleName()).isEmpty()) {
                roleRepository.save(role);
            }
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(userRoles);

        return userRepository.save(user);
    }

    @Override
    public boolean checkEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public MyRole findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseGet(() -> new MyRole(roleName));
    }

    @Override
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }
}
