package com.financial.loan.domain.entity;


import java.util.function.Consumer;
import java.util.function.Function;


/**
 * @param <T> тип успешного значения
 */
public class Result<T>
{

    private final boolean isSuccess;
    private final T value;
    private final String error;

    private Result(boolean isSuccess, T value, String error)
    {
        this.isSuccess = isSuccess;
        this.value = value;
        this.error = error;
    }

    /**
     * Создает успешный результат со значением.
     *
     * @param value значение успешного результата
     * @param <T> тип значения
     * @return успешный результат
     */
    public static <T> Result<T> success(T value)
    {
        return new Result<>(true, value, null);
    }

    /**
     * Создает успешный результат без значения (void).
     *
     * @param <T> тип значения
     * @return успешный результат
     */
    public static <T> Result<T> success() {
        return new Result<>(true, null, null);
    }

    /**
     * Создает неудачный результат с сообщением об ошибке.
     *
     * @param error сообщение об ошибке
     * @param <T> тип значения
     * @return неудачный результат
     */
    public static <T> Result<T> failure(String error) {
        return new Result<>(false, null, error);
    }

    /**
     * Возвращает true, если результат успешный.
     *
     * @return true если успешный, иначе false
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Возвращает true, если результат неудачный.
     *
     * @return true если неудачный, иначе false
     */
    public boolean isFailure() {
        return !isSuccess;
    }

    /**
     * Возвращает значение успешного результата.
     *
     * @return значение
     * @throws IllegalStateException если результат неудачный
     */
    public T getValue() {
        if (!isSuccess) {
            throw new IllegalStateException("Нельзя получить значение из неудачного результата. Ошибка: " + error);
        }
        return value;
    }

    /**
     * Возвращает сообщение об ошибке.
     *
     * @return сообщение об ошибке
     * @throws IllegalStateException если результат успешный
     */
    public String getError() {
        if (isSuccess) {
            throw new IllegalStateException("Нельзя получить ошибку из успешного результата");
        }
        return error;
    }


    /**
     * Преобразует значение успешного результата в другое значение.
     *
     * @param mapper функция преобразования
     * @param <U> тип нового значения
     * @return новый результат с преобразованным значением
     */
    public <U> Result<U> map(Function<? super T, ? extends U> mapper)
    {
        if (isSuccess) {
            try {
                return Result.success(mapper.apply(value));
            } catch (Exception e) {
                return Result.failure(e.getMessage());
            }
        }
        return Result.failure(error);
    }






    /**
     * Связывает значение успешного результата с новым результатом.
     *
     * @param binder функция, возвращающая новый Result
     * @param <U> тип значения нового результата
     * @return результат из функции связывания
     */
    public <U> Result<U> bind(Function<? super T, Result<U>> binder) {
        if (isSuccess) {
            return binder.apply(value);
        }
        return Result.failure(error);
    }

    /**
     * Выполняет действие над значением, если результат успешный.
     *
     * @param action действие для выполнения
     * @return текущий результат для цепочки вызовов
     */
    public Result<T> onSuccess(Consumer<? super T> action) {
        if (isSuccess) {
            action.accept(value);
        }
        return this;
    }

    /**
     * Выполняет действие над ошибкой, если результат неудачный.
     *
     * @param action действие для выполнения
     * @return текущий результат для цепочки вызовов
     */
    public Result<T> onFailure(Consumer<String> action)
    {
        if (!isSuccess) {
            action.accept(error);
        }
        return this;
    }

    /**
     * Возвращает значение или значение по умолчанию, если результат неудачный.
     *
     * @param defaultValue значение по умолчанию
     * @return значение успешного результата или defaultValue
     */
    public T getValueOrElse(T defaultValue) {
        return isSuccess ? value : defaultValue;
    }

    @Override
    public String toString() {
        if (isSuccess) {
            return "Успех{значение=" + value + "}";
        }
        return "Ошибка{сообщение='" + error + "'}";
    }


}