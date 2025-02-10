package com.pinger.automation.allure;

import com.pinger.automation.core.enums.annotations.Defect;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class AllureListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        Method testMethod = method.getTestMethod().getConstructorOrMethod().getMethod();

        if (testMethod.isAnnotationPresent(Defect.class)) {
            Defect defectAnnotation = testMethod.getAnnotation(Defect.class);
            String[] defectIds = defectAnnotation.ids();

            for (String defectId : defectIds) {
                if (!defectId.isEmpty()) {
                    Allure.label("Defect", defectId);
                }
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // No implementation needed after test execution
    }
}
