package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.payloadDTO.ClienteDTO;
import team5.azienda.energia.servicies.ClienteService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ClientiController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clienti")
    public List<ClienteDTO> getAllClienti(Pageable pageable) {
        return clienteService.findAllClienti(pageable).stream().map(cliente -> new ClienteDTO(cliente.getRagioneSociale(), cliente.getDataInserimento(),
                cliente.getDataUltimoContatto(), cliente.getFatturatoAnnuale(), cliente.getPec(), cliente.getTelefono(),
                cliente.getEmailContatto(), cliente.getNomeContatto(), cliente.getCognomeContatto(),
                cliente.getTelefonoContatto(), cliente.getLogoAziendale())).toList();
    }

    @PostMapping("/cliente")
    public void saveCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteService.save(clienteDTO);
    }
}
