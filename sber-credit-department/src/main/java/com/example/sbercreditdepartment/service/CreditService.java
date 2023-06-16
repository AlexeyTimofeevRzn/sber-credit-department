package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.utils.mapper.CreditMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    private final CreditRepository creditRepository;

    private final CreditMapper creditMapper;

    public CreditService(CreditRepository creditRepository, CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
    }

    public List<CreditDTO> getAllCredits() {
        return creditMapper.toDTOs(creditRepository.findAll());
    }

    public CreditDTO getOneCredit(int id) {
        return creditMapper.toDTO(creditRepository.findById(id).orElseThrow(RuntimeException::new));
    }
}
