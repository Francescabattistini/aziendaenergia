package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Role;
import team5.azienda.energia.entities.User;
import team5.azienda.energia.repositories.RoleRepo;
import team5.azienda.energia.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepo roleRepo;

    //DA MODIFICARE POI
    public User save(User user) {
        return userRepository.save(user);
    }

    public Page<User> findAllUsers(int size, int page, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    //DA MODIFICARE POI
    /*
    public User updateUser(long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setNome(userDetails.getNome());
            user.setCognome(userDetails.getCognome());
            user.setAvatar(userDetails.getAvatar());
            user.setRoles(userDetails.getRoles());
            return userRepository.save(user);
        }).orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));
    }

    //DA MODIFICARE POI
    public void findByIdAndDelete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));
        userRepository.delete(user);
    }

 public User aggiungiRoleAUser(long userId, long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + userId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Ruolo non trovato con ID: " + roleId));
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public User deleteRoleFromUser(long userId, long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + userId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Ruolo non trovato con ID: " + roleId));
        user.getRoles().remove(role);
        return userRepository.save(user);
    }


     */



}
