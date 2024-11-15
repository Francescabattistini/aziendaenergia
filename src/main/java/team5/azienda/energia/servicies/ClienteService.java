package team5.azienda.energia.servicies;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloads.ClienteDTO;
import team5.azienda.energia.repositories.ClienteRepo;

import java.io.IOException;


@Service
public class ClienteService {

    @Autowired
    private Cloudinary cloudinaryUploader;


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
    public String uploadLogoAziendale(MultipartFile file, long id) {

        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        Cliente found = clienteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        found.setLogoAziendale(url);
        this.clienteRepo.save(found);

        return url;
    }


}
