package com.rationaleemotions.extensions;

import java.util.concurrent.atomic.AtomicInteger;

public interface CounterAware {

  void setCounter(AtomicInteger counter);
}
