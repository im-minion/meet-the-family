package io.vaibhav.mtf.executor;

import io.vaibhav.mtf.constants.Gender;

public interface IExecutor {
    void addChild(String motherName, String name, Gender gender);

    void getRelation(String name, String relation);
}
