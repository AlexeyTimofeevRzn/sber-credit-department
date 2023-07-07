package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.ManagerDTO;
import com.example.sbercreditdepartment.model.GenericModel;
import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.repository.CreditContractRepository;
import com.example.sbercreditdepartment.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ManagerMapper extends GenericMapper<Manager, ManagerDTO> {

    private final CreditContractRepository creditContractRepository;
    private final RoleRepository roleRepository;

    public ManagerMapper(ModelMapper modelMapper, CreditContractRepository creditContractRepository, RoleRepository roleRepository) {
        super(Manager.class, ManagerDTO.class, modelMapper);
        this.creditContractRepository = creditContractRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected void mapSpecificFields(ManagerDTO source, Manager destination) {
        if (!Objects.isNull(source.getContracts())) {
            destination.setContracts(creditContractRepository.findAllById(source.getContracts()));
        } else {
            destination.setContracts(Collections.emptyList());
        }
        destination.setRole(roleRepository.findById(source.getRoleId()).orElseThrow(RuntimeException::new));
    }

    @Override
    protected void mapSpecificFields(Manager source, ManagerDTO destination) {
        destination.setContracts(getIds(source));
        if (!Objects.isNull(source.getRole())) {
            destination.setRoleId(source.getRole().getId());
        } else {
            destination.setRoleId(1);
        }
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Manager.class, ManagerDTO.class)
                .addMappings(mapping -> mapping.skip(ManagerDTO::setContracts))
                .addMappings(mapping -> mapping.skip(ManagerDTO::setRoleId))
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(ManagerDTO.class, Manager.class)
                .addMappings(mapping -> mapping.skip(Manager::setContracts))
                .addMappings(mapping -> mapping.skip(Manager::setRole))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected List<Integer> getIds(Manager entity) {
        return entity.getContracts().stream().map(GenericModel::getId).collect(Collectors.toList());
    }
}
