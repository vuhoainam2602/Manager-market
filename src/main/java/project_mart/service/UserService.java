package project_mart.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project_mart.model.CustomUserDetail;
import project_mart.model.User;
import project_mart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> search(String key) {
        return userRepository.search(key);
    }

    public void deleteById(Long id) {
        User fromDB = userRepository.findById(id).orElse(null);
        if (fromDB == null) {
            return;
        }

        fromDB.setCheck(false);
        userRepository.save(fromDB);

    }

    public void add(User user) {
        user.setCheck(true);
        userRepository.save(user);
    }

    public void update(User user) {
        user.setCheck(true);
        userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }


}
