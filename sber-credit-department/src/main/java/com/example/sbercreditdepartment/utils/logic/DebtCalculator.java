package com.example.sbercreditdepartment.utils.logic;

import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.repository.CreditRepository;
import org.springframework.stereotype.Component;

@Component
public class DebtCalculator {

    private final CreditRepository creditRepository;

    public DebtCalculator(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public int calculateMaximumDebt(RequestDTO dto) {
        int creditType = dto.getCredit();
        Credit credit = creditRepository.findById(creditType).orElseThrow(RuntimeException::new);
        double monthlyPayment = dto.getMonthlyPayment();
        int duration = dto.getDuration();
        double percentagePerMonth = credit.getPercentage() / (100 * 12);
        double maxDebt = monthlyPayment * (1 - Math.pow(1 + percentagePerMonth, -duration)) / percentagePerMonth;

        return (int) Math.floor(maxDebt);
    }

}
