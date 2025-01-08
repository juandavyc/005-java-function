package org.juandavyc;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Function;

public class Main {

    // le puedes pasar un parametro y devuleve ese o otro
    public static void main(String[] args) {

        System.out.println("Hello world!");

        // Function
        // apply

        // compose -> aplica antes
        // anthen aplica despues

        var chain = new StringBuilder("Usando la interface, ");
        // <Tipo entrada, tipo Salida>
        Function<StringBuilder, String> toStringg = in -> in.append("Function<>").toString();

        // recibio un String y devolvio un string
        System.out.println(
                usingFunction(
                        toStringg,
                        chain
                )
        );

        // ej 2;

        var birth = LocalDate.of(1995, 10, 3);
        // aplica la fecha
        Function<LocalDate, Integer> returnAge = date -> date.until(LocalDate.now()).getYears();
        // aplica el entero, la edad y crea un pojo
        Function<Integer, Person> returnPerson = age -> new Person(age); // Person::new;
        // aplica el Person
        Function<Person, String> returnToString = per -> per.toString();

//        System.out.println(
//                "Naci el: "+birth+
//                " tengo: "+(birth.until(LocalDate.now()).getYears())
//        );

        System.out.println(
                "usingFunctionWithCompose: "+
                usingFunctionWithCompose(
                        returnToString,
                        returnPerson,
                        returnAge,
                        birth
                )
        );

        //

        var person = new Person(19);
        Function<Person, Integer> returnAge1 = per -> per.getAge(); //Person::getAge;

        Function<Integer, LocalDate> returnBirth = age -> {
            var now = LocalDate.now();
            return LocalDate.of(now.getYear()-age, now.getMonth(), now.getDayOfMonth());
        };
        Function<LocalDate, String> returnString = date -> date.toString();

        System.out.println(
                "usinFunctionWithAndThen: "+
                usinFunctionWithAndThen(
                        returnAge1,
                        returnBirth,
                        returnString,
                        person
                )
        );

    }

    private static <T, R> R usingFunction(Function<T, R> fun, T value) {
        return fun.apply(value);
    }

    private static <T, R, V, O> O usinFunctionWithAndThen(
            Function<T, R> fun1,
            Function<R, V> fun2,
            Function<V, O> fun3,
            T value
    ) {
        return fun1.andThen(fun2).andThen(fun3).apply(value);
    }

    private static <T, R, V, O> R usingFunctionWithCompose(
            Function<T, R> fun1, //entra y t sale R
            Function<V, T> fun2, // Entra V y sale T
            Function<O, V> fun3, // Entra O y sale V
            O value
    ) {
        // va de derecha a izquierda
        return fun1.compose(fun2).compose(fun3).apply(value);
    }


}