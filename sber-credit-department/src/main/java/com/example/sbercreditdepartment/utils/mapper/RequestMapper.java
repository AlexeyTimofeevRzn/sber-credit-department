package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.exception.CreditNotFoundException;
import com.example.sbercreditdepartment.exception.ManagerNotFoundException;
import com.example.sbercreditdepartment.exception.UserNotFoundException;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.model.User;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.repository.ManagerRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RequestMapper extends GenericMapper<Request, RequestDTO> {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final CreditRepository creditRepository;

    public RequestMapper(ModelMapper modelMapper, UserRepository userRepository, ManagerRepository managerRepository, CreditRepository creditRepository) {
        super(Request.class, RequestDTO.class, modelMapper);
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
        this.creditRepository = creditRepository;
    }

    @Override
    protected void mapSpecificFields(RequestDTO source, Request destination) {
        destination.setUser(userRepository.findById(source.getUser()).orElseThrow(UserNotFoundException::new));
        destination.setManager(managerRepository.findById(source.getManager()).orElse(null));
        destination.setCredit(creditRepository.findById(source.getCredit()).orElseThrow(CreditNotFoundException::new));
    }

    @Override
    protected void mapSpecificFields(Request source, RequestDTO destination) {
        User user = source.getUser();
        Manager manager = source.getManager();
        Credit credit = source.getCredit();
        if (!Objects.isNull(user)) {
            destination.setUser(user.getId());
        }
        if (!Objects.isNull(manager)) {
            destination.setManager(manager.getId());
        }
        if (!Objects.isNull(credit)) {
            destination.setCredit(credit.getId());
        }
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Request.class, RequestDTO.class)
                .addMappings(mapping -> mapping.skip(RequestDTO::setManager))
                .addMappings(mapping -> mapping.skip(RequestDTO::setUser))
                .addMappings(mapping -> mapping.skip(RequestDTO::setCredit))
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(RequestDTO.class, Request.class)
                .addMappings(mapping -> mapping.skip(Request::setManager))
                .addMappings(mapping -> mapping.skip(Request::setUser))
                .addMappings(mapping -> mapping.skip(Request::setCredit))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected List<Integer> getIds(Request entity) {
        return null;
    }
}
