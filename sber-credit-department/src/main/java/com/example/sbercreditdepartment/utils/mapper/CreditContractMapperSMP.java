package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.repository.ManagerRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class CreditContractMapperSMP {

    private final UserRepository userRepository;
    private final CreditRepository creditRepository;
    private final ManagerRepository managerRepository;

    public CreditContractMapperSMP(UserRepository userRepository, CreditRepository creditRepository, ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.creditRepository = creditRepository;
        this.managerRepository = managerRepository;
    }

    public CreditContract toEntity(CreditContractDTO dto) {
        CreditContract creditContract = new CreditContract();

        creditContract.setStartDate(Date.valueOf(LocalDate.now()));
        creditContract.setMonthlyPayment(dto.getCurrentDebt() / dto.getDuration());
        creditContract.setCredit(creditRepository.findById(dto.getCredit()).orElseThrow(RuntimeException::new));
        creditContract.setUser(userRepository.findById(dto.getUser()).orElseThrow(RuntimeException::new));
        creditContract.setDuration(dto.getDuration());
        creditContract.setManager(managerRepository.findById(dto.getManager()).orElseThrow(RuntimeException::new));
        creditContract.setCurrentDebt(dto.getCurrentDebt());

        return creditContract;
    }

}
