package ca.kallanou.itunesfinder.domain.framework

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>