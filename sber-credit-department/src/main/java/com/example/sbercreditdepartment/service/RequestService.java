package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.enums.RequestStatus;
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
        if (((dto.getRequiredDebt() + 1.0) / dto.getDuration()) > dto.getMonthlyPayment()) {
            request.setRequestStatus(RequestStatus.DECLINED);
        } else{
          request.setRequestStatus(RequestStatus.CONSIDERED);
        }
        // Заглушка (потом тут будет пользователь)
        request.setUser(userRepository.findById(1).orElseThrow(RuntimeException::new));
        request.setCredit(creditRepository.findById(dto.getCredit()).orElseThrow(RuntimeException::new));
        requestRepository.save(request);
    }
}
