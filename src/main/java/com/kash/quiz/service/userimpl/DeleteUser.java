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
@RequiredArgsConstructor
@Slf4j
public class DeleteUser implements QuizService {

    private final UserRepo userRepo;

    private final Response response;

    private final ModelMapper mMapper;

    @Override
    public QuizServiceType getServiceType() {
        return QuizServiceType.DELETE_USER;
    }

    @Override
    public <T> Response executeService(T t) throws QuizException {

        log.info("=>>DeleteUser:: Inside executeService Method <<=");

        Integer userId = (Integer) t;

        // => Before delete the User, Fetch the User:
        User getUser = userRepo.findById(userId).orElseThrow(() -> new QuizException("User Not " +
                "Found With UserId = " + userId));

        // => Now Simply Delete the User with userId:
        userRepo.deleteById(userId);

        // => Now Convert the User to UserResponseDTO:
        UserResponseDTO userInfo = this.mMapper.map(getUser, UserResponseDTO.class);

        // => Now Simply Return Response:
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully Delete The User With UserId = " + userId);
        response.setData(userInfo);

        return response;

    }
}
