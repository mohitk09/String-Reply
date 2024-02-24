# Summary
Our company has released a beta version of **String Reply Service** and it has been a huge success.
In the current implementation (as part of boilerplate code), the **String Reply Service** takes in an input string (in the format of `[a-z0-9]*`)
and returns the input in a JSON object.

For example,

```
GET /reply/kbzw9ru
{
    "data": "kbzw9ru"
}
```

As the service is widely adopted, there have been increasing feature requests.
Our project manager has come back with the following requirements for V2 of the service:

The input string will now be comprised of two components, a rule and a string, separated by a dash (-).
Rules **always** contain two numbers. Each number represents a string operation.

The supported numbers are:

- `1`: reverse the string

   E.g. `kbzw9ru` becomes `ur9wzbk`

- `2`: encode the string via MD5 hash algorithm and display as hex

   E.g. `kbzw9ru` becomes `0fafeaae780954464c1b29f765861fad`

The numbers are applied in sequence, i.e. the output of the first rule will
serve as the input of the second rule. The numbers can also be repeated,
i.e. a rule of 11 would mean reversing the string twice, resulting in no change to the string.

Giving a few examples,

```
GET /v2/reply/11-kbzw9ru
{
    "data": "kbzw9ru"
}
```
```
GET /v2/reply/12-kbzw9ru
{
    "data": "5a8973b3b1fafaeaadf10e195c6e1dd4"
}
```
```
GET /v2/reply/22-kbzw9ru
{
    "data": "e8501e64cf0a9fa45e3c25aa9e77ffd5"
}
```



## Build project

To build the project, simply run
```
./gradlew build
```

## Start project

To start the project, simply run
```
./gradlew bootRun
```

Once the service started, the endpoint will be available at `localhost:8080`, so you can make request to the service endpoint

```json
GET localhost:8080/reply/helloworld

{
    message: "helloword"
}
```

## Implementation Details
1. Introduced a `StringReplyV2Controller`, that resides in the same folder as the v1 controller.
2. The the new controller processes the latest endpoints. 
3. The new controller relies on the RuleProcessor class for request processing.
4. The RuleProcessor class, has a switch case which simulates a factory design pattern to deal with different rules.
5. Currenly there are only two rules but this could be extended further if more rules are introduces.
6. Based on the rules, the required transformation happen.
7. Building and running the project remain the same and the new APIS can be tested the same way old APIs are tested.
7. Some basic test cases have been added.