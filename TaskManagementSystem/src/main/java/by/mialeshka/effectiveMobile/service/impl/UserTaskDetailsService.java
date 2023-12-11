package by.mialeshka.effectiveMobile.service.impl;

import by.mialeshka.effectiveMobile.entity.UserTask;
import by.mialeshka.effectiveMobile.repository.UserTaskRepository;
import by.mialeshka.effectiveMobile.security.TaskUsrDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserTaskDetailsService implements UserDetailsService {
    @Autowired
    private UserTaskRepository userTaskRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTask user = userTaskRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Not found user = " + username);
        }

        return new TaskUsrDetails(user);
    }

    public static String currentUser () {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
