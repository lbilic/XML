package com.uns.ac.rs.xml.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.domain.entity.User;

/**
 * Implementation of {@link UserDetailsService}.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    //private final UserRepository userRepository;

//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //final User user = userRepository.findByUsername(username);
        // .orElseThrow(() -> new UsernameNotFoundException(String.format("No user with username %s found!", username)));
        //return UserDetailsFactory.create(user);

        User user = new User();
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User '" + username + "' not found"
            );
        }

        return UserDetailsFactory.create(user);

        //AuthorityType type = AuthorityType.REGISTERED_USER;

        /*return org.springframework.security.core.userdetails.User//
                .withUsername(username)//+
                .password(user.getPassword())
                //.passwordEncoder(new BCryptPasswordEncoder()::encode)//
                .authorities(type)//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();*/

        //return UserDetailsFactory.create(user);
    }

}