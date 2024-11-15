package team5.azienda.energia.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team5.azienda.energia.entities.Role;
import team5.azienda.energia.entities.User;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloadDTO.UserDTO;
import team5.azienda.energia.repositories.RoleRepo;
import team5.azienda.energia.repositories.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private Cloudinary cloudinaryUploader;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepo roleRepo;

    public User saveUser(UserDTO body) {

        this.userRepository.findByUsername(body.username()).ifPresent(
                existingUser -> {
                    throw new BadRequestException("L'username " + body.username() + " è già in uso!");
                }
        );

        this.userRepository.findByEmail(body.email()).ifPresent(
                existingUser -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        User user = new User();
        user.setUsername(body.username());
        user.setEmail(body.email());
        user.setPassword(body.password());
        user.setNome(body.nome());
        user.setCognome(body.cognome());
        user.setAvatar(body.avatar());

        return userRepository.save(user);
    }


    public Page<User> findAllUsers(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public User updateUser(long id, UserDTO body) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(body.username());
            user.setEmail(body.email());
            user.setPassword(body.password());
            user.setNome(body.nome());
            user.setCognome(body.cognome());
            user.setAvatar(body.avatar());
            return userRepository.save(user);
        }).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        userRepository.delete(user);
    }

    public User aggiungiRoleAUser(long userId, long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new NotFoundException(roleId));
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public User deleteRoleFromUser(long userId, long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new NotFoundException(roleId));
        user.getRoles().remove(role);
        return userRepository.save(user);
    }

    public String uploadImg(MultipartFile file, long id) {

        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        user.setAvatar(url);
        this.userRepository.save(user);

        return url;

}}
