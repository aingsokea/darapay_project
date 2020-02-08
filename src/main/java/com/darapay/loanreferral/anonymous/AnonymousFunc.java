package com.darapay.loanreferral.anonymous;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class AnonymousFunc {
    public static Authentication GetCurrentPrincipalName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
    
    public static String getCondition(String value) {
        String condition = "";
        if (value.equals("16")) {
            condition = "";
        } else if (value.equals("17")) {
            condition = "AND CAST(LR.createddate AS DATE) = '" + LocalDate.now() + "'";
        } else if (value.equals("18")) {
            condition = "AND CAST(LR.createddate AS DATE) = '" + LocalDate.now().minusDays(1L) + "'";
        } else if (value.equals("19")) {
            LocalDate date = LocalDate.now();
            LocalDate start = date.withDayOfMonth(1);
            LocalDate end = date.withDayOfMonth(date.lengthOfMonth());
            condition = "AND CAST(LR.createddate AS DATE) BETWEEN '" + start + "' AND '" + end + "'";
        } else if (value.equals("20")) {
            LocalDate date = LocalDate.now();
            LocalDate start = date.with(firstDayOfYear());
            LocalDate end = date.with(lastDayOfYear());
            condition = "AND CAST(LR.createddate AS DATE) BETWEEN '" + start + "' AND '" + end + "'";
        } else {
            condition = "";
        }

        return condition;
    }

    public static String converFromModelToToJson(Object value) {
        ObjectMapper mapper = new ObjectMapper();
        String aftermap = "";
        try {
            aftermap =  mapper.writeValueAsString(value);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aftermap;
    }

    public static String getCustomBindingKey(Field field) {
        String annotationValue = field.getAnnotation(com.darapay.loanreferral.annotations.ModelBinding.class).value();
        if (annotationValue.isEmpty()) {
            return field.getName();
        }
        else {

            return annotationValue;
        }
    }

    public static void AssignClass(Object fromClass, Object toClass)
    {
        Class<?> setEntity = toClass.getClass();
        Class<?> getModel = fromClass.getClass();

        Arrays.asList(setEntity.getDeclaredFields()).stream()
                .filter(s -> s.isAnnotationPresent(com.darapay.loanreferral.annotations.ModelBinding.class))
                .map(setField -> {
                    setField.setAccessible(true);
                    Field[] getFields = getModel.getDeclaredFields();
                    List<Field> fields = Arrays.asList(getFields);
                    java.util.Optional<Field> field = fields.stream()
                            .filter(s -> s.isAnnotationPresent(com.darapay.loanreferral.annotations.ModelBinding.class)
                                    && getCustomBindingKey(s)
                                    .equals(getCustomBindingKey(setField)))
                            .findFirst();
                    if (field != null && field.isPresent()) {
                        Field getField = field.get();
                        getField.setAccessible(true);
                        Object getValue;
                        try {
                            getValue = getField.get(fromClass);
                            setField.set(toClass, getValue);
                        } catch (IllegalArgumentException | IllegalAccessException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    return setField;
                }).collect(Collectors.toList());
    }
}
