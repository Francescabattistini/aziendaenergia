package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Role;
import team5.azienda.energia.repositories.RoleRepo;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    //DA MODIFICARE POI
    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public Page<Role> findAllRoles(int size, int page, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return roleRepo.findAll(pageable);
    }

    public Optional<Role> findById(Long id) {
        return roleRepo.findById(id);
    }

    //DA MODIFICARE POI
    /*
    public Role updateRole(Long id, Role roleDetails) {
        return roleRepo.findById(id).map(role -> {
            role.setRuolo(roleDetails.getRuolo());
            role.setUsers(roleDetails.getUsers());
            return roleRepo.save(role);
        }).orElseThrow(() -> new NotFoundException("Ruolo non trovato con ID: " + id));
    }
    //DA MODIFICARE POI

    public void findByIdAndDelete(Long id) {
        Role role = roleRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Ruolo non trovato con ID: " + id));
        roleRepo.delete(role);
    }
     */
}
