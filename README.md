# Optional

Optional is a wrapper library that allows developers to use Optional since JDK 1.6.


## Installation

add the following remote maven url to your root `build.gradle` file:
```groovy
allprojects {
    repositories {
     ......
     maven {url 'https://dl.bintray.com/gxjfict/AndroidMaven/'}
    }
}
```
add the following dependency to your app module `build.gradle`  file:
```groovy
dependencies {
    implementation 'com.hrdzkj:optional:0.1'
}
```
## Usage

### Basic

To begin using this Optional, it's like using java.util.Optional:


```java
        Optional<String> lastName = Optional.ofNullable("Daisuke");
        Optional<String> firstName = Optional.ofNullable("Sato");
        Optional<String> fullname = lastName.flatMap(new Function1<String, Optional<String>>() {
                        @Override
                        public Optional<String> apply(final String ln) {
                            return firstName.map(new Function1<String, String>() {
                                @Override
                                public String apply(final String fn) {
                                    return ln + "-" + fn;
                                }
                            });
                        }
                    });

// With retrolambda
        Optional<String> lastName = Optional.ofNullable("Daisuke");
        Optional<String> firstName = Optional.ofNullable("Sato");
        Optional<String> fullname = lastName.flatMap(ln -> firstName.map(fn -> {
                    return ln + "-" + fn;
                }));

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

