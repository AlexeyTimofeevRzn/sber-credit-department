package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.exception.CreditNotFoundException;
import com.example.sbercreditdepartment.exception.ManagerNotFoundException;
import com.example.sbercreditdepartment.exception.UserNotFoundException;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.repository.ManagerRepository;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditContractMapper extends GenericMapper<CreditContract, CreditContractDTO> {

    private final UserRepository userRepository;
    private final CreditRepository creditRepository;
    private final ManagerRepository managerRepository;
    private final RequestRepository requestRepository;

    public CreditContractMapper(ModelMapper modelMapper, UserRepository userRepository, CreditRepository creditRepository, ManagerRepository managerRepository, RequestRepository requestRepository) {
        super(CreditContract.class, CreditContractDTO.class, modelMapper);
        this.userRepository = userRepository;
        this.creditRepository = creditRepository;
        this.managerRepository = managerRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    protected void mapSpecificFields(CreditContractDTO source, CreditContract destination) {
        destination.setManager(managerRepository.findById(source.getManager()).orElseThrow(ManagerNotFoundException::new));
        destination.setCredit(creditRepository.findById(source.getCredit()).orElseThrow(CreditNotFoundException::new));
        destination.setUser(userRepository.findById(source.getUser()).orElseThrow(UserNotFoundException::new));
        destination.setRequest(requestRepository.findById(source.getRequest()).orElseThrow(RuntimeException::new));
    }

    @Override
    protected void mapSpecificFields(CreditContract source, CreditContractDTO destination) {
        destination.setCredit(source.getCredit().getId());
        destination.setUser(source.getUser().getId());
        destination.setManager(source.getManager().getId());
        if (source.getRequest() != null) {
            destination.setRequest(source.getRequest().getId());

        } else {
            // Заглушка
            destination.setRequest(0);
        }
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(CreditContract.class, CreditContractDTO.class)
                .addMappings(mapping -> mapping.skip(CreditContractDTO::setCredit))
                .addMappings(mapping -> mapping.skip(CreditContractDTO::setManager))
                .addMappings(mapping -> mapping.skip(CreditContractDTO::setUser))
                .addMappings(mapping -> mapping.skip(CreditContractDTO::setRequest))
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(CreditContractDTO.class, CreditContract.class)
                .addMappings(mapping -> mapping.skip(CreditContract::setCredit))
                .addMappings(mapping -> mapping.skip(CreditContract::setManager))
                .addMappings(mapping -> mapping.skip(CreditContract::setUser))
                .addMappings(mapping -> mapping.skip(CreditContract::setRequest))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected List<Integer> getIds(CreditContract entity) {
        return null;
    }
}
