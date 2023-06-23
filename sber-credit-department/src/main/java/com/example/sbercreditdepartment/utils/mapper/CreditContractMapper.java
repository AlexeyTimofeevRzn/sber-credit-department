package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.model.GenericModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditContractMapper extends GenericMapper<CreditContract, CreditContractDTO> {


    public CreditContractMapper(ModelMapper modelMapper) {
        super(CreditContract.class, CreditContractDTO.class, modelMapper);
    }

    @Override
    protected void mapSpecificFields(CreditContractDTO source, CreditContract destination) {

    }

    @Override
    protected void mapSpecificFields(CreditContract source, CreditContractDTO destination) {

    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(CreditContract.class, CreditContractDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(CreditContractDTO.class, CreditContract.class).setPostConverter(toEntityConverter());
    }

    @Override
    protected List<Integer> getIds(CreditContract entity) {
        return null;
    }
}
