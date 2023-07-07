package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.TwoDatesDTO;
import com.example.sbercreditdepartment.enums.RequestStatus;
import com.example.sbercreditdepartment.exception.RequestNotFoundException;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.repository.CreditContractRepository;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.utils.logic.DebtAndPaymentCalculator;
import com.example.sbercreditdepartment.utils.mapper.CreditContractMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CreditContractService {

    private final CreditContractRepository creditContractRepository;
    private final RequestRepository requestRepository;
    private final CreditContractMapper creditContractMapper;
    private final DebtAndPaymentCalculator debtAndPaymentCalculator;

    public CreditContractService(CreditContractRepository creditContractRepository, RequestRepository requestRepository, CreditContractMapper creditContractMapper, DebtAndPaymentCalculator debtAndPaymentCalculator) {
        this.creditContractRepository = creditContractRepository;
        this.requestRepository = requestRepository;
        this.creditContractMapper = creditContractMapper;
        this.debtAndPaymentCalculator = debtAndPaymentCalculator;
    }

//    public List<CreditContract> getAllContracts() {
//        return creditContractRepository.findAll();
//    }

    public List<CreditContract> getContractsOfManager(int managerId) {
        return creditContractRepository.getCreditContractByManagerId(managerId);
    }


    public CreditContract getContract(int id) {
        return creditContractRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<CreditContract> getContractsBetweenTwoDates(TwoDatesDTO dto) {
        return creditContractRepository.getCreditContractBetweenTwoDates(dto.getStartDate(), dto.getEndDate());
    }

    public void saveContract(CreditContractDTO dto) {
        Request request = requestRepository.findById(dto.getRequest()).orElseThrow(RequestNotFoundException::new);
        request.setRequestStatus(RequestStatus.SIGNED);
        requestRepository.save(request);
        dto.setMonthlyPayment(debtAndPaymentCalculator.calculateMonthlyPayment(dto));
        dto.setStartDate(Date.valueOf(LocalDate.now()));
        creditContractRepository.save(creditContractMapper.toEntity(dto));
    }
}
