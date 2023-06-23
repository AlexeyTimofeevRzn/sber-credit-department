package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.repository.CreditContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditContractService {

    private final CreditContractRepository creditContractRepository;

    public CreditContractService(CreditContractRepository creditContractRepository) {
        this.creditContractRepository = creditContractRepository;
    }

    public List<CreditContract> getAllContracts() {
        return creditContractRepository.findAll();
    }

    public CreditContract getContract(int id) {
        return creditContractRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void saveContract(CreditContractDTO dto) {
        creditContractRepository.save(new CreditContract());
    }

}
