package com.kash.quiz.service.authimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.constant.RoleConstants;
import com.kash.quiz.dao.RoleRepo;
import com.kash.quiz.dao.UserRepo;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Role;
import com.kash.quiz.model.User;
import com.kash.quiz.payload.RegisterRequest;
import com.kash.quiz.payload.Response;
import com.kash.quiz.payload.UserResponseDTO;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterUser implements QuizService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final RoleRepo roleRepo;

    @Autowired
    private final ModelMapper mMapper;

    @Autowired
    private final Response response;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public QuizServiceType getServiceType() {
        return QuizServiceType.REGISTER_USER;
    }

    @Override
    public <T> Response executeService(T t) throws QuizException {

        log.info("=>> RegisterUser:: Inside executeService Method<<=");
        RegisterRequest userInfo = (RegisterRequest) t;

        //=>> Convert userDTO To user:
        User user = mMapper.map(userInfo, User.class);

        /*---- Now We have Encoded The Password ----*/
        user.setUserPassword(this.passwordEncoder.encode(user.getUserPassword()));

        //=> Fetching user role from RegisterRequest:
        String userRole = userInfo.getUserRole();

        //=> Set based on RoleId from the given userRole:
        Integer roleId = userRole.equalsIgnoreCase(RoleConstants.NORMAL_USER_NAME) ? RoleConstants.NORMAL_USER : RoleConstants.ADMIN_USER;

        //=> Fetch Role with the help of roleId:
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new QuizException("Role Not " +
                "Found With RoleId = " + roleId));

        //=> Set the role into user:
        user.getRoles().add(role);

        User saveUser;
        try {
            //=> Now Simply save the user into DB:
            saveUser = userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Please Enter Unique EmailId");
        }

        //=>> Now Again Convert saveUser to userDTO:
        UserResponseDTO registerUser = mMapper.map(saveUser, UserResponseDTO.class);


        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully User Register Into DB!");
        response.setData(registerUser);

        return response;
    }
}
