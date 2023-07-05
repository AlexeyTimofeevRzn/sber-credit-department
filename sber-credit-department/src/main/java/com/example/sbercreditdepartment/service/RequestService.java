package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.ManagerDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.dto.TwoDatesDTO;
import com.example.sbercreditdepartment.enums.RequestStatus;
import com.example.sbercreditdepartment.exception.RequestNotFoundException;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.logic.DebtCalculator;
import com.example.sbercreditdepartment.utils.mapper.ManagerMapper;
import com.example.sbercreditdepartment.utils.mapper.RequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final CreditRepository creditRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;
    private final ManagerMapper managerMapper;
    private final DebtCalculator debtCalculator;

    public RequestService(RequestRepository requestRepository, CreditRepository creditRepository, UserRepository userRepository, RequestMapper requestMapper, ManagerMapper managerMapper, DebtCalculator debtCalculator) {
        this.requestRepository = requestRepository;
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
        this.requestMapper = requestMapper;
        this.managerMapper = managerMapper;
        this.debtCalculator = debtCalculator;
    }

    public List<Request> getAllRequests() {
//        return requestMapper.toDTOs(requestRepository.findAll());
        return requestRepository.findAll();
    }

    // TODO: fix work with DTO
    public Request getOneRequest(int id) {
//        return requestMapper.toDTO(requestRepository.findById(id).orElseThrow(RuntimeException::new));
        return requestRepository.findById(id).orElseThrow(RequestNotFoundException::new);
    }

    public void acceptRequest(Request request, ManagerDTO manager) {
        request.setManager(managerMapper.toEntity(manager));
        request.setRequestStatus(RequestStatus.ACCEPTED);
        requestRepository.save(request);
    }

    public void declineRequest(Request request) {
        request.setRequestStatus(RequestStatus.DECLINED);
        requestRepository.save(request);
    }

    public List<Request> getRequestsBetweenTwoDates(TwoDatesDTO dto) {
//        return requestMapper.toDTOs(requestRepository.requestsBetweenTwoDates(dto.getStartDate(), dto.getEndDate()));
        return requestRepository.requestsBetweenTwoDates(dto.getStartDate(), dto.getEndDate());
    }

    public void saveRequest(RequestDTO dto) {
        Request request = requestMapper.toEntity(dto);
        Credit credit = creditRepository.findById(dto.getCredit()).orElseThrow(RuntimeException::new);
        // Тут устанавливается статус по заявке
        if ((dto.getMonthlyPayment() * dto.getDuration()) < credit.getMinimalSum()) {
            request.setRequestStatus(RequestStatus.DECLINED);
        } else {
            request.setRequestStatus(RequestStatus.CONSIDERED);
        }
        // Тут сеттится максимальный долг по запросу
        request.setMaximumDebt(debtCalculator.calculateMaximumDebt(dto));
        // Заглушка (потом тут будет пользователь)
        request.setUser(userRepository.findById(1).orElseThrow(RuntimeException::new));
        request.setCredit(creditRepository.findById(dto.getCredit()).orElseThrow(RuntimeException::new));
        requestRepository.save(request);
    }
}
