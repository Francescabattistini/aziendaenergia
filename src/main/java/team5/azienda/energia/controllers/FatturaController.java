package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Fattura;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.payloadDTO.FatturaDTO;
import team5.azienda.energia.servicies.FatturaService;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/fattures")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    // 1. GET http://localhost:3001/fattures
    @GetMapping
    public Page<Fattura> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {

        return this.fatturaService.findAllFatture(page, size, sortBy);
    }


    // 2. POST http://localhost:3001/fattures (+ req.body) --> 201
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Fattura save(@RequestBody @Validated FatturaDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.fatturaService.saveFattura(body);
    }


    /*// 3. GET http://localhost:3001/fattures/{userId}
    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId) {
        return this.usersService.findById(userId);
    }


    // 4. PUT http://localhost:3001/fattures/{userId} (+ req.body)
    @PutMapping("/{userId}")
    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody @Validated NewUserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        // Ovunque ci sia un body bisognerebbe validarlo!
        return this.usersService.findByIdAndUpdate(userId, body);
    }


    // 5. DELETE http://localhost:3001/fattures/{userId} --> 204
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId) {
        this.usersService.findByIdAndDelete(userId);
    }*/


}
