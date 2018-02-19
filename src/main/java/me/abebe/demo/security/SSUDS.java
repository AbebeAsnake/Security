package me.abebe.demo.security;

import me.abebe.demo.model.Roles;
import me.abebe.demo.model.Users;
import me.abebe.demo.repo.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SSUDS implements UserDetailsService {
    private UsersRepository userRepo;

    public SSUDS(UsersRepository userRepository) {
        this.userRepo = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> userAuthorities = new HashSet<>();
        Users thisUser = userRepo.findUsersByUserName(username);
        return new User(thisUser.getUserName(), thisUser.getPassword(), grantedAuthorities(thisUser));
    }

    public Set<GrantedAuthority> grantedAuthorities(Users user) {
        Set<GrantedAuthority> userAuthorities = new HashSet<>();
        for (Roles eachRole : user.getRoles()) {
            userAuthorities.add(new SimpleGrantedAuthority(eachRole.getRoleName()));
        }
        return userAuthorities;
    }
}
