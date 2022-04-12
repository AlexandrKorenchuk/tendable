package com.example.presentation.main.adapter.utils;

public interface ContentCheckFunction<T> {

    boolean areContentsTheSame(T oldItem, T newItem);
}
