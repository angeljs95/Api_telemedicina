package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Dtos.HojaClinicaDto;
import com.NoCountry.telemedicinaBack.Entity.Consulta;
import com.NoCountry.telemedicinaBack.Entity.HojaClinica;
import com.NoCountry.telemedicinaBack.Exceptions.MiException;
import com.NoCountry.telemedicinaBack.Repository.HojaClinicaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HojaClinicaService {

    @Autowired
    private HojaClinicaRepository hojaClinicaRepository;
    @Autowired
    private ModelMapper modelMapper;


    public void guardarHoja(HojaClinica hojaClinica){
        hojaClinicaRepository.save(hojaClinica);
    }

    public void crearHoja(Consulta consulta){
      //  HojaClinica hojaClinica= new HojaClinica(consulta);
        hojaClinicaRepository.save(new HojaClinica(consulta));

    }
    public List<HojaClinicaDto> verHistorialMedico(Long pacienteId){
        List<HojaClinica> lista= hojaClinicaRepository.findByPacienteId(pacienteId);
        List<HojaClinicaDto> listaDto= lista.stream().map(hojaClinica -> modelMapper.map(hojaClinica, HojaClinicaDto.class))
                .collect(Collectors.toList());
        return listaDto;
    }

    public void actualizarHojaClinica(HojaClinicaDto hojaClinica) throws MiException{
        validar(hojaClinica);
        HojaClinica h= hojaClinicaRepository.findByConsultaId(hojaClinica.getConsultaId());
        h.setDiagnostico(hojaClinica.getDiagnostico());
        h.setNotasMedicas(hojaClinica.getNotasMedicas());
        h.setTratamiento(hojaClinica.getTratamiento());
        h.setSintomatologia(hojaClinica.getSintomatologia());
        hojaClinicaRepository.save(h);
    }
    public HojaClinicaDto traerHojaClinica (Long consultaId){

       HojaClinica hojaClinica= hojaClinicaRepository.findByConsultaId(consultaId);

       HojaClinicaDto dto= modelMapper.map(hojaClinica, HojaClinicaDto.class);
        return  dto;

    }

    public HojaClinica verHojaClinica(Long id){
        return hojaClinicaRepository.findById(id).orElse(null);
    }

    public boolean borrarHoja(Long id){
         hojaClinicaRepository.deleteById(id);
         return true;
    }

    public void validar(HojaClinicaDto hojaClinicaDto)  throws MiException {
//        if(hojaClinicaDto.getId()== null){
//            throw new MiException("no se puede actualizar ya que no hay un Id asignado");
//        }

        if( hojaClinicaDto.getSintomatologia().isEmpty() || hojaClinicaDto.getSintomatologia()== null){
            throw new MiException(" Debe agregar Sintomatologia del paciente");
        }

        if(hojaClinicaDto.getDiagnostico().isEmpty() || hojaClinicaDto.getDiagnostico()== null){
            throw new MiException("Debe asignar un diagnostico segun lo visto en la consulta");
        }
        if (hojaClinicaDto.getNotasMedicas().isEmpty()|| hojaClinicaDto.getNotasMedicas()== null){
            throw new MiException("Debe añadir comentarios a su consulta");
        }
    }


}
