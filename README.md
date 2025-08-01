## Spring 연습
필수 패키지를 만들 수 있다.

CRUD 기능을 구현한다.

Create -> POST

Read -> Get

Update -> PUT, PATCH

Delete -> DELETE


### * Get일 때는 변경하지 않고 읽기만 하기 때문에 @Transactional에 readOnly = true를 추가해준다.


## Layered architecture

Controller <-> Service <-> Repository

