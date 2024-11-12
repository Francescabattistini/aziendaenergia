package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.repositories.ClienteRepo;

import java.util.Optional;


@Service
public class ClienteService {
    @Autowired
    private  ClienteRepo clienteRepo;

    public Cliente save (Cliente cliente) {
        return clienteRepo.save(cliente);
    }

   public Page<Cliente> findAllClienti(int size, int page, String sortBy) {
       if (size > 50) size = 50;
       Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
       return this.clienteRepo.findAll(pageable);
   }
   public Optional<Cliente> findById(long id) {
       return clienteRepo.findById(id); //return this.clienteRepo.findById(id).orElseThrow(() -> new NotFoundException(id)); ---> camobiare con questo una volta che avrò le exception
   }

    public Cliente updateCliente(long id, Cliente clienteDetails) {
        return clienteRepo.findById(id).map(cliente -> {
            cliente.setRagioneSociale(clienteDetails.getRagioneSociale());
            cliente.setEmail(clienteDetails.getEmail());
            cliente.setDataInserimento(clienteDetails.getDataInserimento());
            cliente.setDataUltimoContatto(clienteDetails.getDataUltimoContatto());
            cliente.setFatturatoAnnuale(clienteDetails.getFatturatoAnnuale());
            cliente.setPec(clienteDetails.getPec());
            cliente.setTelefono(clienteDetails.getTelefono());
            cliente.setEmailContatto(clienteDetails.getEmailContatto());
            cliente.setNomeContatto(clienteDetails.getNomeContatto());
            cliente.setCognomeContatto(clienteDetails.getCognomeContatto());
            cliente.setTelefonoContatto(clienteDetails.getTelefonoContatto());
            cliente.setLogoAziendale(clienteDetails.getLogoAziendale());
            return clienteRepo.save(cliente);
        }).orElse(null);
    }


    /*public void findByIdAndDelete(long id) {
        Cliente cliente = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + id));//TODO NOTFOUNDEXCEPTION
        clienteRepo.delete(cliente);
    }*/

}
