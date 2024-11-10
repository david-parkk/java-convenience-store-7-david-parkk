package store;

public enum Message {

    NO_QUANTITY(true, "수량이 모자랍니다"),
    SUCCESS(false, "정상 응답");

    private boolean isException;
    private String message;

    Message(boolean isException, String message) {
        this.isException = isException;
        this.message = message;
    }
}
