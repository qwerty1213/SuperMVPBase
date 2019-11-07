package com.android.tool.widget.longimage;

import android.support.annotation.NonNull;

import com.android.tool.util.T;

/**
 * Compatibility factory to instantiate decoders with empty public constructors.
 * @param <T> The base type of the decoder this factory will produce.
 */
public class CompatDecoderFactory <T> implements com.android.tool.widget.longimage.DecoderFactory<T> {
  private Class<? extends T> clazz;

  public CompatDecoderFactory(@NonNull Class<? extends T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public T make() throws IllegalAccessException, InstantiationException {
    return clazz.newInstance();
  }
}
