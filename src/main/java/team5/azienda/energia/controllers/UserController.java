package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team5.azienda.energia.entities.User;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloadDTO.UserDTO;
import team5.azienda.energia.servicies.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {



    @Autowired
    private UserService userService;

    // 1. GET http://localhost:3005/users
    @GetMapping
    public Page<User> findAll(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.userService.findAllUsers( page, size, sortBy);
    }

    // 2. POST http://localhost:3005/users (+ req.body) --> 201
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated UserDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.userService.saveUser(body);
    }

    // 3. GET http://localhost:3005/users/{userId}
    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId) {
        return this.userService.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));
    }

    // 4. PUT http://localhost:3005/users/{userId} (+ req.body)
    @PutMapping("/{userId}")
    public User findByIdAndUpdate(@PathVariable Long userId,
                                  @RequestBody @Validated UserDTO body,
                                  BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.userService.updateUser(userId, body);
    }
    // 5. DELETE http://localhost:3005/users/{userId} --> 204
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long userId) {
        this.userService.findByIdAndDelete(userId);
    }

    //6.Upload Immagine
    @PatchMapping("/{userId}/img")
    public String uploadAvatar (@PathVariable long userId, @RequestParam("img") MultipartFile file) {
        return this.userService.uploadImg(file, userId);
    }
}
