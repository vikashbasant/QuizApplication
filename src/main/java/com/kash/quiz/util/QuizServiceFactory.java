package com.kash.quiz.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizServiceFactory {
    private final Map<QuizServiceType, QuizService> serviceMap = new EnumMap<>(QuizServiceType.class);

    @Autowired
    public QuizServiceFactory (Set<QuizService> actionSet) {
        actionSet.forEach(this::createAction);
    }

    private void createAction(QuizService service) {
        serviceMap.put(service.getServiceType(), service);
    }

    public QuizService getService(QuizServiceType type) {
        return Optional.ofNullable(serviceMap.get(type)).orElseThrow(() -> new RuntimeException("Action (" + type +
                ") is not implemented yet"));
    }

}
