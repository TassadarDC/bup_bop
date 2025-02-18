package com.pinger.automation.utils;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Arrays;
import java.util.Map;

@Slf4j
public class SoftVerifier extends Assertion {
    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

    @Override
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            a.doAssert();
            onAssertSuccess(a);
            if (a.getMessage() != null) {
                log.info("Assertion Passed: " + a.getMessage());
                Allure.step("Assertion Passed: " + a.getMessage());
            }
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            m_errors.put(ex, a);
        } finally {
            onAfterAssert(a);
        }
    }

    public void verifyAll() {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(ae.getKey().getMessage());
                sb.append("\nStack Trace :");
                sb.append(Arrays.toString(ae.getKey().getStackTrace()).replaceAll(",", "\n"));
            }
            throw new AssertionError(sb.toString());
        }
    }
}

