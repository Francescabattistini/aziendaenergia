package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.payloadDTO.ClienteDTO;
import team5.azienda.energia.repositories.ClienteRepo;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepo clienteRepo;

    public void save(ClienteDTO newCliente) {
        clienteRepo.save(mapToEntity(newCliente));
    }

    public Page<Cliente> findAll(int page, int size, String sortBy) {
        if (size >= 100) size = 100;
        return clienteRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    public Cliente findById(long id) {
        return clienteRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    public Cliente updateCliente(long id, ClienteDTO requestBody) {
        Cliente cliente = this.findById(id);

        cliente.setRagioneSociale(requestBody.ragioneSociale());
        cliente.setPec(requestBody.pec());
        cliente.setCognomeContatto(requestBody.cognomeContatto());
        cliente.setFatturatoAnnuale(requestBody.fatturatoAnnuale());
        cliente.setEmailContatto(requestBody.emailContatto());
        cliente.setTelefono(requestBody.telefono());
        cliente.setLogoAziendale(requestBody.logoAziendale());
        cliente.setNomeContatto(requestBody.nomeContatto());
        cliente.setCognomeContatto(requestBody.cognomeContatto());

        return clienteRepo.save(cliente);
    }

    public Cliente deleteCliente(long id) {
        Cliente cliente = this.findById(id);
        clienteRepo.delete(cliente);

        return cliente;
    }

    private Cliente mapToEntity(ClienteDTO newCliente) {
        return new Cliente(newCliente.ragioneSociale(), newCliente.dataInserimento(),
                newCliente.dataUltimoContatto(), newCliente.fatturatoAnnuale(), newCliente.pec(),
                newCliente.telefono(), newCliente.emailContatto(), newCliente.nomeContatto(),
                newCliente.cognomeContatto(), newCliente.telefonoContatto(), newCliente.logoAziendale());
    }


}
