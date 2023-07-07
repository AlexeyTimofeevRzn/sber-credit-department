package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.exception.CreditNotFoundException;
import com.example.sbercreditdepartment.model.Credit;
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
        return creditMapper.toDTOs(creditRepository.findCreditsNotDeleted());
    }

    public CreditDTO getOneCredit(int id) {
        return creditMapper.toDTO(creditRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    public Credit softDelete(int id) {
        Credit credit = creditRepository.findById(id).orElseThrow(CreditNotFoundException::new);
        credit.setDeleted(true);
        return creditRepository.save(credit);
    }
}
