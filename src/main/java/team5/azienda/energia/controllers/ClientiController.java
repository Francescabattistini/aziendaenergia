package team5.azienda.energia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.payloadDTO.ClienteDTO;
import team5.azienda.energia.servicies.ClienteService;

@RestController
@RequestMapping("/api/clienti")
public class ClientiController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "15") int size,
                                       @RequestParam(defaultValue = "nomeContatto") String sortBy,
                                       @RequestParam(defaultValue = "ASC") String direction) {
        return clienteService.findAll(page, size, sortBy, direction);
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


    @GetMapping("/filtra")
    public Page<Cliente> filtraClienti(
            @RequestParam(required = false) Double fatturatoAnnuale,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInserimento,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataUltimoContatto,
            @RequestParam(required = false) String parteNome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomeContatto") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        return clienteService.filterClienti(fatturatoAnnuale, dataInserimento, dataUltimoContatto, parteNome, page, size, sortBy, direction);
    }




}
