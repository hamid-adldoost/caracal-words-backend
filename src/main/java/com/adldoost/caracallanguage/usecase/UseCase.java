package com.adldoost.caracallanguage.usecase;

public interface UseCase<T, R> {

    R execute(T request);
}
