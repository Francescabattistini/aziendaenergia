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
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloads.ClienteDTO;
import team5.azienda.energia.repositories.ClienteRepo;

import java.io.IOException;
import java.time.LocalDate;


@Service
public class ClienteService {

    @Autowired
    private Cloudinary cloudinaryUploader;

    @Autowired
    private ClienteRepo clienteRepo;

    public void save(ClienteDTO newCliente) {
        clienteRepo.save(mapToEntity(newCliente));
    }

    public Page<Cliente> findAll(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return clienteRepo.findAll(pageable);
    }

    public Cliente findById(long id) {
        return clienteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
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

        return clienteRepo.save(cliente);
    }

    public Cliente deleteCliente(long id) {
        Cliente cliente = this.findById(id);
        clienteRepo.delete(cliente);
        return cliente;
    }

    private Cliente mapToEntity(ClienteDTO newCliente) {
        return new Cliente(
                newCliente.ragioneSociale(),
                newCliente.dataInserimento(),
                newCliente.dataUltimoContatto(),
                newCliente.fatturatoAnnuale(),
                newCliente.pec(),
                newCliente.telefono(),
                newCliente.emailContatto(),
                newCliente.nomeContatto(),
                newCliente.cognomeContatto(),
                newCliente.telefonoContatto(),
                newCliente.logoAziendale()
        );
    }

    public String uploadLogoAziendale(MultipartFile file, long id) {
        String url;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        Cliente found = clienteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        found.setLogoAziendale(url);
        clienteRepo.save(found);
        return url;
    }

    public Page<Cliente> filterClienti(Double fatturatoAnnuale, LocalDate dataInserimento,
                                       LocalDate dataUltimoContatto, String parteNome,
                                       int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (fatturatoAnnuale != null) {
            return clienteRepo.findByFatturatoAnnualeGreaterThanEqual(fatturatoAnnuale, pageable);
        }
        if (dataInserimento != null) {
            return clienteRepo.findByDataInserimento(dataInserimento, pageable);
        }
        if (dataUltimoContatto != null) {
            return clienteRepo.findByDataUltimoContatto(dataUltimoContatto, pageable);
        }
        if (parteNome != null) {
            return clienteRepo.findByNomeContattoContainingIgnoreCase(parteNome, pageable);
        }
        return clienteRepo.findAll(pageable);
    }
}
