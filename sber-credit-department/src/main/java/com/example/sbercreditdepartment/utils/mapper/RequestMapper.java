package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.model.Request;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestMapper extends GenericMapper<Request, RequestDTO> {


    public RequestMapper(ModelMapper modelMapper) {
        super(Request.class, RequestDTO.class, modelMapper);
    }

    @Override
    protected void mapSpecificFields(RequestDTO source, Request destination) {

    }

    @Override
    protected void mapSpecificFields(Request source, RequestDTO destination) {

    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Request.class, RequestDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(RequestDTO.class, Request.class).setPostConverter(toEntityConverter());
    }

    @Override
    protected List<Integer> getIds(Request entity) {
        return null;
    }
}
