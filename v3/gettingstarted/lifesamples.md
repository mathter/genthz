---
title: Life samples
description: Life samples ...
layout: libdoc/page

category: Getting started
order: 91
---
* Do not
{:toc}

## HelloWorld sample
It is required to get [`ObjectFactory`](../apidocs/org/genthz/ObjectFactory.html){:target="_blank"} firstly to start
using GenThz Project. It is possible to use a default realization for generating values for your project.
```java
// The simplest way to create ObjectFactory
ObjectFactory objectFactory = new DashaObjectFactory();
```
All java objects are based on simple types such as ``long``, ``int``, ``java.lang.String`` and etc
(all simple types description is [here](../docs/simpletypes)). There
is default realization [`DashaDsl`](../apidocs/org/genthz/dasha/dsl/DashaDsl.html){:target="_blank"}
for generating all such types. And you can use a simple code to produce a new object:
```java
// Creating ObjectFactory using generation provider.
ObjectFactory objectFactory = new DashaObjectFactory();
// Generate string value.
String value = objectFactory.get(String.class);
```

## Complex object generation
What about complex objects? It is very simple! All complex objects consist of simple types such as `long`, `int`,
`java.lang.String` and/or another complext objects. And Generated engine can automatically produce such objects
automatically using [`DefaultFiller`](../apidocs/org/genthz/function/DefaultFiller.html){:target="_blank"}:
```java
public class Person {
    private UUID uuid;
    private String name;
    private String lastName;
    private Date birthDate;

    public Person() {
    }
}

ObjectFactory objectFactory = new DashaObjectFactory();
Person value = objectFactory.get(Person.class);
```

## Custom object generation
### Custom instance builder
Creation of the object consists of two phases: instance creation (using
[constructor](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Constructor.html){:target="_blank"}) phase and
filling one. There are default instance builder and default object filler to create objects. Defaut instance buider uses
class constructor with minimum count of arguments. If you want to create object using instance builder only use
[InstanceBuilderFirst#simple()](../apidocs/org/genthz/dsl/InstanceBuilderFirst.html#simple(org.genthz.function.InstanceBuilder)){:target="_blank"} .
In this case filling phase will be skipped.
```java
import java.util.UUID;

public class Person {
    private UUID uuid;
    private String name;
    private String lastName;
    private Date birthDate;

    public Person() {
    }
}

    // Create custom configuration/
    Person value = new DashaDsl() {
        {
//          Custom generation for Person class strict.
            strict(Person.class)
//              Use instance builder for object creation witout filler.
                    .simple(ctx -> {
                        Person person = new Person();
                        person.setUuid(UUID.randomUUID());
                        person.setName("Oliver");
                        person.setLastName("Brown");
                        person.setBirthday(new Date());

                        return person;
                    });
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
    }.def()
//      Get ObjectFactory
            .objectFactory()
//      Generate Person class object
            .get(Person.class);
```
### Custom filler
Also you can use custom filler with default instance builder.
```java
import java.util.UUID;

public class Person {
    private UUID uuid;
    private String name;
    private String lastName;
    private Date birthDate;

    public Person() {
    }
}

// Create custom configuration
Person value = new DashaDsl() {
        {
//          Custom generation for Person class strict.
            strict(Person.class)
//              Use custom filler to set object fields.
                .filler(ctx -> {
//                  Get instance created by default instance builder.
                    Person person = ctx.instance();
                    person.setUuid(UUID.randomUUID());
                    person.setName("Oliver");
                    person.setLastName("Brown");
                    person.setBirthday(new Date());
                    }));
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        }.def()
//      Get ObjectFactory
         .objectFactory()
//      Generate Person class object
         .get(Person.class);
```
### Both instance builder and filler
Using both and custom instance builder and filler.
```java
import java.util.UUID;

public class Person {
    private UUID uuid;
    private String name;
    private String lastName;
    private Date birthDate;

    public Person() {
    }
}

// Create custom configuration
Person value = new DashaDsl() { 
        {
//          Custom generation for Person class strict.
            strict(Person.class)
//              Use custom instance builder to set object fields.
                .instanceBuilder(ctx -> {
                    Person person = new Person();
                    return person;
                })
//              Use custom filler to set object fields.
                .filler(ctx -> {
//                  Get instance created by default instance builder.
                    Person person = ctx.instance();
                    person.setName("Oliver");
                    person.setLastName("Brown");
                    person.setBirthday(new Date());
                    }));
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        }.def()
//      Get ObjectFactory
         .objectFactory()
//      Generate Person class object
         .get(Person.class);
```
## Recursion
What will happen if there is a recursion in the object structure?
```java
public class Recursion {
    private Recursion recursion;
}
```
Of couse ``java.lang.StackOverflowError`` will be generated by [DefaultFiller](../apidocs/org/genthz/function/DefaultFiller.html){:target="_blank"}!
To avoid this promlem custom default [configuration builder (DashaDsl)](../apidocs/org/genthz/dasha/dsl/DashaDsl.html){:target="_blank"}
contains special filler to break generation.
Gneration depth can be sprecified in [Defaults#defaultMaxGenerationDepth](../apidocs/org/genthz/Defaults.html#defaultMaxGenerationDepth()){:target="_blank"}
```java
public class Recursion {
    private Recursion recursion;
}

// Create custom configuration.
Recursion value = new DashaDsl()
//      Set default parameters for generation.
        .defaults(new DashaDefaults(){
//          Change generation depth parameter. Set depth to 10.
            @Override
            public Function<Context, Long> defaultMaxGenerationDepth() {
//              Max depth is 10.
                return ctx -> 10L;
            }
        })
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        .def()
//      Get ObjectFactory
        .objectFactory()
//      Generate Person class object
        .get(Recursion.class);
```
## Path selectors
### Single path
It is possible to specify custom
[InstanceBuilder](../apidocs/org/genthz/function/InstanceBuilder.html){:target="_blank"}
or
[Filler](../apidocs/org/genthz/function/Filler.html){:target="_blank"}
for selected object fields. Next example shows custom filler for field ``name`` of the ``Person`` class.
```java
public class Person {
    private UUID uuid;
    private String name;
    private String lastName;
    private Date birthDate;

    public Person() {
    }
}

// *** Using custom instance builder ***
// Create custom configuration
Person value = new DashaDsl() {
        {
//          Custom generation for Person class strict.
            strict(Person.class)
//              Generate value for name field of Persone class.
                .path("name")
//              Set custom instance builder for field name of Person class.
                .simple(ctx -> "Alex");
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        }.def()
//      Get ObjectFactory
        .objectFactory()
//      Generate Person class object
        .get(Person.class);

// *** Using custom filler ***
// Create custom configuration
Person value = new DashaDsl() {
        {
//          Generate value for name field of any classes and any field type.
            path("name")
//              Set custom instance builder for field name of Person class.
                .filler(ctx -> ctx.set("Alex"));
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        }.def()
//      Get ObjectFactory
        .objectFactory()
//      Generate Person class object
        .get(Person.class);

// Create custom configuration
Person value = new  DashaDsl() {
        {

//          Generate value for object fiedl named as "name".
            path("name")
//              Field name must be type of String.
                .strict(String.class )
//              Set custom instance builder for field name of Person class.
                .simple(ctx -> "Alex");
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        }.def()
//      Get ObjectFactory
        .objectFactory()
//      Generate Person class object
        .get(Person.class);
```
### Path sequence
It is possible to specify the nesting level of the field. An next samples shows filling of the ``person.name`` field of
the class `User`.

```java
public class User {
    private Person person;

    private String login;

    private String password;
}

public class Person {
    protected String name;

    protected String lastName;

    protected Date birthday;

    private IdCard idCard;
}

User value = new DashaDsl() {
        {
//          Generate value for name fiedl of Persone class.
            path("person/name")
//              Set custom instance builder for field name of Person class.
                .simple(ctx -> "Alex");
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        }.def()
//      Get ObjectFactory
        .objectFactory()
//      Generate Person class object
        .get(User.class);
```
### Matche any symbols
You can use the ``*`` character to specify any matches any characters in the field name.

```java
User value = new DashaDsl() {
        {
//          Generate value for name fiedl of Persone class.
            path("person/n*e")
//              Set custom instance builder for field name of Person class.
                .simple(ctx -> "Alex");
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
        }.def()
//      Get ObjectFactory
        .objectFactory()
//      Generate Person class object
        .get(User.class);
```
### Matche root element
``/`` symbol at the start of the path points to root object. The next sample describes
the ``name`` (``java.lang.String`` class)
field any object assigned to the ``person`` field any another root object.

```java
public class User {
    private Person person;

    private String login;

    private String password;
}

public class Person {
    protected String name;

    protected String lastName;

    protected Date birthday;

    private IdCard idCard;
}

    User value = new DashaDsl() {
        {
//          Generate value for name fiedl of Persone class.
            path("/person/name")
//              Set custom instance builder for field name of Person class.
                    .simple(ctx -> "Alex");
        }
//      Use defaults configuration such as int, java.lang.Stirng and etc.
    }.def()
//      Get ObjectFactory
            .objectFactory()
//      Generate Person class object
            .get(User.class);
```
### Matche subpaths
Fixed nested lavel:
```java
path("/name"); // Field name of the root object. First level.
path("/../name"); // Field name of any subfief of the root object. Second level
path("/..5/name"); // 5th level