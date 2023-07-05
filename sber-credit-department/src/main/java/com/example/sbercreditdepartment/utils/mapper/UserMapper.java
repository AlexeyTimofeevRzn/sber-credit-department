package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.model.GenericModel;
import com.example.sbercreditdepartment.model.User;
import com.example.sbercreditdepartment.repository.ClientStatusRepository;
import com.example.sbercreditdepartment.repository.CreditContractRepository;
import com.example.sbercreditdepartment.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {

    private final CreditContractRepository creditContractRepository;
    private final ClientStatusRepository clientStatusRepository;
    private final RoleRepository roleRepository;

    public UserMapper(ModelMapper modelMapper, CreditContractRepository creditContractRepository, ClientStatusRepository clientStatusRepository, RoleRepository roleRepository) {
        super(User.class, UserDTO.class, modelMapper);
        this.creditContractRepository = creditContractRepository;
        this.clientStatusRepository = clientStatusRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getContracts())) {
            destination.setContracts(creditContractRepository.findAllById(source.getContracts()));
        } else {
            destination.setContracts(Collections.emptyList());
        }
        destination.setClientStatus(clientStatusRepository
                .findById(source.getClientStatus()).orElseThrow(RuntimeException::new));
        destination.setRole(roleRepository.findById(source.getRoleId()).orElseThrow(RuntimeException::new));
    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
        destination.setContracts(getIds(source));
        if (!Objects.isNull(source.getClientStatus())) {
            destination.setClientStatus(source.getClientStatus().getId());
        } else {
            destination.setClientStatus(1);
        }
        if (!Objects.isNull(source.getRole())) {
            destination.setRoleId(source.getRole().getId());
        } else {
            destination.setRoleId(2);
        }
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(mapping -> mapping.skip(UserDTO::setContracts))
                .addMappings(mapping -> mapping.skip(UserDTO::setClientStatus))
                .addMappings(mapping -> mapping.skip(UserDTO::setRoleId))
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(mapping -> mapping.skip(User::setContracts))
                .addMappings(mapping -> mapping.skip(User::setClientStatus))
                .addMappings(mapping -> mapping.skip(User::setRole))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected List<Integer> getIds(User entity) {
        return entity.getContracts().stream().map(GenericModel::getId).collect(Collectors.toList());
    }
}
