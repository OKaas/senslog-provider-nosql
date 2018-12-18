package cz.senslog.provider.service;

import cz.senslog.model.db.User;
import cz.senslog.provider.repository.UserRepository;
import cz.senslog.provider.security.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Created by OK on 11/17/2017.
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserToken loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info("Try login: {}", username);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String currentUserName = auth == null ? null : auth.getName();
        }

        User user = userRepository.findByNameEquals(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        UserToken userToken = new UserToken(user.getName(), user.getPassword());
        userToken.setEmail(user.getEmail());
        userToken.setUid(user.getUid());


        // set to context
        final UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(user, null, userToken.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        return userToken;
    }

    /* --- Collaborates --- */

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<? extends GrantedAuthority> authorities;
        authorities = asList(() -> "ROLE_ADMIN", () -> "ROLE_BASIC");

        LOGGER.info("Get authorities for: {}", username);

        return authorities;
    }

    /* --- Getters / Setters --- */

    /* --- Commons  --- */
}




