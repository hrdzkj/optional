package com.gxjf.optional;

import java.io.Serializable;
import java.util.*;
import java.util.Objects;

/**
 * A wrapper class that wraps non-null values that may or may not be there. As this wrapper acts as a
 * monad, it is possible to safely perform chained operations on wrapped values that may or may not exist.
 *
 * As this is a value-based class, equals and hashcode operations are simply forwarded to the wrapped object.
 * This may cause unexpected behavior for some identity-sensitive operations, therefore equals and hascode operations
 * should generally be avoided on wrappers.
 *
 * @param <T> the type of the wrapped value.
 */
public final class Optional<T> implements Serializable {
    //private static final Empty<?> EMPTY = new Empty<>();
    private static final Optional<?> EMPTY = new Optional();
    protected final T value;
    private Optional() {
        this.value = null;
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> empty() {
        Optional var0 = EMPTY;
        return var0;
    }

    private Optional(T var1) {
        this.value = java.util.Objects.requireNonNull(var1);
    }
    /**
     * Wraps a value in a non-empty wrapper if the value is non-null and returns the wrapped value,
     * returns an empty wrapper in case the value is null.
     *
     * @param value the nullable value to wrap.
     * @param <T>   the type of the value to be wrapped.
     * @return the wrapped value or an empty wrapper if the value is null.
     */
    public static <T> Optional<T> ofNullable(T value) {
        return value == null?empty():of(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> of(T value) {
        return  new Optional(value);
    }




    /**
     * Extracts the value from the wrapper. Throws a NoSuchElementException if
     * performed on an empty wrapper.
     * @return the unwrapped value.
     */
    public T get() {
        if(this.value == null) {
            throw new NoSuchElementException("No value present");
        } else {
            return this.value;
        }
    }


    /**
     * Returns a wrapped object obtained from a function if performed on a non-empty wrapper.
     *
     * @param function the factory function that generates the object.
     * @param <S>      the type of the transformed object;
     * @return the transformed object or an empty wrapper if executed on an empty-wrapper.
     */
    public <S> Optional<S> map(Function1<? super T, S> function) {
        java.util.Objects.requireNonNull(function);
        return !this.isPresent() ? empty() : ofNullable(function.apply(this.value));
        // return of(function.apply(value));
    }

    /**
     * If the predicate evaluates to true, returns the instance, otherwise returns an empty
     * wrapper.
     *
     * @param predicate the predicate function to evaluated.
     * @return the same instance of the wrapped object.
     */
    public Optional<T> filter(Predicate<? super T> predicate) {
        java.util.Objects.requireNonNull(predicate);
        return !this.isPresent()?this:(predicate.test(this.value)?this:empty());
        /*
        if (predicate.verify(value)) {
            return this;
        } else {
            return empty();
        }
        */
    }

    /**
     * If performed on a non-empty wrapper, returns the wrapped object returned by the specified
     * function. Otherwise returns an empty wrapper.
     *
     * @param function the function that returns another wrapped object.
     * @param <S>      the type of the new returned object.
     * @return the wrapped object returned by the function.
     */
    public <S> Optional<S> flatMap(Function1<? super T, Optional<S>> function) {
        // return function.apply(value);
        java.util.Objects.requireNonNull(function);
        return !this.isPresent()?empty(): java.util.Objects.requireNonNull(function.apply(this.value));
    }

    /**
     * Tells whether this wrapper is non-empty or empty.
     *
     * @return true if the wrapper is non-empty, false if empty.
     */
    public boolean isPresent() {
        return this.value != null;
        // return true;
    }

    /**
     * Calls a function if called on a non-empty wrapper.
     *
     * @param action1 the function to call.
     */
    public void doIfPresent(Action1<T> action1) {
        // action1.accept(value);
        if(this.value != null) {
            action1.accept(this.value);
        }
    }

    /**
     * Returns the unwrapped value if called on a non-empty wrapper, or returns the specified value if called
     * on an empty wrapper.
     *
     * @param alternative the value to return in case the wrapper is empty.
     * @return either the unwrapped value or the specified one.
     */
    public T orElse(T alternative) {
        // return value;
        return this.value != null?this.value:alternative;
    }

    /**
     * Returns the unwrapped value if called on a non-empty wrapper, or returns the value returned by the
     * specified function if called on an empty wrapper.
     *
     * @param function the unwrapped value or the specified one.
     * @return either the unwrapped value or the value obtained from the specified function.
     */
    public T orElseGet(Function0<T> function) {
        // return value;
        return this.value != null?this.value:function.apply();
    }

    /**
     * Returns the unwrapped valued if called on a non-empty wrapper, or throws the exeception returned by the
     * specified function if called on an empty wrapper.
     *
     * @param function the function that returns the exception in case the wrapper is empty.
     * @param <X>      the type of the exception
     * @return the exception to throw
     * @throws X the thrown exception.
     */
    public <X extends Throwable> T orElseThrow(Function0<X> function) throws X {
        // return value;
        if(this.value != null) {
            return this.value;
        } else {
            throw function.apply(); //这里有点和源码不一样
        }
    }


    public String toString() {/*
        return "Some {" +
                "value=" + value +
                '}';
                */
        return this.value != null?String.format("Optional[%s]", new Object[]{this.value}):"Optional.empty";
    }

    @Override
    public boolean equals(Object object) {
        /*
        if (this == object) return true;

        if (!(object instanceof Some)) return false;

        final Some some = (Some) object;

        return value.equals(some.value);
        */
        if(this == object) {
            return true;
        } else if(!(object instanceof java.util.Optional)) {
            return false;
        } else {
            final Optional some = (Optional) object;
            return Objects.equals(this.value, some.value);
        }
    }

    // 该方法未修改
    public int hashCode() {
        return value.hashCode();
    }
}