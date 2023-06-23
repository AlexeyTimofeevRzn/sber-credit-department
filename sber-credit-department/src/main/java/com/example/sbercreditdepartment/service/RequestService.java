package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.enums.RequestStatus;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.mapper.RequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    private final CreditRepository creditRepository;

    private final UserRepository userRepository;

    private final RequestMapper requestMapper;

    public RequestService(RequestRepository requestRepository, CreditRepository creditRepository, UserRepository userRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
        this.requestMapper = requestMapper;
    }

    public List<Request> getAllRequests() {
//        return requestMapper.toDTOs(requestRepository.findAll());
        return requestRepository.findAll();
    }

    public Request getOneRequest(int id) {
//        return requestMapper.toDTO(requestRepository.findById(id).orElseThrow(RuntimeException::new));
        return requestRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void acceptRequest(Request request) {
        request.setRequestStatus(RequestStatus.ACCEPTED);
        requestRepository.save(request);
    }

    public void declineRequest(Request request) {
        request.setRequestStatus(RequestStatus.DECLINED);
        requestRepository.save(request);
    }

    public void saveRequest(RequestDTO dto) {
        Request request = requestMapper.toEntity(dto);
        // Тут устанавливается статус по заявке
        request.setRequestStatus(RequestStatus.CONSIDERED);
        // Тут сеттится максимальный долг по запросу
        request.setMaximumDebt(this.calculateMaximumDebt(dto));
        // Заглушка (потом тут будет пользователь)
        request.setUser(userRepository.findById(1).orElseThrow(RuntimeException::new));
        request.setCredit(creditRepository.findById(dto.getCredit()).orElseThrow(RuntimeException::new));
        requestRepository.save(request);
    }

    // Метод для расчета финального долга
    private double calculateMaximumDebt(RequestDTO dto) {
        int creditType = dto.getCredit();
        Credit credit = creditRepository.findById(creditType).orElseThrow(RuntimeException::new);
        double monthlyPayment = dto.getMonthlyPayment();
        int duration = dto.getDuration();
        double percentagePerMonth = credit.getPercentage() / (100 * 12);

        return monthlyPayment * (1 - Math.pow(1 + percentagePerMonth, -duration)) / percentagePerMonth;
    }
}
