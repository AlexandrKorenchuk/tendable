package com.example.presentation.utils;

import java.util.List;

public interface PayloadFunction<T> {

    List<Object> getChangePayload(T oldItem, T newItem);
}
