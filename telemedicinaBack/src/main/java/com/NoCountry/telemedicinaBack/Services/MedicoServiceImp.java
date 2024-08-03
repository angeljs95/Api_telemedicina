package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Dtos.MedicoDto;
import com.NoCountry.telemedicinaBack.Entity.Medico;
import com.NoCountry.telemedicinaBack.Repository.MedicoRepository;
import com.NoCountry.telemedicinaBack.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImp {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Medico saveeeee(Medico usuario){
        return medicoRepository.save(usuario);

    }

    public List<MedicoDto> listarMedicoPorEspecialidad(String especialidad){
        List<Medico> listar= medicoRepository.findByEspecialidad(especialidad);
        List<MedicoDto> listarDto= listar.stream().map(medico -> modelMapper.map(medico, MedicoDto.class)).collect(Collectors.toList());
        return listarDto;
    }

    public List<MedicoDto> listarMedicoPorCriterio(String filtroBusqueda){
        List<Medico> lista= medicoRepository.buscarPorCriterioPersonalizado(filtroBusqueda);
        List<MedicoDto> listaDto= lista.stream().map(medico -> modelMapper.map(medico, MedicoDto.class)).collect(Collectors.toList());
        return listaDto;

    }


    public Medico savee(MedicoDto m){

        Medico medico= medicoRepository.findById(m.getId()).orElse(null);

        if(medico!= null){
            medico.setEspecialidad(m.getEspecialidad());
            medico.setAnios_experiencia(m.getAnios_experiencia());
            medico.setN_licencia(m.getN_licencia());
            medico.setConsultorio(m.getConsultorio());
            medico.setNum_contacto(m.getNum_contacto());
            medico.setGenero(m.getGenero());
            return medicoRepository.save(medico);
        }
        return null;
    }


    public Medico findByIdMedico(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> listarTodosLosMedicos(){
        return medicoRepository.findAll();
    }

}
