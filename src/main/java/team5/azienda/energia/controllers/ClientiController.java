package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.payloadDTO.ClienteDTO;
import team5.azienda.energia.servicies.ClienteService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ClientiController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clienti")
    public List<Cliente> getAllClienti(Pageable pageable) {
        return clienteService.findAllClienti(pageable).stream().toList();
    }

    @PostMapping("/save")
    public void saveCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteService.save(clienteDTO);
    }
}
