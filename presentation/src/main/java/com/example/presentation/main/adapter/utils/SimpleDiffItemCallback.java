package com.example.presentation.main.adapter.utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class SimpleDiffItemCallback<T> extends DiffUtil.ItemCallback<T> {

    private final ContentCheckFunction<T> contentCheckFunction;
    private final PayloadFunction<T> payloadFunction;

    public SimpleDiffItemCallback() {
        this(new EqualContentCheckFunction<>());
    }

    public SimpleDiffItemCallback(ContentCheckFunction<T> contentCheckFunction) {
        this(contentCheckFunction, new PayloadDefaultFunction<>());
    }

    public SimpleDiffItemCallback(ContentCheckFunction<T> contentCheckFunction,
                                  PayloadFunction<T> payloadFunction) {
        this.contentCheckFunction = contentCheckFunction;
        this.payloadFunction = payloadFunction;
    }

    @Override
    public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return contentCheckFunction.areContentsTheSame(oldItem, newItem);
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
        return payloadFunction.getChangePayload(oldItem, newItem);
    }

    public static class EqualContentCheckFunction<T> implements ContentCheckFunction<T> {

        @Override
        public boolean areContentsTheSame(T oldItem, T newItem) {
            return oldItem.equals(newItem);
        }
    }

    public static class PayloadDefaultFunction<T> implements PayloadFunction<T> {

        @Override
        public List<Object> getChangePayload(T oldItem, T newItem) {
            return null;
        }
    }
}
