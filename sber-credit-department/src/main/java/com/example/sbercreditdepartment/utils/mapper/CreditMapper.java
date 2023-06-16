package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.dto.GenericDTO;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.model.GenericModel;
import jakarta.persistence.Column;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditMapper extends GenericMapper<Credit, CreditDTO> {


    public CreditMapper(ModelMapper modelMapper) {
        super(Credit.class, CreditDTO.class, modelMapper);
    }

    @Override
    protected void mapSpecificFields(CreditDTO source, Credit destination) {

    }

    @Override
    protected void mapSpecificFields(Credit source, CreditDTO destination) {

    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Credit.class, CreditDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(CreditDTO.class, Credit.class).setPostConverter(toEntityConverter());
    }

    @Override
    protected List<Integer> getIds(Credit entity) {
        return null;
    }
}
