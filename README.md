# java-convenience-store-precourse

## 결제 시스템 구현하기
- 사용자가 입력한 상품의 가격과 수량을 기반으로 최종 결제 금액을 계산한다.
  - 총구매액은 상품별 가격과 수량을 곱하여 계산하며, 프로모션 및 멤버십 할인 정책을 반영하여 최종 결제 금액을 산출한다.
- 구매 내역과 산출한 금액 정보를 영수증으로 출력한다.
- 영수증 출력 후 추가 구매를 진행할지 또는 종료할지를 선택할 수 있다.

<hr>

## 기능목록
### 상품을 구매할 수 있다.

- 재고가 있는 상품만 구입할 수 있다.

### 프로모션 할인을 적용할 수 있다

- 구입한 날짜가 프로모션 날짜 범위에 있어야 적용할 수 있다.
- 프로모션은 N + 1 구조이다.
- 프로모션 재고가 떨어지면, 정가로 구매해야한다.

### 맴버쉽 할인을 적용할 수 있다.

- 맴버쉽 회원은 프로모션 미적용 금액의 30%를 할인 받는다.
- 최대 맴버쉽 할인의 최대한 한도는 8000원이다.

### 영수증을 출력 할 수 있다.

- 영수증으로 구매 내역과 할인을 요약한다.
- 영수증 항목은 아래와 같다.
  - 구매 상품 내역: 구매한 상품명, 수량, 가격
  - 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록
  - 금액 정보
    - 총구매액: 구매한 상품의 총 수량과 총 금액
    - 행사할인: 프로모션에 의해 할인된 금액
    - 멤버십할인: 멤버십에 의해 추가로 할인된 금액
    - 내실돈: 최종 결제 금액
