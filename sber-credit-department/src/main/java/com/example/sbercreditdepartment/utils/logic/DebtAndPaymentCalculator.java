package com.example.sbercreditdepartment.utils.logic;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.exception.CreditNotFoundException;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.repository.CreditRepository;
import org.springframework.stereotype.Component;

@Component
public class DebtAndPaymentCalculator {

    private final CreditRepository creditRepository;

    public DebtAndPaymentCalculator(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public int calculateMaximumDebt(RequestDTO dto) {
        int creditType = dto.getCredit();
        Credit credit = creditRepository.findById(creditType).orElseThrow(RuntimeException::new);
        double percentage = credit.getPercentage();
        double monthlyPayment = dto.getMonthlyPayment();
        int duration = dto.getDuration();
        double p = monthlyPayment * duration;
        double maxDebt = p / (1 + (percentage / 100) * ((duration + 0.0) / 12));
//        double maxDebt = monthlyPayment * (1 - Math.pow(1 + percentagePerMonth, -duration)) / percentagePerMonth;

        return (int) Math.floor(maxDebt);
    }

    public double calculateMonthlyPayment(CreditContractDTO dto) {
        double summ = dto.getCurrentDebt();
        int duration = dto.getDuration();
        Credit credit = creditRepository.findById(dto.getCredit()).orElseThrow(CreditNotFoundException::new);
        double proc = credit.getPercentage() / (100 * 12);
        double payment = summ * proc / (1 - Math.pow(1 + proc, -duration));
        return payment;
    }

}
