package com.kash.quiz.helper;

import com.kash.quiz.constant.RoleConstants;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Role;
import com.kash.quiz.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationRunnerForUserRole {

    @Autowired
    private final RoleRepo roleRepo;

    public void createRoles() throws QuizException {
        log.info("=>>: ApplicationRunnerForUserRole:: Inside createRoles Method :<<=");

        try {
            //=>> Create a Role for normal user:
            Role normalRole = new Role();
            normalRole.setRoleId(RoleConstants.NORMAL_USER);
            normalRole.setRoleName(RoleConstants.NORMAL_USER_NAME);

            //=>> Create a Role for admin user:
            Role adminRole = new Role();
            adminRole.setRoleId(RoleConstants.ADMIN_USER);
            adminRole.setRoleName(RoleConstants.ADMIN_USER_NAME);

            //=>> Create list of role:
            List<Role> allUserRole = new ArrayList<>(Arrays.asList(normalRole, adminRole));

            //=>> Now Simply Save The List of RoleList:
            roleRepo.saveAll(allUserRole);
        } catch (Exception e) {
            throw new QuizException("Some Wrong while Adding Role into DB.");
        }

    }
}
