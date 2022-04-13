package com.example.presentation.utils;

public interface ContentCheckFunction<T> {

    boolean areContentsTheSame(T oldItem, T newItem);
}
