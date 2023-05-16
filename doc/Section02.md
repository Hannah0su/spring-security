# spring-security

# 1장

# 안녕! 스프링 시큐리티

## ex01

- 프로젝트에 spring-security 종속성을 추가하고, 간단한 Rest 엔드포인트를 구현한다.

![](/images/ch02/ex01-001.png)

- 애플리케이션을 실행하면 위와 같이 콘솔에 시큐리티 패스워드가 생성된다.
- HTTP Basic 인증으로, 애플리케이션의 엔드포인트를 호출하려면 Authorization 헤더에 이 값을 싣어야한다.

![](/images/ch02/ex01-002.png)

- Authorization 헤더를 이용하지 않고, 엔드포인트를 호출한 모습
- `HTTP 401 Unauthorized`이 반환된다.

![](/images/ch02/ex01-003.png)

- Authorization 헤더를 이용하여 정상적으로 호출하면 위와 같이 올바른 응답이 반환된다.

>HTTP 401 Unauthorized : 클라이언트가 요청된 리소스에 대한 유효한 인증 자격 증명이 없어 요청을 거부
> 
> HTTP 403 Forbidden : 서버가 해당 요청의 호출자를 식별했지만, 권한이 없어서 요청을 거부

![](/images/ch02/components_of_spring_security_architecture.jpg) 

1. `Authentication Filter` 가 요청을 가로챈다.
2. 인증 책임이 `Authentication Manager`에게 위임된다.
3. `Authentication Manager`는 인증 로직이 구현된 `Authentication Provider` 를 이용한다.
4. `Authentication Provider`는 `UserDetailService` 로 사용자를 찾고, `Password Encoder` 로 암호를 검증한다.
5. 인증 결과가 `Authentication Filter`에 반환된다.
6. 인증된 엔티티에 관한 세부 정보가 `Security Context` 에 저장된다.

> UserDetailService 와 PasswordEncoder는 자동으로 구성되는 빈이다.
> 
> UserDetailService와 PasswordEncoder 객체 모두 Basic 인증 흐름에 꼭 필요한 요소이다.

`Authentication Provider` 는 이러한 빈을 통해 사용자를 찾고 암호를 확인한다.

사용자의 세부 정보는 스프링 시큐리티로 `UserDetailService` 계약을 구현하는 객체가 관리한다.
이러한 기본 자격 증명에서 사용자의 이름은 'user', 암호는 UUID 형식이며, Spring Context 가 로드될 때 생성된다.

`PasswordEncoder`는 두 가지 일을 수행한다.
- 암호 인코딩
- 암호가 기존 인코딩과 일치하는지 확인

---

