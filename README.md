# Optional [![Build Status][1]][2] [![Code Coverage][3]][4] [![Android Weekly][5]][6]

Optional is a wrapper library that allows developers to use Optional since JDK 1.6.


## Installation

Optional is installed by adding the following dependency to your `build.gradle` file:

```groovy
dependencies {
    implementation 'pub.hrdzkj:0.1.0'
}
```

## Usage

### Basic

To begin using Optional, it's like using java.util:


```java
Optional<String> lastName = Optional.ofNullable("Daisuke");
Optional<String> firstName = Optional.ofNullable("Sato");
Optional<String> fullname =
        lastName.flatMap(new Func1<String, Optional<String>>() {
            @Override
            public Optional<String> call(final String ln) {
                return firstName.map(new Func1<String, String>() {
                    @Override
                    public String call(final String fn) {
                        return Strings.join(" ", ln, fn);
                    }
                });
            }
        });

// With retrolambda
fullName = lastName.flatMap(ln -> firstName.map(fn -> Strings.join(" ", ln, fn)));
```

## LICENSE
```

    Copyright 2018 hrdzkj

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

```

