package com.kash.quiz.service.userimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.registoruserdto.RegisterRequestDTO;
import com.kash.quiz.dto.registoruserdto.UserResponseDTO;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.User;
import com.kash.quiz.repo.UserRepo;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUser implements QuizService {

    private final UserRepo userRepo;

    private final Response response;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public QuizServiceType getServiceType() {
        return QuizServiceType.UPDATE_USER;
    }

    @Override
    public <T> Response executeService(T t) throws QuizException {

        log.info("=>> GetAllUser:: Inside executeService Method <<=");

        RegisterRequestDTO userInfo = (RegisterRequestDTO) t;

        // => First Find The User With userId:
        User findUser = userRepo.findById(userInfo.getUserId()).orElseThrow(() -> new QuizException(
                "User Not Found With UserId = " + userInfo.getUserId()));

        // => Then Simply Update The User:
        findUser.setName(userInfo.getName());
        findUser.setUserPassword(this.passwordEncoder.encode(userInfo.getUserPassword()));
        findUser.setUserEmail(userInfo.getUserEmail());
        findUser.setUserAbout(userInfo.getUserAbout());


        User sUser;
        try {
            // => Then Simply Save Updated User Into DB:
            sUser = this.userRepo.save(findUser);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Please Enter Unique EmailId");
        }

        // => Convert The uDTO to UserResponseDTO:
        UserResponseDTO userResponseDTO = this.modelMapper.map(sUser, UserResponseDTO.class);

        // => Simply Return The Response:
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully Update The User With UserId = " + userInfo.getUserId());
        response.setData(userResponseDTO);

        return response;
    }
}
