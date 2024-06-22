package com.example.congesabs.entity;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class CumulativeCongesValidator implements ConstraintValidator<CumulativeConges, Date> {

    private String otherFieldName;

    @Override
    public void initialize(CumulativeConges constraintAnnotation) {
        otherFieldName = constraintAnnotation.otherFieldName();    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Demande demande = new Demande();
        PropertyDescriptor property = null;
        try {
            property = new PropertyDescriptor(otherFieldName, Demande.class);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }

        Method readMethod = property.getReadMethod();
        try {
            Object otherFieldValue = readMethod.invoke(demande);
            if (otherFieldValue instanceof Date) {
                Date otherFieldDate = (Date) otherFieldValue;
                TimeZone timeZone = TimeZone.getDefault();
                LocalDate date = LocalDate.ofInstant(value.toInstant(), timeZone.toZoneId());
                LocalDate otherFieldDateLocal = LocalDate.ofInstant(otherFieldDate.toInstant(), timeZone.toZoneId());

                long daysBetween = ChronoUnit.DAYS.between(date, otherFieldDateLocal);

                // Check if the conges is cumulative
                if (isCumulative()) {
                    // If cumulative, take the days on other year
                    return daysBetween >= 0;
                } else {
                    // If not cumulative, don't take the days in other year
                    return daysBetween >= 0 && daysBetween <= 365;
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private boolean isCumulative() {
        // Implement your logic here to determine if the conges is cumulative or not
        // Return true if cumulative, false otherwise
        return true; // Replace this with your actual logic
    }
}