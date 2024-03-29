package com.usc.crud.service;

import com.usc.crud.model.Empleado;
import com.usc.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<Empleado> finByUser(Empleado empleado){

        return repository.findById(empleado.getId());

    }
    public Empleado guardarUser(Empleado empleado){

        return repository.save (empleado);

    }
    public List<Empleado> buscarTdoso(){

        return (List<Empleado>) repository.findAll();

    }

    public Object updateUser(Empleado empleadoActualizado, Long id) {
        String msj = "";
        Optional<Empleado> userDb = repository.findById(id);
        if(userDb.isPresent()) {
            Empleado empleadoExistente = userDb.get();
            empleadoExistente.setUser(empleadoActualizado.getUser());
            empleadoExistente.setEmail(empleadoActualizado.getEmail());
            empleadoExistente.setPassword(empleadoActualizado.getPassword());
            return repository.save(empleadoExistente);
        } else {
            msj = "Usuario no encontrado";
        }
        return msj;
    }
    public String  eliminarUser(Long id){

        String msj = "";
        Optional<Empleado> userDb = repository.findById (id);
        if(!userDb.isEmpty()){
            repository.delete(userDb.get());
            msj = "usuario eliminado correctamente";
        }
        else{
            msj = "usuario no encontradpo";
        }

return msj;
    }
}
