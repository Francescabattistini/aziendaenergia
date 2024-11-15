package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.payloadDTO.ClienteDTO;
import team5.azienda.energia.services.ClienteService;

@RestController
@RequestMapping("/api")
public class ClientiController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clienti")
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "15") int size,
                                       @RequestParam(defaultValue = "nomeContatto") String sortBy) {
        return clienteService.findAll(page, size, sortBy);
    }

    @GetMapping("/clientiByFatturato")
    public Page<Cliente> getAllClientiOrderByFatturato(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "15") int size,
                                                       @RequestParam(defaultValue = "fatturatoAnnuale") String sortBy) {
        return clienteService.findAll(page, size, sortBy);
    }

    @PostMapping("")
    public void saveCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteService.save(clienteDTO);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable long id, @RequestBody ClienteDTO newCliente) {
        return clienteService.updateCliente(id, newCliente);
    }

    @DeleteMapping("/{id}")
    public Cliente deleteCliente(@PathVariable long id) {
        return clienteService.deleteCliente(id);
    }


}
