package kr.ac.hansung.cse.hellospringdatajpa.service;

import java.util.List;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyRole;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyUser;

public interface RegistrationService {
    MyUser createUser(MyUser user, List<MyRole> userRoles);

    boolean checkEmailExists(String email);

    MyRole findRoleByRoleName(String roleName);
    
    List<MyUser> getAllUsers();
}
