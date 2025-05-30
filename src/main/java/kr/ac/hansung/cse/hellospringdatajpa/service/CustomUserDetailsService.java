package kr.ac.hansung.cse.hellospringdatajpa.service;

import java.util.Collection;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyRole;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyUser;
import kr.ac.hansung.cse.hellospringdatajpa.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + username + " no exist."));
        return new User(myUser.getEmail(), myUser.getPassword(), getAuthorities(myUser));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(MyUser user) {
        String[] userRoles = user.getRoles()
                .stream()
                .map(MyRole::getRoleName)
                .toArray(String[]::new);

        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
