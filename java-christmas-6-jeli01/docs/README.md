# 기능 목록

---

## 주문 기능

### 날짜

- [x] 1이상 31이하의 숫자가 아닌 경우, 에러가 발생한다.

### 주문할 메뉴

- [x] 메뉴판에 없는 메뉴인 경우, 에러가 발생한다.
- [x] 메뉴의 개수가 1이상의 숫자가 아닌 경우, 에러가 발생한다.
- [x] 메뉴가 중복인 경우, 에러가 발생한다.
- [x] 음료만 주문했을 경우, 에러가 발생한다.
- [x] 21개 이상의 메뉴를 주문했을 경우, 에러가 발생한다.

---

## 혜택 계산 기능

### 12월 이벤트 기능

- 크리스마스 디데이 할인
    - [x] 2023.12.1에 1000원으로 이벤트가 시작된다.
    - [x] 100원씩 할인이 증가하며 2023.12.25에 이벤트가 종료된다.

- 크리스마스 디데이 할인을 제외한 이벤트
    - [x] 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.
    - [x] 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인한다.
    - [x] 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.
    - [x] 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.

### 이벤트 배지

- [x] 총 혜택 금액이 5천 원 이상이면 별을 지급한다.
- [x] 총 혜택 금액이 1만 원 이상이면 트리를 지급한다.
- [x] 총 혜택 금액이 2만 원 이상이면 산타를 지급한다.

### 이벤트 주의사항

- [x] 총 주문 금액 10,000원 이상부터 위의 이벤트가 적용된다.

### 기타 고려사항

- [x] 할인 금액이 총 금액 보다 넘어가면 무료이다.

---

## UI 기능

- [x] 예상 방문 날짜를 받는 기능
    - [x] 날짜 조건에 맞지 않는다면, 에러 메시지를 출력 후 재입력을 받는다.


- [x] 주문할 메뉴를 받는 기능
    - [x] 입력 형식이 다른 경우, 에러 메시지를 출력 후 재입력을 받는다.
        - [x] \- 문자 형식을 지키지 않은 경우
    - [x] 메뉴 조건에 맞지 않는다면, 에러 메세지를 출력 후 재입력을 받는다.


- [x] 주문 메뉴를 출력하는 기능
- [x] 할인 전 총 주문 금액을 출력하는 기능
- [x] 증정 메뉴를 출력하는 기능
    - [x] 해당 되지 않으면 "없음"을 출력한다.


- [x] 혜택 내역을 출력하는 기능
    - [x] 해당 되지 않으면 "없음"을 출력한다.


- [x] 총 혜택 금액을 출력하는 기능
- [x] 할인 후 결제 금액을 출력하는 기능
- [x] 이벤트 배지를 출력하는 기능
    - [x] 해당 되지 않으면 "없음"을 출력한다.