package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team5.azienda.energia.entities.Cliente;
<<<<<<< Updated upstream
import team5.azienda.energia.payloadDTO.ClienteDTO;
import team5.azienda.energia.servicies.ClienteService;
=======
import team5.azienda.energia.payloads.ClienteDTO;
import team5.azienda.energia.services.ClienteService;

import java.time.LocalDate;
>>>>>>> Stashed changes

@RestController
@RequestMapping("/api/clienti")
public class ClientiController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "15") int size,
                                       @RequestParam(defaultValue = "nomeContatto") String sortBy) {
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

    @PatchMapping("/{clienteId}/logo")
    public String uploadLogo(@PathVariable("clienteId")long id, @RequestParam("logo")MultipartFile file) {
        return this.clienteService.uploadLogoAziendale(file, id);
    }

}
