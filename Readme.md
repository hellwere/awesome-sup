Документация по синтаксису для AOP:

Паттерн для написания AOP значений.
execution(modifiers-pattern? return-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)

// Любой метод в пакете service
@Before("execution(* com.example.service.*.*(..))")

// Все методы с возвращаемым типом String
@Before("execution(String com.example..*(..))")

// Все public методы контроллеров
@Before("execution(public * com.example.controller..*(..))")

// Все методы внутри пакета controller
@Pointcut("within(com.example.controller.*)")

// Все методы внутри пакета controller и подпакетов
@Pointcut("within(com.example.controller..*)")

this — если прокси реализует указанный тип.
target — если реальный объект является указанным типом.
@Pointcut("this(com.example.service.MyService)")

// Методы с одним аргументом типа Long
@Before("execution(* *(Long))")
// Методы с двумя аргументами: String и int
@Before("execution(* *(String, int))")

Перехватывает методы с определённой аннотацией.
@Before("@annotation(org.springframework.transaction.annotation.Transactional)")

// Комбинирование (&&, ||, !)
// Все методы в пакете service, кроме аннотированных @Deprecated
@Pointcut("execution(* com.example.service..*(..)) && !@annotation(java.lang.Deprecated)")