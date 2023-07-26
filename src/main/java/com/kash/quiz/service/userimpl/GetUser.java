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


@Service
@Slf4j
@RequiredArgsConstructor
public class GetUser implements QuizService {

    private final UserRepo userRepo;

    private final ModelMapper mMapper;

    private final Response response;

    @Override
    public QuizServiceType getServiceType() {
        return QuizServiceType.GET_USER;
    }

    @Override
    public <T> Response executeService(T t) throws QuizException {

        log.info("=>> GetUser:: Inside executeService Method <<=");
        Integer userId = (Integer) t;

        // => Fetch User With UserId:
        User getUser = userRepo.findById(userId).orElseThrow(() -> new QuizException("User is not Found With UserId: " + userId));

        // => Convert User into UserResponseDTO:
        UserResponseDTO fetchUser = mMapper.map(getUser, UserResponseDTO.class);

        // => Simply Return The Response:
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully Fetch User Info with UserId: " + userId);
        response.setData(fetchUser);

        return response;
    }
}
