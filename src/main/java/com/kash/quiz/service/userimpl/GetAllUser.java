package com.kash.quiz.service.userimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.registoruserdto.UserResponseDTO;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.User;
import com.kash.quiz.repo.UserRepo;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllUser implements QuizService {

    private final UserRepo userRepo;

    private final ModelMapper mMapper;

    private final Response response;

    @Override
    public QuizServiceType getServiceType() {
        return QuizServiceType.GET_ALL_USER;
    }

    @Override
    public <T> Response executeService(T t) throws QuizException {

        log.info("=>> GetAllUser:: Inside executeService Method <<=");

        // => Find All The Record Of User:
        List<User> allUser = userRepo.findAll();


        // => If Record Is Empty, Then Simply Throw Exception:
        if (allUser.isEmpty()) {
            throw new QuizException("No Record Found!");
        }

        // => Fetch One User At A Time, Collect Into ArrayList:
        List<UserResponseDTO> listOfUser = allUser.stream().map(user -> mMapper.map(user, UserResponseDTO.class)).toList();

        // => Now Simply Return Response:
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully Fetch All The Record");
        response.setData(listOfUser);

        return response;
    }
}
